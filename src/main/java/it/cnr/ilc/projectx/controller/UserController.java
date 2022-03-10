package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description of UserController
 * <p>
 * Created at 08/03/2022 15:25
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/users")
public class UserController {

    @NonNull
    private final UserRepository userRepository;

    @GetMapping
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
}
