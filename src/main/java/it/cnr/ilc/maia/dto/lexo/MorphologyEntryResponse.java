package it.cnr.ilc.maia.dto.lexo;

import static it.cnr.ilc.maia.dto.lexo.PosTraitUtil.getLabel;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MorphologyEntryResponse {

    private String propertyId;
    private String propertyLabel;
    private String propertyDescription;
    private List<PropertyValue> propertyValues;

    public MorphologyEntryResponse(MorphologyEntry entry, boolean filter) {
        this.propertyId = entry.getPropertyId();
        this.propertyLabel = entry.getPropertyLabel();
        this.propertyDescription = entry.getPropertyDescription();
        if (filter && propertyId.endsWith("partOfSpeech")) {
            this.propertyValues = entry.getPropertyValues().stream().filter(v -> PosTraitUtil.containsPos(getLabel(v.getValueId()))).toList();
        } else {
            this.propertyValues = entry.getPropertyValues();
        }
    }
    
}
