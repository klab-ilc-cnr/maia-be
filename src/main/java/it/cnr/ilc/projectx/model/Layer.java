package it.cnr.ilc.projectx.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "layers")
public class Layer extends TracedEntity {

    public static final String TABLE_NAME = "layers";
    public static final String GENERATOR_NAME = TABLE_NAME + "_generator";
    public static final String SEQUENCE_NAME = TABLE_NAME + "_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @NonNull
    private String name;

    private String description;

    private String color;

    @OneToMany(mappedBy = "layer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feature> features;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + color + '\'' +
                ", layout='" + description + '\'' +
                ", created=" + super.getCreated() +
                ", updated=" + super.getUpdated() +
                ", createdBy=" + super.getCreatedBy() +
                ", updatedBy=" + super.getUpdatedBy() +
                '}';
    }
}

