package it.cnr.ilc.maia.dto.lexo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DictionaryEntriesRequest {

    private String text;
    private String searchMode;
    private String pos;
    private String author;
    private String lang;
    private String status;
    private Integer offset;
    private Integer limit;
    
}
