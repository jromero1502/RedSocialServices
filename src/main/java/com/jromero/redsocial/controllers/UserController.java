package com.jromero.redsocial.controllers;

import com.jromero.redsocial.models.UserModel;
import com.jromero.redsocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping()
    public Iterable<UserModel> getUsers() {
        return repository.findAll();
    }

    /*
    @GetMapping(path = "/test")
    public String testVoid() {
        return "Holaa! Soy un test UwU";
    }

    */
}
