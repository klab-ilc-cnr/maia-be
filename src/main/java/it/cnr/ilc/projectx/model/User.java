package it.cnr.ilc.projectx.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Set;

/**
 * Description of User
 * <p>
 * Created at 08/03/2022 15:10
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class User extends TracedEntity {

    public static final String TABLE_NAME = "users";
    public static final String GENERATOR_NAME = TABLE_NAME + "_generator";
    public static final String SEQUENCE_NAME = TABLE_NAME + "_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private long id;

    @NonNull
    private String name;

    //    @NonNull
    private String surname;

    @NonNull
    private String email;

//    private String username;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", schema = "public", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    private boolean active;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Attribute attributes;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", active=" + active +
                '}';
    }
}

