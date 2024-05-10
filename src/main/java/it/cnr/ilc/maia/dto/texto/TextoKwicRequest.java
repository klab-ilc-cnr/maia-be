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
    private final String query;
    private final String filter;
    private final Integer width;
    private final Integer start;
    private final Integer end;

    public TextoKwicRequest(KwicRequest maiaRequest) {
        resources = maiaRequest.getResources();
        StringBuilder builder = new StringBuilder();
        String value = maiaRequest.getFilters().getSearchValue();
        String operand = "=";
        if (value.matches(".*[*?].*")) {
            operand = "like";
            value = value.replaceAll("\\*", "%").replaceAll("\\?", "_");
        }
        if ("form".equals(maiaRequest.getFilters().getSearchMode())) {
            builder.append("token ").append(operand).append(" '").append(value).append("'");
        } else if ("lemma".equals(maiaRequest.getFilters().getSearchMode())) {
            builder.append("lemma ").append(operand).append(" '").append(value).append("'");
        } else {
            throw new RuntimeException("searchMode unknown");
        }
        query = builder.toString();
        builder = new StringBuilder();
        if (maiaRequest.getFilters().getIndex() != null) {
            builder.append("id = ").append(maiaRequest.getFilters().getIndex()).append(" and ");
        }
        if (maiaRequest.getFilters().getText() != null) {
            builder.append("resource_name like '").append(maiaRequest.getFilters().getText()).append("%' and ");
        }
        if (maiaRequest.getFilters().getReference() != null) {
            builder.append("section_index like '").append(maiaRequest.getFilters().getReference()).append("%' and ");
        }
        if (maiaRequest.getFilters().getLeftContext() != null) {
            builder.append("left_context like '%").append(maiaRequest.getFilters().getLeftContext()).append("%' and ");
        }
        if (maiaRequest.getFilters().getKwic() != null) {
            builder.append("token like '%").append(maiaRequest.getFilters().getKwic()).append("%' and ");
        }
        if (maiaRequest.getFilters().getRightContext() != null) {
            builder.append("right_context like '%").append(maiaRequest.getFilters().getRightContext()).append("%' and ");
        }
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 5);
        }
        filter = builder.toString();
        width = maiaRequest.getFilters().getContextLength();
        start = maiaRequest.getStart();
        end = maiaRequest.getEnd();
    }

}
