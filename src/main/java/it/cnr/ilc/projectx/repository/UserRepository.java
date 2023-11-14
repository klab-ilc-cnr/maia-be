package it.cnr.ilc.projectx.repository;

import it.cnr.ilc.projectx.model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(@NotBlank String email);

    Optional<User> findByUsername(@NotBlank String username);

    Optional<User> findByUsernameAndPassword(@NotBlank String username, @NotBlank String password);
}
