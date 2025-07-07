package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class KwicResponse {

    private final Integer count;
    private final List<KwicData> data;

    public KwicResponse(List<Map<String, Object>> response, Integer start, Integer end, KwicFilters filters) {
        I id = new I();
        List<KwicData> temp = response.stream()
                .map(k -> new KwicData(id.next(), k))
                .filter(k -> filter(k, filters))
                .toList();
        count = temp.size();
        data = temp.subList(start, Math.min(end, temp.size()));
    }

    private static boolean filter(KwicData data, KwicFilters filters) {
        if (filters.getIndex() != null && !data.getIndex().equals(filters.getIndex())) {
            return false;
        }
        if (filters.getText() != null && !data.getText().toLowerCase().startsWith(filters.getText().toLowerCase())) {
            return false;
        }
        if (filters.getReference() != null && !data.getReference().toLowerCase().startsWith(filters.getReference().toLowerCase())) {
            return false;
        }
        if (filters.getLeftContext() != null && !data.getLeftContext().toLowerCase().contains(filters.getLeftContext().toLowerCase())) {
            return false;
        }
        if (filters.getKwic() != null && !data.getKwic().toLowerCase().startsWith(filters.getKwic().toLowerCase())) {
            return false;
        }
        if (filters.getRightContext() != null && !data.getRightContext().toLowerCase().contains(filters.getRightContext().toLowerCase())) {
            return false;
        }
        if (filters.getAnnotated() != null && !data.getAnnotated().equals(filters.getAnnotated())) {
            return false;
        }
        return true;
    }

    private static class I {

        private int i = 0;

        private int next() {
            return i++;
        }
    }
}
