package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author oakgen
 */
@Getter
public class TextoAisRequest {

    public final Long FEATURE_ID = 20081537l;

    private final List<Long> resources;
    private final Long featureId;
    private final String value;
    private final Integer width;

    public TextoAisRequest(AisRequest maiaRequest) {
        resources = maiaRequest.getResources();
        featureId = FEATURE_ID;
        value = maiaRequest.getFilters().getSearchValue();
        width = maiaRequest.getFilters().getContextLength();
    }

}
