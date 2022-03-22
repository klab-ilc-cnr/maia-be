package it.cnr.ilc.projectx.repository;

import it.cnr.ilc.projectx.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description of UserRepository
 * <p>
 * Created at 08/03/2022 15:25
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
