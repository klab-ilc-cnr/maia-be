package it.cnr.ilc.maia.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

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
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9@._-]{4,20}$")
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", schema = "public", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    private boolean active;

    @Column(columnDefinition = "json")
    @Convert(converter = UserAttributeConverter.class)
    private Attribute attributes;

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", email='" + email + '\''
                + ", roles=" + roles
                + ", active=" + active
                + '}';
    }

    @Converter
    private static class UserAttributeConverter implements AttributeConverter<Attribute, String> {

        private static final ObjectMapper MAPPER = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(Attribute x) {
            try {
                return MAPPER.writeValueAsString(x);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public Attribute convertToEntityAttribute(String y) {
            try {
                return MAPPER.readValue(y, Attribute.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
