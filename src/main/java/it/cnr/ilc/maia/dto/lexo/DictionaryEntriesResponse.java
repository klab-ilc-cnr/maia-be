package it.cnr.ilc.maia.dto.lexo;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DictionaryEntriesResponse {

    public DictionaryEntriesResponse(DictionaryEntries dictionaryEntries) {
        this.totalHits = dictionaryEntries.getTotalHits();
        this.list = dictionaryEntries.getList().stream().map(e->new DictionaryEntryResponse(e)).collect(Collectors.toList());
    }

    private Integer totalHits;
    private List<DictionaryEntryResponse> list;
}
