package com.example.CRUDENDPOINTSCHECKPOINTNEW.model;

import com.example.CRUDENDPOINTSCHECKPOINTNEW.Views;
import com.example.CRUDENDPOINTSCHECKPOINTNEW.Views.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Service;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.LimitedView.class)
    private long id;
    @JsonView(Views.LimitedView.class)
    private String email;
    @JsonView(Views.DetailedView.class)
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
