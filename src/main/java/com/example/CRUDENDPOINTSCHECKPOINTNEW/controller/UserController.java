package com.example.CRUDENDPOINTSCHECKPOINTNEW.controller;

import com.example.CRUDENDPOINTSCHECKPOINTNEW.Views;
import com.example.CRUDENDPOINTSCHECKPOINTNEW.model.User;
import com.example.CRUDENDPOINTSCHECKPOINTNEW.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository repository;



    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    @JsonView(Views.LimitedView.class)
    public Object getAllFromDatabase(){
        return this.repository.findAll();
    }

    @PostMapping("")
    @JsonView(Views.LimitedView.class)
    public Object addMultipleUsers(@RequestBody List<User> users){
        return this.repository.saveAll(users);
    }

    //TODO create a get that returns by user id {id} with a limited view

    //TODO create a patch that edits existing rows and displays in a limited view

    //TODO create a delete request that deletes by user id {id} and returns the number of users left in the database

    //TODO create a endpoint that takes a post request, checks that he email and password match, if it matches return
    //TODO a custom message, if it doesn't mathc it returns a custom message


}
