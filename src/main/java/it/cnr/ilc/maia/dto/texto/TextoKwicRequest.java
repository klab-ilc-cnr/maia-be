package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author oakgen
 */
@Getter
public class TextoKwicRequest {

    private final List<Long> resources;
    private final Long layer;
    private final String query;
    private final Integer width;

    public TextoKwicRequest(KwicRequest maiaRequest) {
        resources = maiaRequest.getResources();
        layer = maiaRequest.getLayerId();
        StringBuilder builder = new StringBuilder();
        String value = maiaRequest.getFilters().getSearchValue();
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
        width = maiaRequest.getFilters().getContextLength();
    }

}
