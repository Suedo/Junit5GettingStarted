package com.webms.demo.controllers;

import com.webms.demo.dao.UserDao;
import com.webms.demo.models.User;
import com.webms.demo.services.UserService;
import com.webms.demo.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    
    @Autowired
    UserService service;
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
    
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        User user = service.findOne(id).orElseThrow(() -> new UserNotFoundException("No used found with specified id"));
        return user;
    }
    
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = service.addUser(user);
        URI newUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(newUserUri).build();
        // uri will be present in Header 'Location' section
    }
}
