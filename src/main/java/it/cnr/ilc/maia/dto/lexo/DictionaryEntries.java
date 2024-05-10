package it.cnr.ilc.maia.dto.lexo;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DictionaryEntries {

    private Integer totalHits;
    private List<DictionaryEntry> list;
}
