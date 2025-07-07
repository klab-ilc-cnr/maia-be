package it.cnr.ilc.maia.dto.lexo;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MorphologyEntry {

    private String propertyId;
    private String propertyLabel;
    private String propertyDescription;
    private List<PropertyValue> propertyValues;
}
