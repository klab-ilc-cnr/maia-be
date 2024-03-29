package it.cnr.ilc.maia.service;

import it.cnr.ilc.maia.dto.AnnotationFeatureDto;
import it.cnr.ilc.maia.model.AnnotationFeature;
import it.cnr.ilc.maia.repository.AnnotationFeatureRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
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
        checkArgument(annotationFeatureDto.getFeatureIds() != null);

//        Optional<List<AnnotationFeature>> tobeUpdated = annotationFeatureRepository.findByAnnotationId(annotationFeatureDto.getAnnotationId());
//        if (tobeUpdated.isEmpty()) {
//            throw new NotFoundException("Cannot find annotation features with ID " + annotationFeatureDto.getAnnotationId());
//        }
        annotationFeatureRepository.deleteByAnnotationId(annotationFeatureDto.getAnnotationId());
        annotationFeatureRepository.saveAll(mapToEntity(annotationFeatureDto));

        List<AnnotationFeature> annotationFeature = annotationFeatureRepository.findByAnnotationId(annotationFeatureDto.getAnnotationId())
                .orElseThrow(EntityNotFoundException::new);

        return mapToAnnotationFeatureDto(annotationFeature);
    }

    @Transactional
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
        annotationFeatureDto.setLayerId(annotationFeatures.stream().findFirst().orElseThrow().getLayerId());

        List<Long> featureList = annotationFeatures.stream().map(annotationFeature
                -> annotationFeature.getFeatureId())
                .collect(Collectors.toList());
        annotationFeatureDto.setFeatureIds(featureList);

        return annotationFeatureDto;
    }

    private List<AnnotationFeature> mapToEntity(AnnotationFeatureDto annotationFeatureDto) {
        List<AnnotationFeature> annotationFeatureList = new ArrayList<>();

        for (Long featureId : annotationFeatureDto.getFeatureIds()) {
            AnnotationFeature annotationFeature = new AnnotationFeature();
            annotationFeature.setAnnotationId(annotationFeatureDto.getAnnotationId());
            annotationFeature.setLayerId(annotationFeatureDto.getLayerId());
            annotationFeature.setFeatureId(featureId);
            annotationFeatureList.add(annotationFeature);
        }

        return annotationFeatureList;
    }

    public Optional<AnnotationFeature> findAnyByLayerId(Long layerId) {
        return annotationFeatureRepository.findByLayerId(layerId).stream().findAny();
    }
}
