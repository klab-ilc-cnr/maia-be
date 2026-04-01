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
    private final Long featureExtraId;

    public TextoAisRequest(AisRequest maiaRequest, Long featureId, Long featureExtraId) {
        this.resources = maiaRequest.getResources();
        this.featureId = featureId;
        this.value = maiaRequest.getFilters().getSearchValue();
        this.width = maiaRequest.getFilters().getContextLength();
        this.featureExtraId = featureExtraId;
    }

}
