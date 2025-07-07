package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author oakgen
 */
@Getter
public class TextoWordAnnotationsRequest {

    private final List<Long> layers;
    private final Integer start;
    private final Integer end;

    public TextoWordAnnotationsRequest(AnnotationMultipleRequest maiaRequest, AnnotationOffset offset) {
        this.layers = List.of(maiaRequest.getLayerId());
        this.start = offset.getStart();
        this.end = offset.getEnd();
    }

}
