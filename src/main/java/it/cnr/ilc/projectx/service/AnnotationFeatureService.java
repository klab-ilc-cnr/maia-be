package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.AnnotationFeatureDto;
import it.cnr.ilc.projectx.dto.CreateTagsetDto;
import it.cnr.ilc.projectx.model.AnnotationFeature;
import it.cnr.ilc.projectx.model.Tagset;
import it.cnr.ilc.projectx.repository.AnnotationFeatureRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        AnnotationFeature annotationFeature = annotationFeatureRepository.save(mapToEntity(annotationFeatureDto));

        return mapToAnnotationFeatureDto(annotationFeature);
    }

    private AnnotationFeatureDto mapToAnnotationFeatureDto(AnnotationFeature annotationFeature) {
        AnnotationFeatureDto annotationFeatureDto = new AnnotationFeatureDto();

        annotationFeatureDto.setAnnotationId(annotationFeature.getAnnotationId());
        annotationFeatureDto.setFeatureId(annotationFeature.getFeatureId());

        return annotationFeatureDto;
    }

    private AnnotationFeature mapToEntity(AnnotationFeatureDto annotationFeatureDto) {
        AnnotationFeature annotationFeature = new AnnotationFeature();

        annotationFeature.setAnnotationId(annotationFeatureDto.getAnnotationId());
        annotationFeature.setFeatureId(annotationFeatureDto.getFeatureId());

        return annotationFeature;
    }
}
