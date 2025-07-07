package it.cnr.ilc.maia.dto.lexo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PosTraitsResponse {

    @NonNull
    private String pos;

    @NonNull
    private List<String> traits;

    public PosTraitsResponse(List<Map<String, String>> list) {
        traits = new ArrayList<>();
        String value;
        for (Map<String, String> map : list) {
            value = map.get("value");
            if (map.get("trait").equals("partOfSpeech")) {
                pos = PosTraitUtil.getPos(value);
            } else {
                traits.add(PosTraitUtil.getTrait(value));
            }
        }
    }

}
