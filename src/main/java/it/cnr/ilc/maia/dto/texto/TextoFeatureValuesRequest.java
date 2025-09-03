package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author oakgen
 */
@Getter
public class TextoFeatureValuesRequest {

    private final List<Long> resources;
    private final String query;
    private final Long feature;

    public TextoFeatureValuesRequest(SearchFilterValuesRequest maiaRequest, Long feature) {
        this.resources = maiaRequest.getResources();
        StringBuilder builder = new StringBuilder();
        String value = maiaRequest.getFilters().getSearchValue();
        value = value.replaceAll("'", "''");
        String operand = "=";
        if (value.matches(".*[*?].*")) {
            operand = "like";
            value = value.replaceAll("\\*", "%").replaceAll("\\?", "_");
        }
        if ("form".equals(maiaRequest.getFilters().getSearchMode())) {
            builder.append("value ").append(operand).append(" '").append(value).append("'");
        } else if ("lemma".equals(maiaRequest.getFilters().getSearchMode())) {
            builder.append("lemma ").append(operand).append(" '").append(value).append("'");
        } else {
            throw new RuntimeException("searchMode unknown");
        }
        query = builder.toString();
        this.feature = feature;
    }

}
