package it.cnr.ilc.projectx.model;

import it.cnr.ilc.projectx.roles.Roles;
import lombok.*;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    private Roles role;

    private boolean active;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
//                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", created=" + getCreated() +
                ", updated=" + getUpdated() +
                '}';
    }
}

