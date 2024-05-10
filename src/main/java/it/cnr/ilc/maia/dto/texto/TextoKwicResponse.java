package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TextoKwicResponse {

    private Integer count;
    private Integer start;
    private Integer end;
    private List<Map<String, Object>> data;

}
