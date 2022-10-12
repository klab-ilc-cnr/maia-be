/*
package it.cnr.ilc.projectx.service.eventHandler;

import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.repository.FeatureRepository;
import it.cnr.ilc.projectx.repository.LayerRepository;
import it.cnr.ilc.projectx.repository.TagsetRepository;
import it.cnr.ilc.projectx.service.AnnotationFeatureService;
import it.cnr.ilc.projectx.service.FeatureService;
import it.cnr.ilc.projectx.service.event.CanTagsetBeDeletedEvent;
import it.cnr.ilc.projectx.service.event.DeleteTagsetEvent;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class DeleteTagsetHandler {

    @NonNull
    private final FeatureService featureService;

    @NonNull
    private final TagsetRepository tagsetRepository;

    @EventListener
    public void deleteTagsetEvent(final DeleteTagsetEvent event) {
        deleteTagset(event.getTagsetId());
    }

    @EventListener
    public void canTagsetBeDeletedEvent(final CanTagsetBeDeletedEvent event) {
        canTagsetBeDeleted(event.getTagsetId());
    }

    @Transactional
    public Boolean deleteTagset(Long tagsetId) {
        if (canTagsetBeDeleted(tagsetId)) {
            tagsetRepository.deleteById(tagsetId);

            return true;
        }

        return false;
    }

    public Boolean canTagsetBeDeleted(Long tagsetId) {
        if (featureService.existsByTagset_Id(tagsetId)) {
            return false;
        }

        return true;
    }
}
*/
