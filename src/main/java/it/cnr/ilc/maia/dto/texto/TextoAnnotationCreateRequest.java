package it.cnr.ilc.maia.dto.texto;

import java.util.Map;
import lombok.Getter;

/**
 *
 * @author oakgen
 */
@Getter
public class TextoAnnotationCreateRequest {

    private final Map<String, Object> resource;
    private final Map<String, Object> layer;
    private final Integer start;
    private final Integer end;

    public TextoAnnotationCreateRequest(AnnotationMultipleRequest request, AnnotationOffset offset) {
        this.resource = Map.of("id", offset.getResourceId());
        this.layer = Map.of("id", request.getLayerId());
        this.start = offset.getStart();
        this.end = offset.getEnd();
    }

}
