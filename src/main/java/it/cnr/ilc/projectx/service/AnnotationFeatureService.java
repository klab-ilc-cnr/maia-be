package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.LayerChoiceDto;
import it.cnr.ilc.projectx.dto.LayerDto;
import it.cnr.ilc.projectx.dto.UpdateLayerChoiceDto;
import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.repository.AnnotationFeatureRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnotationFeatureService {

    @NonNull
    private final AnnotationFeatureRepository annotationFeatureRepository;

    public Boolean isPresentFeature(Long featureId) {
        return annotationFeatureRepository.findByFeatureId(featureId).isPresent();
    }
}
