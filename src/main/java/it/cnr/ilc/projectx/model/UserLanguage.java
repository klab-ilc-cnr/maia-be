package it.cnr.ilc.projectx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_languages", schema = "public")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public class UserLanguage implements Serializable {

    @Id
    @Column
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @NonNull
    private Long code;

    @NonNull
    private String name;
}
