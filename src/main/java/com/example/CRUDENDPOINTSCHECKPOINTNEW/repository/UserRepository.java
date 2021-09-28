package com.example.CRUDENDPOINTSCHECKPOINTNEW.repository;

import com.example.CRUDENDPOINTSCHECKPOINTNEW.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
