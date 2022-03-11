package it.cnr.ilc.projectx.model;

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
public class User {

    public static final String TABLE_NAME = "users";
    public static final String GENERATOR_NAME = TABLE_NAME + "_generator";
    public static final String SEQUENCE_NAME = TABLE_NAME + "_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

