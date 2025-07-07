package it.cnr.ilc.maia.dto.texto;

import lombok.Getter;

@Getter
public class Offset {

    private final Integer start;
    private final Integer end;

    public Offset(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }
}
