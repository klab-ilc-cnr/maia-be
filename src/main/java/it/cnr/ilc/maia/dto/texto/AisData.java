package it.cnr.ilc.maia.dto.texto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class AisData {

    private final Integer index;
    private final Integer rowIndex;
    private final Long textId;
    private final String text;
    private final String reference;
    private final String section;
    private final List<Offset> offsets = new ArrayList<>();

    public AisData(Integer id, Map<String, Object> map) {
        index = id;
        rowIndex = (Integer) map.get("row_left_number");
        textId = ((Number) map.get("resource_id")).longValue();
        text = (String) map.get("resource_name");
        reference = (String) map.get("section_left_index");
        section = (String) map.get("section");
        Integer start = ((Number) map.get("start")).intValue() - ((Number) map.get("left_start")).intValue();
        Integer end = ((Number) map.get("end")).intValue() - ((Number) map.get("left_start")).intValue();
        offsets.add(new Offset(start, end));
    }

}
