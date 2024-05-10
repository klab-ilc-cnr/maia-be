package it.cnr.ilc.maia.dto.lexo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DictionaryCreateEntryRequest {

    @NonNull
    private String lang;

    @NonNull
    private String label;

}
