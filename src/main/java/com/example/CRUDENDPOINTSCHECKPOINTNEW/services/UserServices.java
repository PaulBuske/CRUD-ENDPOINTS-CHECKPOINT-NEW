package com.example.CRUDENDPOINTSCHECKPOINTNEW.services;

import com.example.CRUDENDPOINTSCHECKPOINTNEW.model.User;
import com.example.CRUDENDPOINTSCHECKPOINTNEW.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServices {

    UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkForNull(long id, User user) {
        if (user.getEmail() == null) {
            user.setEmail(this.userRepository.findById(id).get().getEmail());
        }
        if (user.getPassword() == null) {
            user.setPassword(this.userRepository.findById(id).get().getPassword());
        }
        user.setId(id);
        return user;
    }

    public String deleteIDAndReturnRemainingTableSize(long id) {
        this.userRepository.deleteById(id);
        int count = 0;
        Iterable<User> listOfUsers = this.userRepository.findAll();

        for (Object i : listOfUsers) {
            count++;
        }
        return "{\n\"count\" : " + count + "\n}";
    }

    public Object checkPasswordAgainstEmail(User user) {
        HashMap<String, Object> imLazy = new HashMap<>();

        if (this.userRepository.existsAllByEmail(user.getEmail()).equals(false))
            return "No user found by that email";
        else if(this.userRepository.existsAllByEmail(user.getEmail()).equals(true)){
            if( this.userRepository.findByEmail(user.getEmail()).getPassword().equals(user.getPassword())){
                imLazy.put("authenticated", true);
                imLazy.put("User", this.userRepository.findByEmail(user.getEmail()));
                return imLazy;
            } else {imLazy.put("authenticated", false);}
        }
        return imLazy;
    }
}
