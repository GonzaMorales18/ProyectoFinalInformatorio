package com.finalproyect.informatorio.controller;

import javax.validation.Valid;

import com.finalproyect.informatorio.entity.User;
import com.finalproyect.informatorio.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<User> getAll(){
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> set(@RequestBody @Valid User user){
        return new ResponseEntity(userRepository.save(user), HttpStatus.CREATED);
    }

}