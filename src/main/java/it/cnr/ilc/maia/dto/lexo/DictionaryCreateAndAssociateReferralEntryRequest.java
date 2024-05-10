package it.cnr.ilc.maia.dto.lexo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DictionaryCreateAndAssociateReferralEntryRequest {

    private String lang;
    private String referralEntryLabel;
    private String fullEntryLabel;
}
