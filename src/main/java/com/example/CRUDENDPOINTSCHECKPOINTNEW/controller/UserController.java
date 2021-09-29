package com.example.CRUDENDPOINTSCHECKPOINTNEW.controller;

import com.example.CRUDENDPOINTSCHECKPOINTNEW.Views;
import com.example.CRUDENDPOINTSCHECKPOINTNEW.model.User;
import com.example.CRUDENDPOINTSCHECKPOINTNEW.repository.UserRepository;
import com.example.CRUDENDPOINTSCHECKPOINTNEW.services.UserServices;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository repository;
    UserServices userServices;


    public UserController(UserRepository repository, UserServices userServices) {
        this.repository = repository;
        this.userServices = userServices;
    }

    @GetMapping("")
    @JsonView(Views.LimitedView.class)
    public Object getAllFromDatabase() {
        return this.repository.findAll();
    }

    @PostMapping("")
    @JsonView(Views.LimitedView.class)
    public Object addMultipleUsers(@RequestBody List<User> users) {
        return this.repository.saveAll(users);
    }


    @GetMapping("{id}")
    @JsonView(Views.LimitedView.class)
    public Object getUserBySpecifiedIDPassedAsAVariable(@PathVariable long id) {
        if (this.repository.findById(id).isEmpty()) {
            return id + " is not a valid database entry, please contact your system administrator.";
        }
        return this.repository.findById(id);
    }

    @PatchMapping("{id}")
    @JsonView(Views.DetailedView.class)
    public Object patchUserByID(@PathVariable long id, @RequestBody User user) {
        if (this.repository.findById(id).isEmpty()) {
            return id + " is not a valid databse entry, please contact your local administrator.";
        } else {
            return this.repository.save(userServices.checkForNull(id, user));
        }
    }


    @DeleteMapping("{id}")
    @JsonView(Views.LimitedView.class)
    public Object deleteRowByIdThenReturnTotalOfTableRowsBackToTheUser(@PathVariable long id){
        if (this.repository.findById(id).isEmpty()){
            return id + " is not a valid databse entry, please contact your local administrator.";
        } else
           return userServices.deleteIDAndReturnRemainingTableSize(id);
    }

    @PostMapping("/authenticate")
    @JsonView(Views.DetailedView.class)
    public Object checkForUserPasswordAndAuthenticate(@RequestBody User user){
       return userServices.checkPasswordAgainstEmail(user);
    }

    @DeleteMapping("/deleteall")
    public String deleteAllUsers(){
       this.repository.deleteAll();
       return "Database cleared of all instances.";
    }



}
