package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author oakgen
 */
@Getter
@Setter
public class WordAnnotationsRequest {

    private List<Long> layers;
    private Integer start;
    private Integer end;

}
