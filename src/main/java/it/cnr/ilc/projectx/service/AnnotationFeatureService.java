package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.AnnotationFeatureDto;
import it.cnr.ilc.projectx.dto.AnnotationRelationDto;
import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.model.AnnotationFeature;
import it.cnr.ilc.projectx.model.AnnotationRelation;
import it.cnr.ilc.projectx.repository.AnnotationFeatureRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class AnnotationFeatureService {

    @NonNull
    private final AnnotationFeatureRepository annotationFeatureRepository;

    public Boolean existsByFeatureId(Long featureId) {
        return annotationFeatureRepository.existsByFeatureId(featureId);
    }

    @Transactional
    public AnnotationFeatureDto save(AnnotationFeatureDto annotationFeatureDto) {
        List<AnnotationFeature> annotationFeatureList = annotationFeatureRepository.saveAll(mapToEntity(annotationFeatureDto));

        return mapToAnnotationFeatureDto(annotationFeatureList);
    }

    @Transactional
    public AnnotationFeatureDto update(AnnotationFeatureDto annotationFeatureDto) {
        checkArgument(annotationFeatureDto != null);
        checkArgument(annotationFeatureDto.getAnnotationId() != null);
        checkArgument(annotationFeatureDto.getFeatures() != null);

//        Optional<List<AnnotationFeature>> tobeUpdated = annotationFeatureRepository.findByAnnotationId(annotationFeatureDto.getAnnotationId());

//        if (tobeUpdated.isEmpty()) {
//            log.error("Cannot find annotation features with ID " + annotationFeatureDto.getAnnotationId());
//            throw new NotFoundException("Cannot find annotation features with ID " + annotationFeatureDto.getAnnotationId());
//        }

        annotationFeatureRepository.deleteByAnnotationId(annotationFeatureDto.getAnnotationId());
        annotationFeatureRepository.saveAll(mapToEntity(annotationFeatureDto));

        List<AnnotationFeature> annotationFeature = annotationFeatureRepository.findByAnnotationId(annotationFeatureDto.getAnnotationId())
                .orElseThrow(EntityNotFoundException::new);

        return mapToAnnotationFeatureDto(annotationFeature);
    }

    public boolean delete(Long annotationId) {
        try {
            annotationFeatureRepository.deleteByAnnotationId(annotationId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private AnnotationFeatureDto mapToAnnotationFeatureDto(List<AnnotationFeature> annotationFeatures) {
        AnnotationFeatureDto annotationFeatureDto = new AnnotationFeatureDto();

        annotationFeatureDto.setAnnotationId(annotationFeatures.stream().findFirst().orElseThrow().getAnnotationId());

        List<Long> featureList = annotationFeatures.stream().map(annotationFeature ->
                        annotationFeature.getFeatureId())
                .collect(Collectors.toList());
        annotationFeatureDto.setFeatures(featureList);

        return annotationFeatureDto;
    }

    private List<AnnotationFeature> mapToEntity(AnnotationFeatureDto annotationFeatureDto) {
        List<AnnotationFeature> annotationFeatureList = new ArrayList<>();

        for (Long featureId : annotationFeatureDto.getFeatures()) {
            AnnotationFeature annotationFeature = new AnnotationFeature();
            annotationFeature.setAnnotationId(annotationFeatureDto.getAnnotationId());
            annotationFeature.setFeatureId(featureId);
            annotationFeatureList.add(annotationFeature);
        }

        return annotationFeatureList;
    }
}
