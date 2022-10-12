package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.model.Feature;
import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.repository.FeatureRepository;
import it.cnr.ilc.projectx.repository.LayerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LayerFeatureConnectorService {

    @NonNull
    private final FeatureRepository featureRepository;
    @NonNull
    private final AnnotationFeatureService annotationFeatureService;


    public boolean canAllFeaturesBeDeletedByLayerId(Layer layer) {
        for (Feature feature : layer.getFeatures()) {
            if (!canFeatureBeDeleted(layer, feature.getId())) {
                return false;
            }
        }

        return true;
    }

    public Boolean canFeatureBeDeleted(Layer layer, Long featureId) {
        if (annotationFeatureService.existsByFeatureId(featureId)) {
            return false;
        }

        for (Feature feature : layer.getFeatures()) {
            if (feature.getId() == featureId) {
                return true;
            }
        }

        return false;
    }

    @Transactional
    public Boolean deleteAllFeaturesAssociatedToLayer(Layer layer) {
        for (Feature feature : layer.getFeatures()) {
            if (!deleteFeature(layer, feature.getId())) {
                return false;
            }
        }

        return true;
    }

    @Transactional
    public Boolean deleteFeature(Layer layer, Long featureId) {
        if (canFeatureBeDeleted(layer, featureId)) {
            featureRepository.deleteById(featureId);

            return true;
        }

        return false;
    }
}
