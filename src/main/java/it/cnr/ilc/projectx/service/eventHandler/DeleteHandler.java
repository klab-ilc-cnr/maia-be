package it.cnr.ilc.projectx.service.eventHandler;

import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.repository.FeatureRepository;
import it.cnr.ilc.projectx.repository.LayerRepository;
import it.cnr.ilc.projectx.repository.TagsetRepository;
import it.cnr.ilc.projectx.service.AnnotationFeatureService;
import it.cnr.ilc.projectx.service.FeatureService;
import it.cnr.ilc.projectx.service.event.DeleteTagsetEvent;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;

@Component
@AllArgsConstructor
public class DeleteHandler {

    @NonNull
    private final LayerRepository layerRepository;

    @NonNull
    private final FeatureService featureService;

    @NonNull
    private final AnnotationFeatureService annotationFeatureService;

    @NonNull
    private final FeatureRepository featureRepository;

    //LAYER

/*    public Boolean canLayerBeDeleted(Long layerId) {
        //TODO aggiungere check se è usato in una Relation
        if (canAllFeaturesBeDeletedByLayerId(layerId)) {
            return true;
        }

        return false;
    }

    public Boolean deleteLayer(Layer layer) {
        if (canLayerBeDeleted(layer.getId())) {
            //TODO aggiungere eliminazione a cascata di tutte le feature che fanno parte del layer
            deleteAllFeaturesByLayerId(layer.getId());
            layerRepository.delete(layer);

            return true;
        }

        return false;
    }*/

    // FEATURE

/*    public boolean canAllFeaturesBeDeletedByLayerId(Long layerId) {
        for (FeatureDto featureDto : featureService.retrieveAllByLayerId(layerId)) {
            if (!canFeatureBeDeleted(layerId, featureDto.getId())) {
                return false;
            }
        }

        return true;
    }

    public Boolean deleteAllFeaturesByLayerId(Long layerId) {
        for (FeatureDto featureDto : featureService.retrieveAllByLayerId(layerId)) {
            if (!deleteFeature(layerId, featureDto.getId())) {
                return false;
            }
        }

        return true;
    }

    public Boolean canFeatureBeDeleted(Long layerId, Long featureId) {
        if (annotationFeatureService.existsByFeatureId(featureId)) {
            return false;
        }

        FeatureDto featureDto = featureService.retrieveById(featureId);

        if (featureDto.getLayerId() != layerId) {
            return false;
        }

        return true;
    }

    @Transactional
    public Boolean deleteFeature(Long layerId, Long featureId) {
        if (canFeatureBeDeleted(layerId, featureId)) {
            featureRepository.deleteById(featureId);

            return true;
        }

        return false;
    }*/
}
