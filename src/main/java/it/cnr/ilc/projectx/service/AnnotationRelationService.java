package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.model.AnnotationRelation;
import it.cnr.ilc.projectx.repository.AnnotationRelationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnotationRelationService {

    @NonNull
    private final AnnotationRelationRepository annotationRelationRepository;

    public AnnotationRelationDto retrieveById(Long id) {
        return mapToAnnotationRelationDto(annotationRelationRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    public Boolean existsAnyLayerInRelation(Long layerId) {
        return annotationRelationRepository.existsBySrcLayer_IdOrTargetLayer_Id(layerId, layerId);
    }

    private AnnotationRelationDto mapToAnnotationRelationDto(AnnotationRelation annotationRelation) {
        AnnotationRelationDto annotationRelationDto = new AnnotationRelationDto();

        annotationRelationDto.setId(annotationRelation.getId());
        annotationRelationDto.setDescription(annotationRelation.getDescription());
        annotationRelationDto.setName(annotationRelation.getName());
        annotationRelationDto.setSrcAnnId(annotationRelation.getSrcAnnotationId());
        annotationRelationDto.setTargetAnnId(annotationRelation.getTargetAnnotationId());
        annotationRelationDto.setSrcLayerId(annotationRelation.getSrcLayer().getId());
        annotationRelationDto.setTargetLayerId(annotationRelation.getTargetLayer().getId());

        return annotationRelationDto;
    }

//    private AnnotationRelation mapToEntity(Tagset existingTagset, UpdateTagsetDto updateTagsetDto) {
//        existingTagset.setName(updateTagsetDto.getName());
//        existingTagset.setDescription(updateTagsetDto.getDescription());
//
//        existingTagset.setValues(
//                updateTagsetDto.getValues().stream().map(createTagsetValuesDto -> {
//                    TagsetValue tagsetValue = new TagsetValue();
//                    tagsetValue.setTagset(existingTagset);
//                    tagsetValue.setName(createTagsetValuesDto.getName());
//                    tagsetValue.setDescription(createTagsetValuesDto.getDescription());
//
//                    return tagsetValue;
//                }).collect(Collectors.toList()));
//        return existingTagset;
//    }

//    private AnnotationRelation mapToEntity(CreateTagsetDto tagsetDto) {
//        Tagset tagset = new Tagset();
//        BeanUtils.copyProperties(tagsetDto, tagset);
//
//        tagset.setValues(
//                tagsetDto.getValues().stream().map(createTagsetValuesDto -> {
//                    TagsetValue tagsetValue = new TagsetValue();
//                    tagsetValue.setTagset(tagset);
//                    tagsetValue.setName(createTagsetValuesDto.getName());
//                    tagsetValue.setDescription(createTagsetValuesDto.getDescription());
//
//                    return tagsetValue;
//                }).collect(Collectors.toList()));
//        return tagset;
//    }

//    private List<AnnotationRelationDto> mapToAnnotationRelationDto(List<AnnotationRelation> annotationRelations) {
//        return tagsets.stream().map(tagset -> mapToTagsetDto(tagset)).collect(Collectors.toList());
//    }
}
