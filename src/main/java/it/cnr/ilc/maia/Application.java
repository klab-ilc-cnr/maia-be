package it.cnr.ilc.maia;

import it.cnr.ilc.maia.model.Role;
import it.cnr.ilc.maia.model.User;
import it.cnr.ilc.maia.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;
import static java.util.TimeZone.getTimeZone;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        TimeZone.setDefault(getTimeZone("UTC"));
        if (userRepository.findAll().isEmpty()) {
            User user = new User();
            user.setName("User");
            user.setSurname("Administrator");
            user.setUsername("admin");
            user.setPassword("password");
            user.setEmail("maia@ilc.cnr.it");
            user.setRoles(Collections.singleton(Role.ADMINISTRATOR));
            user.setActive(true);
            userRepository.save(user);
        }
    }
}
