package it.cnr.ilc.maia.dto.lexo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DictionaryAssociateEntryRequest {

    @NonNull
    private String dictionaryEntryId;

    @NonNull
    private String lexicalEntryId;

    @NonNull
    private Integer position;

}
