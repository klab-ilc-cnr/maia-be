package it.cnr.ilc.maia.dto.texto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AnnotationOffset {

    @NonNull
    private Integer index;

    @NonNull
    private Long resourceId;

    @NonNull
    private Integer start;

    @NonNull
    private Integer end;
}
