package it.cnr.ilc.projectx.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "workspaces")
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Workspace extends TracedEntity {

    public static final String TABLE_NAME = "workspaces";
    public static final String GENERATOR_NAME = TABLE_NAME + "_generator";
    public static final String SEQUENCE_NAME = TABLE_NAME + "_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @NonNull
    private String name;

    private String note;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", created=" + super.getCreated() +
                ", updated=" + super.getUpdated() +
                ", createdBy=" + super.getCreatedBy() +
                ", updatedBy=" + super.getUpdatedBy() +
                '}';
    }
}

