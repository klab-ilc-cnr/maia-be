package it.cnr.ilc.maia.dto.texto;

import java.util.Map;
import lombok.Getter;

/**
 *
 * @author oakgen
 */
@Getter
public class TextoAnnotationFeatureCreateRequest {

    private final Map<String, Object> annotation;
    private final Map<String, Object> feature;
    private final String value;

    public TextoAnnotationFeatureCreateRequest(Long annotationId, AnnotationFeature annotationFeature) {
        this.annotation = Map.of("id", annotationId);
        this.feature = Map.of("id", annotationFeature.getFeatureId());
        this.value = annotationFeature.getValue();
    }

}
