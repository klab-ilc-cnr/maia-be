package it.cnr.ilc.maia.dto.texto;

import java.util.Map;
import lombok.Getter;

@Getter
public class AicData {

    private final Integer index;
    private final Integer rowIndex;
    private final Long textId;
    private final String text;
    private final String reference;
    private final String leftContext;
    private final String annotation;
    private final String rightContext;
    private final Integer annotationOffset;

    public AicData(Integer id, Map<String, Object> map) {
        index = id;
        rowIndex = (Integer) map.get("row_left_number");
        textId = ((Number) map.get("resource_id")).longValue();
        text = (String) map.get("resource_name");
        reference = (String) map.get("section_left_index");
        leftContext = (String) map.get("left");
        annotation = (String) map.get("annotation");
        rightContext = (String) map.get("right");
        annotationOffset = ((Number) map.get("start")).intValue();
    }

}
