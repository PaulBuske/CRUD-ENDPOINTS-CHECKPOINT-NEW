package com.example.CRUDENDPOINTSCHECKPOINTNEW.repository;

import com.example.CRUDENDPOINTSCHECKPOINTNEW.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    Boolean existsAllByEmail(String email);

    User findByEmail(String email);

}
