package it.cnr.ilc.maia.dto.lexo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssociateReferralEntryRequest {

    @NonNull
    private String referralEntryId;

    @NonNull
    private String fullEntryId;

}
