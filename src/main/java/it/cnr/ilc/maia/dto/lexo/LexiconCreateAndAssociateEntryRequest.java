package it.cnr.ilc.maia.dto.lexo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LexiconCreateAndAssociateEntryRequest {

    @NonNull
    private String lang;

    @NonNull
    private String label;

    @NonNull
    private String pos;

    @NonNull
    private String[] type;

    @NonNull
    private String dictionaryEntryId;

    @NonNull
    private Integer position;

}
