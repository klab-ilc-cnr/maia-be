package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.model.AnnotationRelation;
import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.repository.AnnotationRelationRepository;
import it.cnr.ilc.projectx.repository.LayerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnotationRelationService {

    @NonNull
    private final AnnotationRelationRepository annotationRelationRepository;
    @NonNull
    private final LayerRepository layerRepository;


    public List<AnnotationRelationDto> retrieveByTextId(Long textId) {
        Optional<List<AnnotationRelation>> entity = annotationRelationRepository.findByTextId(textId);

        if (!entity.isPresent()) {
            return new ArrayList<AnnotationRelationDto>();
        }

        return mapToAnnotationRelationDto(entity.get());
    }

    public Boolean existsAnyLayerInRelation(Long layerId) {
        return annotationRelationRepository.existsBySrcLayer_IdOrTargetLayer_Id(layerId, layerId);
    }

    public AnnotationRelationDto save(CreateAnnotationRelationDto createAnnotationRelationDto) {
        AnnotationRelation annotationRelations = annotationRelationRepository.save(
                mapToEntity(createAnnotationRelationDto));

        return mapToAnnotationRelationDto(annotationRelations);
    }

    @Transactional
    public AnnotationRelationDto update(UpdateAnnotationRelationDto annotationRelationDto) {
        checkArgument(annotationRelationDto != null);
        checkArgument(annotationRelationDto.getId() != null);
        checkArgument(annotationRelationDto.getName() != null);

        Optional<AnnotationRelation> tobeUpdated = annotationRelationRepository.findById(annotationRelationDto.getId());

        if (tobeUpdated.isEmpty()) {
            log.error("Cannot find relation with ID " + annotationRelationDto.getId());
            throw new NotFoundException("Cannot find relation with ID " + annotationRelationDto.getId());
        }

        annotationRelationRepository.save(mapToEntity(tobeUpdated.get(), annotationRelationDto));

        AnnotationRelation annotationRelation = annotationRelationRepository.findById(annotationRelationDto.getId()).orElseThrow(EntityNotFoundException::new);

        return mapToAnnotationRelationDto(annotationRelation);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Boolean delete(Long relationId) {
        try {
            annotationRelationRepository.deleteById(relationId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private List<AnnotationRelationDto> mapToAnnotationRelationDto(List<AnnotationRelation> annotationRelations) {
        return annotationRelations.stream().map(annotationRelation -> mapToAnnotationRelationDto(annotationRelation))
                .collect(Collectors.toList());
    }

    private AnnotationRelationDto mapToAnnotationRelationDto(AnnotationRelation annotationRelation) {
        AnnotationRelationDto annotationRelationDto = new AnnotationRelationDto();

        annotationRelationDto.setId(annotationRelation.getId());
        annotationRelationDto.setDescription(annotationRelation.getDescription());
        annotationRelationDto.setName(annotationRelation.getName());
        annotationRelationDto.setSrcAnnotationId(annotationRelation.getSrcAnnotationId());
        annotationRelationDto.setTargetAnnotationId(annotationRelation.getTargetAnnotationId());
        annotationRelationDto.setSrcLayerId(annotationRelation.getSrcLayer().getId());
        annotationRelationDto.setTargetLayerId(annotationRelation.getTargetLayer().getId());
        annotationRelationDto.setTextId(annotationRelation.getTextId());

        return annotationRelationDto;
    }

    private AnnotationRelation mapToEntity(CreateAnnotationRelationDto annotationRelationDto) {
        AnnotationRelation annotationRelation = new AnnotationRelation();
        BeanUtils.copyProperties(annotationRelationDto, annotationRelation);
        annotationRelation.setSrcAnnotationId(annotationRelationDto.getSrcAnnotationId());
        annotationRelation.setTargetAnnotationId(annotationRelationDto.getTargetAnnotationId());
        Layer srcLayer = layerRepository.getById(annotationRelationDto.getSrcLayerId());
        annotationRelation.setSrcLayer(srcLayer);
        Layer targetLayer = layerRepository.getById(annotationRelationDto.getTargetLayerId());
        annotationRelation.setTargetLayer(targetLayer);
        annotationRelation.setTextId(annotationRelationDto.getTextId());

        return annotationRelation;
    }

    private AnnotationRelation mapToEntity(AnnotationRelation tobeUpdatedEntity, UpdateAnnotationRelationDto updateDto) {
        BeanUtils.copyProperties(updateDto, tobeUpdatedEntity);
//        tobeUpdatedEntity.setSrcAnnotationId(updateDto.getSrcAnnotationId());
//        tobeUpdatedEntity.setTargetAnnotationId(updateDto.getTargetAnnotationId());
//        tobeUpdatedEntity.setSrcLayer(layerRepository.getById(updateDto.getSrcLayerId()));
//        tobeUpdatedEntity.setTargetLayer(layerRepository.getById(updateDto.getTargetLayerId()));
//        tobeUpdatedEntity.setTextId(updateDto.getTextId());

        return tobeUpdatedEntity;
    }
}
