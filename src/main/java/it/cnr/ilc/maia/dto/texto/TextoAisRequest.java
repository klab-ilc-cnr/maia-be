package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author oakgen
 */
@Getter
public class TextoAisRequest {

    private final List<Long> resources;
    private final Long featureId;
    private final String value;
    private final Integer width;

    public TextoAisRequest(AisRequest maiaRequest, Long semanticsFeatureId) {
        resources = maiaRequest.getResources();
        featureId = semanticsFeatureId;
        value = maiaRequest.getFilters().getSearchValue();
        width = maiaRequest.getFilters().getContextLength();
    }

}
