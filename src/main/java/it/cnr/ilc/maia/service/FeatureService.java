package it.cnr.ilc.maia.service;

import it.cnr.ilc.maia.repository.FeatureRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import jakarta.persistence.EntityNotFoundException;
import static com.google.common.base.Preconditions.checkArgument;
import it.cnr.ilc.maia.dto.AnnotationFeatureDto;
import it.cnr.ilc.maia.dto.CreateFeatureDto;
import it.cnr.ilc.maia.dto.FeatureDto;
import it.cnr.ilc.maia.model.AnnotationFeature;
import it.cnr.ilc.maia.model.Feature;
import it.cnr.ilc.maia.model.FeatureType;
import it.cnr.ilc.maia.model.Layer;
import it.cnr.ilc.maia.model.Tagset;

@Service
@RequiredArgsConstructor
public class FeatureService {

    @NonNull
    private final FeatureRepository featureRepository;

    @NonNull
    private final LayerService layerService;

    @NonNull
    private final TagsetService tagsetService;

    @NonNull
    private final LayerFeatureConnectorService layerFeatureConnectorService;

    @NonNull
    private final AnnotationFeatureService annotationFeatureService;

    public List<FeatureDto> retrieveAllByLayerId(Long layerId) {
        return mapToFeatureDto(featureRepository.findByLayer_Id(layerId));
    }

    @Transactional
    public CreateFeatureDto saveFeature(CreateFeatureDto featureDto) {
        Layer layer = layerService.retrieveLayer(featureDto.getLayerId());
        Tagset tagset = null;
        if (featureDto.getType() == FeatureType.TAGSET) {
            tagset = tagsetService.retrieveEntityById(Objects.requireNonNull(featureDto.getTagsetId()));
        }

        Feature feature = featureRepository.save(mapToEntity(featureDto, layer, tagset));

        //NOTA da specifiche ogni associazione tra annotazione e feature contiene una entry per ogni feature
        //presente nel layer, perciò se si inserisce una nuova feature questa va aggiunta alla tabella associativa
        //con valore vuoto
        Optional<AnnotationFeature> anyExistentAnnotationFeature = annotationFeatureService.findAnyByLayerId(featureDto.getLayerId());
        if (anyExistentAnnotationFeature.isPresent()) {
            AnnotationFeatureDto annotationFeatureDto = new AnnotationFeatureDto();
            Long annotationId = anyExistentAnnotationFeature.orElseThrow(EntityNotFoundException::new).getAnnotationId();
            annotationFeatureDto.setAnnotationId(annotationId);
            annotationFeatureDto.setLayerId(feature.getLayer().getId());
            annotationFeatureDto.setFeatureIds(List.of(feature.getId()));
            annotationFeatureService.save(annotationFeatureDto);
        }

        return mapToCreateFeatureDto(feature);
    }

    public FeatureDto updateFeature(FeatureDto featureDto) {
        checkArgument(featureDto != null);
        checkArgument(featureDto.getId() != null);
        checkArgument(featureDto.getName() != null);
        checkArgument(featureDto.getLayerId() != null);

        Optional<Feature> tobeUpdated = featureRepository.findById(featureDto.getId());

        if (tobeUpdated.isEmpty()) {
            throw new NotFoundException("Cannot find feature with ID " + featureDto.getId());
        }

        featureRepository.save(mapToEntity(tobeUpdated.get(), featureDto));

        Feature feature = featureRepository.findById(featureDto.getId()).orElseThrow(EntityNotFoundException::new);

        return mapToFeatureDto(feature);
    }

    /*    public Boolean canBeDeleted(Long layerId, Long featureId) {
        if (annotationFeatureService.existsByFeatureId(featureId)) {
            return false;
        }

        FeatureDto featureDto = retrieveById(featureId);

        if (featureDto.getLayerId() != layerId) {
            return false;
        }

        return true;
    }

    @Transactional
    public Boolean deleteFeature(Long layerId, Long featureId) {
        if (canBeDeleted(layerId, featureId)) {
            featureRepository.deleteById(featureId);

            return true;
        }

        return false;
    }*/
    public FeatureDto retrieveById(Long id) {
        return mapToFeatureDto(featureRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

//    public boolean canAllFeaturesBeDeletedByLayerId(Long layerId) {
//        for (FeatureDto featureDto : retrieveAllByLayerId(layerId)) {
//            if (!canBeDeleted(layerId, featureDto.getId())) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    public Boolean deleteAllFeaturesByLayerId(Long layerId) {
//        for (FeatureDto featureDto : retrieveAllByLayerId(layerId)) {
//            if (!deleteFeature(layerId, featureDto.getId())) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//    @Transactional
//    public Boolean deleteTagset(Long tagsetId) {
//        if (canTagsetBeDeleted(tagsetId)) {
//            tagsetService.uncheckedDelete(tagsetId);
//
//            return true;
//        }
//
//        return false;
//    }
//    public boolean existsByTagset_Id(Long tagsetId) {
//        return featureRepository.existsByTagset_Id(tagsetId);
//    }
//    public Boolean canTagsetBeDeleted(Long tagsetId) {
//        if (existsByTagset_Id(tagsetId)) {
//            return false;
//        }
//
//        return true;
//    }

    /*    public boolean canAllFeaturesBeDeletedByLayerId(Long layerId) {
        for (FeatureDto featureDto : retrieveAllByLayerId(layerId)) {
            if (!canBeDeleted(layerId, featureDto.getId())) {
                return false;
            }
        }

        return true;
    }

    public Boolean deleteAllByLayerId(Long layerId) {
        for (FeatureDto featureDto : retrieveAllByLayerId(layerId)) {
            if (!delete(layerId, featureDto.getId())) {
                return false;
            }
        }

        return true;
    }*/
    public Boolean canBeDeleted(Long layerId, Long featureId) {
        Layer layer = layerService.retrieveLayer(layerId);

        return layerFeatureConnectorService.canFeatureBeDeleted(layer, featureId);
    }

    @Transactional
    public Boolean delete(Long layerId, Long featureId) {
        Layer layer = layerService.retrieveLayer(layerId);

        return layerFeatureConnectorService.deleteFeature(layer, featureId);
    }

    private Feature mapToEntity(Feature existingFeature, FeatureDto updateFeatureDto) {
        existingFeature.setName(updateFeatureDto.getName());
        existingFeature.setDescription(updateFeatureDto.getDescription());
        existingFeature.setType(updateFeatureDto.getType());
        Tagset tagset = null;

        if (updateFeatureDto.getType() == FeatureType.TAGSET) {
            tagset = tagsetService.retrieveEntityById(updateFeatureDto.getTagset().getId());
        }

        existingFeature.setTagset(tagset);

        return existingFeature;
    }

    private Feature mapToEntity(CreateFeatureDto featureDto, Layer layer, Tagset tagset) {
        Feature feature = new Feature();
        BeanUtils.copyProperties(featureDto, feature);
        feature.setLayer(layer);

        if (tagset != null) {
            feature.setTagset(tagset);
        }

        return feature;
    }

    private List<FeatureDto> mapToFeatureDto(List<Feature> features) {
        return features.stream().map(feature -> mapToFeatureDto(feature)).collect(Collectors.toList());
    }

    private FeatureDto mapToFeatureDto(Feature feature) {
        FeatureDto featureDto = new FeatureDto();
        BeanUtils.copyProperties(feature, featureDto);
        featureDto.setLayerId(feature.getLayer().getId());

        if (feature.getType() == FeatureType.TAGSET) {
            featureDto.setTagset(tagsetService.mapToTagsetDto(Objects.requireNonNull(feature.getTagset())));
        }

        return featureDto;
    }

    private CreateFeatureDto mapToCreateFeatureDto(Feature feature) {
        CreateFeatureDto createFeatureDto = new CreateFeatureDto();
        BeanUtils.copyProperties(feature, createFeatureDto);

        return createFeatureDto;
    }
}
