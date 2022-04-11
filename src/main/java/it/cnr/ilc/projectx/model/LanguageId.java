package it.cnr.ilc.projectx.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class LanguageId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "language_id")
    private Long languageId;
}
