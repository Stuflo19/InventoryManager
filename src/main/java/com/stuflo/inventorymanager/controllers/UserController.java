package com.stuflo.inventorymanager.controllers;

import com.stuflo.inventorymanager.models.User;
import com.stuflo.inventorymanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {this.userRepo = userRepo;}

    @GetMapping(produces = { "application/hal+json" })
    public ResponseEntity<User> User() {
        var users = userRepo.findAll();
        var firstUser = StreamSupport.stream(users.spliterator(), false).findFirst();

        if(firstUser.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        var user = firstUser.get();

        var link = linkTo(UserController.class).slash(user.getId()).withSelfRel();
        user.add(link);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
