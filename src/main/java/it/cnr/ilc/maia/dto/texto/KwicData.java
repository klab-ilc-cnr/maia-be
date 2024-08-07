package it.cnr.ilc.maia.dto.texto;

import java.util.Map;
import lombok.Getter;

@Getter
public class KwicData {

    private final Integer index;
    private final Integer rowIndex;
    private final Long textId;
    private final String text;
    private final String reference;
    private final String leftContext;
    private final String kwic;
    private final String rightContext;
    private final Integer kwicOffset;

    public KwicData(Integer id, Map<String, Object> map) {
        index = id;
        rowIndex = (Integer) map.get("row_number");
        textId = ((Number) map.get("resource_id")).longValue();
        text = (String) map.get("resource_name");
        reference = (String) map.get("section_index");
        leftContext = (String) map.get("left");
        kwic = (String) map.get("token");
        rightContext = (String) map.get("right");
        kwicOffset = ((Number) map.get("start")).intValue();
    }

}
