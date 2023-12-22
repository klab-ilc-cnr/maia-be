package it.cnr.ilc.maia.repository;

import it.cnr.ilc.maia.model.User;
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
