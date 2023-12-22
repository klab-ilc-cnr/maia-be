package it.cnr.ilc.maia.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Attribute implements Serializable {

    @NonNull
    List<Language> lang;
}
