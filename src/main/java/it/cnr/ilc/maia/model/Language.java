package it.cnr.ilc.maia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Language implements Serializable {

    @NonNull
    private String code;

    @NonNull
    private String name;

    public Language(String name, String code) {
        this.code = code;
        this.name = name;
    }
}
