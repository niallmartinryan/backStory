package com.nmfryan.Controller;



import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.nmfryan.Assembly.UserAssembly;
import com.nmfryan.Entities.User;
import com.nmfryan.Exceptions.UserNotFoundException;
import com.nmfryan.Repositories.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private final UserRepository repository;
    private final UserAssembly assembler;

    UserController(UserRepository repository, UserAssembly assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/Users")
    public CollectionModel<EntityModel<User>> all() {

        List<EntityModel<User>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/Users")
    public User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/Users/{id}")
    public EntityModel<User> one(@PathVariable UUID id) {

        User user = repository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));

//        return EntityModel.of(User, //
//                linkTo(methodOn(UserController.class).one(id)).withSelfRel(),
//                linkTo(methodOn(UserController.class).all()).withRel("Users"));
        return assembler.toModel(user);
    }

    @PutMapping("/Users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable UUID id) {

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
    public void deleteUser(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}