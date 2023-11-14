package it.cnr.ilc.projectx.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "features")
public class Feature extends TracedEntity {

    public static final String TABLE_NAME = "features";
    public static final String GENERATOR_NAME = TABLE_NAME + "_generator";
    public static final String SEQUENCE_NAME = TABLE_NAME + "_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @NonNull
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FeatureType type;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tagset_id", referencedColumnName = "id")
    private Tagset tagset;

    @NonNull
    @ManyToOne
    private Layer layer;

    private String description;

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", created=" + super.getCreated()
                + ", updated=" + super.getUpdated()
                + ", createdBy=" + super.getCreatedBy()
                + ", updatedBy=" + super.getUpdatedBy()
                + '}';
    }
}
