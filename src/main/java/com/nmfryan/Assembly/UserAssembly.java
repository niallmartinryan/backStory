package com.nmfryan.Assembly;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.nmfryan.Controller.UserController;
import com.nmfryan.Entities.User;
import org.springframework.hateoas.EntityModel;
        import org.springframework.hateoas.server.RepresentationModelAssembler;
        import org.springframework.stereotype.Component;

@Component
public class UserAssembly implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User User) {

        return EntityModel.of(User,
                linkTo(methodOn(UserController.class).one(User.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("Users"));
    }
}