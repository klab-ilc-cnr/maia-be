package it.cnr.ilc.maia.dto.texto;

import java.util.ArrayList;
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
    private final List<KwicFeatureFiler> features;

    public static record KwicFeatureFiler(Long feature, String[] values) {

    }

    public static record FeatureIds(Long semanticsFeatureId, Long posFeatureId, Long namedEntityFeatureId) {

    }

    public TextoKwicRequest(KwicRequest maiaRequest) {
        resources = maiaRequest.getResources();
        layer = maiaRequest.getLayerId();
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
        width = maiaRequest.getFilters().getContextLength();
        features = null;
    }

    public TextoKwicRequest(SearchRequest maiaRequest, FeatureIds featureIds) {
        resources = maiaRequest.getResources();
        layer = null;
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
        width = maiaRequest.getFilters().getContextLength();
        features = new ArrayList<>();
        if (maiaRequest.getFilters().getSemantics() != null && maiaRequest.getFilters().getSemantics().length > 0) {
            features.add(new KwicFeatureFiler(featureIds.semanticsFeatureId, maiaRequest.getFilters().getSemantics()));
        }
        if (maiaRequest.getFilters().getPoss() != null && maiaRequest.getFilters().getPoss().length > 0) {
            features.add(new KwicFeatureFiler(featureIds.posFeatureId, maiaRequest.getFilters().getPoss()));
        }
        if (maiaRequest.getFilters().getNamedEntities() != null && maiaRequest.getFilters().getNamedEntities().length > 0) {
            features.add(new KwicFeatureFiler(featureIds.namedEntityFeatureId, maiaRequest.getFilters().getNamedEntities()));
        }
    }

    /*
{
 "resources":[123],
 "start":0,
 "end":50,
 "filters":{
  "searchValue":"allegrezza",
  "searchMode":"lemma",
  "semantics":["http://lexica/mylexicon#LexO_2024-05-3013_26_22_095","http://lexica/mylexicon#LexO_2024-05-3013_25_58_697"],
  "poss":["NOUN"],
  "contextLength":10,
  "index":1,
  "text":"Decameron",
  "reference": "I.Intro",
  "leftContext": "festa",
  "kwic": "allegrezza",
  "rightContext": "piacere"
 }
}
     */
}
