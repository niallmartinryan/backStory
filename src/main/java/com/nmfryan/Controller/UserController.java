package com.nmfryan.Controller;



import java.util.List;
import java.util.UUID;

import com.nmfryan.Entities.User;
import com.nmfryan.Exceptions.UserNotFoundException;
import com.nmfryan.Repositories.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/Users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/Users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/Users/{id}")
    User one(@PathVariable UUID id) {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/Users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable UUID id) {

        return repository.findById(id)
                .map(User -> {
                    User.setFirstName(newUser.getFirstName());
                    User.setSecondName(newUser.getSecondName());
                    User.setEmail(newUser.getEmail());
                    return repository.save(User);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/Users/{id}")
    void deleteUser(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}