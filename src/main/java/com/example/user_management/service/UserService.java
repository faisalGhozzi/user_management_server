package com.example.user_management.service;

import com.example.user_management.exception.AlreadyExistsException;
import com.example.user_management.exception.ResourceNotFoundException;
import com.example.user_management.model.User;
import com.example.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        // Returns all of the users present in the database
        return userRepository.findAll();
    }

    public ResponseEntity<User> getUser(Integer id) throws ResourceNotFoundException {
        // Return the user with the given ID
        // if the ID doesn't exist in the database a ResourceNotFoundException will be thrown
        // else we're going to return the user
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee with id ["+ id +"] not found")
        );
        return ResponseEntity.ok().body(user);
    }

    public void addUser(User user) throws AlreadyExistsException {
        // Adds a user to the database
        // Check if the email is present in the database
        // if the email exists an AlreadyExistsException will be thrown
        // else we're going to save the new entry in our database
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new AlreadyExistsException("Email exists");
        }
        user.setCreatedAt(LocalDate.now());
        userRepository.save(user);
    }

    public ResponseEntity<User> updateUser(Integer id, User userDetails) throws ResourceNotFoundException, AlreadyExistsException {
        // Update the requested user's information
        // Check if the user's id exists in the database, if not a ResourceNotFoundException will be thrown
        // if the user exists, check the email address if it exists in the database an AlreadyExistsException will be thrown
        // else persist the new information
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [" + id + "] does not exist"
                ));
        Optional<User> userOptional = userRepository.findUserByEmail(userDetails.getEmail());
        if(userOptional.isPresent()){
            if(userOptional.get().getId() != id){
                throw new AlreadyExistsException("this email is already linked to another user");
            }
        }
        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setBirthday(userDetails.getBirthday());
        user.setCreatedAt(user.getCreatedAt());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }



}
