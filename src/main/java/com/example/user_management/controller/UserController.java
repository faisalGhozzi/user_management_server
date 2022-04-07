package com.example.user_management.controller;

import java.util.List;

import com.example.user_management.exception.AlreadyExistsException;
import com.example.user_management.exception.ResourceNotFoundException;
import com.example.user_management.model.User;

import com.example.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        return userService.getUser(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user) throws AlreadyExistsException {
        userService.addUser(user);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Integer id,
            @RequestBody User userDetails
    ) throws ResourceNotFoundException, AlreadyExistsException {
        return userService.updateUser(id, userDetails);
    }
}
