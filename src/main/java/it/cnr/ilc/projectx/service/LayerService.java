package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.repository.LayerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class LayerService {

    @NonNull
    private final LayerRepository layerRepository;

    @NonNull
    private final LayerFeatureConnectorService layerFeatureConnectorService;

    @NonNull
    private final AnnotationRelationService annotationRelationService;

    public List<LayerDto> retrieveAllLayers() {
        return mapToLayerDto(layerRepository.findAll());
    }

    @Transactional
    public LayerDto saveLayer(LayerDto layerDto) {
        Layer layer = layerRepository.save(mapToEntity(layerDto));
        return mapToLayerDto(layer);
    }

    private Layer mapToEntity(LayerDto layerDto) {
        Layer layer = new Layer();
        BeanUtils.copyProperties(layerDto, layer);
        return layer;
    }

    private List<LayerDto> mapToLayerDto(List<Layer> layers) {
        return layers.stream().map(layer -> mapToLayerDto(layer)).collect(Collectors.toList());
    }

    private LayerDto mapToLayerDto(Layer layer) {
        LayerDto layerDto = new LayerDto();
        BeanUtils.copyProperties(layer, layerDto);
        return layerDto;
    }

    private LayerChoiceDto mapToLayerChoiceDto(Layer layer) {
        LayerChoiceDto layerChoiceDto = new LayerChoiceDto();
        layerChoiceDto.setId(layer.getId());
        layerChoiceDto.setColor(layer.getColor());
        layerChoiceDto.setDescription(layer.getDescription());
        layerChoiceDto.setName(layer.getName());

        return layerChoiceDto;
    }

    private Layer mapToEntity(Layer tobeUpdated, UpdateLayerChoiceDto updateLayerChoiceDto) {
        Layer layer = new Layer();
        BeanUtils.copyProperties(tobeUpdated, layer);
        BeanUtils.copyProperties(updateLayerChoiceDto, layer);

        return layer;
    }

    public Layer retrieveLayer(Long layerId) {
        return layerRepository.findById(layerId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Boolean canBeDeleted(Long layerId) {
        if(annotationRelationService.existsAnyLayerInRelation(layerId))
        {
            return false;
        }

        Layer layer = retrieveLayer(layerId);
        if (layerFeatureConnectorService.canAllFeaturesBeDeletedByLayerId(layer)) {
            return true;
        }

        return false;
    }

    @Transactional
    public Boolean delete(Layer layer) {
        if (canBeDeleted(layer.getId())) {
            layerFeatureConnectorService.deleteAllFeaturesAssociatedToLayer(layer);
            layerRepository.delete(layer);

            return true;
        }

        return false;
    }

/*    public Boolean canBeDeleted(Long layerId) {
        //TODO aggiungere check se è usato in una Relation
        if (featureService.canAllFeaturesBeDeletedByLayerId(layerId)) {
            return true;
        }

        return false;
    }

    public Boolean delete(Layer layer) {
        if (canBeDeleted(layer.getId())) {
            //TODO aggiungere eliminazione a cascata di tutte le feature che fanno parte del layer
            featureService.deleteAllFeaturesByLayerId(layer.getId());
            layerRepository.delete(layer);

            return true;
        }

        return false;
    }*/

    @Transactional
    public LayerChoiceDto update(UpdateLayerChoiceDto updateLayerChoiceDto) {
        checkArgument(updateLayerChoiceDto != null);
        checkArgument(updateLayerChoiceDto.getId() != null);
        checkArgument(updateLayerChoiceDto.getName() != null);

        Layer tobeUpdated = retrieveLayer(updateLayerChoiceDto.getId());

        if (tobeUpdated == null) {
            log.error("Cannot find layer with ID " + updateLayerChoiceDto.getId());
            throw new NotFoundException("Cannot find layer with ID " + updateLayerChoiceDto.getId());
        }

        layerRepository.save(mapToEntity(tobeUpdated, updateLayerChoiceDto));

        Layer layer = retrieveLayer(updateLayerChoiceDto.getId());

        return mapToLayerChoiceDto(layer);
    }
}
