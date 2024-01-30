package it.cnr.ilc.maia;

import it.cnr.ilc.maia.model.Role;
import it.cnr.ilc.maia.model.User;
import it.cnr.ilc.maia.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;
import static java.util.TimeZone.getTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        try (ConfigurableApplicationContext context = SpringApplication.run(Application.class, args)) {
            int port = context.getEnvironment().getRequiredProperty("server.port-shutdown", Number.class).intValue();
            try (ServerSocket server = new ServerSocket(port)) {
                boolean shutdown = false;
                while (!shutdown) {
                    try (Socket socket = server.accept()) {
                        if (socket.getInetAddress().isAnyLocalAddress() || socket.getInetAddress().isLoopbackAddress()) {
                            shutdown = true;
                        }
                    }
                }
            }
        }
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
