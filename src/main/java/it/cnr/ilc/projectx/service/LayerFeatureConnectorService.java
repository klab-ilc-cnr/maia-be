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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LayerFeatureConnectorService {

    @NonNull
    private final FeatureRepository featureRepository;

    @NonNull
    private final LayerRepository layerRepository;
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
        List<Feature> featuresCopy = new ArrayList<>(layer.getFeatures());

        if (canFeatureBeDeleted(layer, featureId)) {
            featuresCopy.removeIf(t -> t.getId() == featureId);
            featureRepository.deleteById(featureId);

            layer.setFeatures(featuresCopy);
            layerRepository.save(layer);

            return true;
        }

        return false;
    }
}
