package it.cnr.ilc.maia.dto.texto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class KwicResponse {

    private final Integer count;
    private final List<KwicData> data;

    public KwicResponse(TextoKwicResponse response) {
        count = response.getCount();
        data = new ArrayList<>(response.getData().size());
        for (Map<String, Object> map : response.getData()) {
            data.add(new KwicData(map));
        }
    }

}
