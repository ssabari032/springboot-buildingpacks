package com.stacksimplify.restservices.controller;

import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    //Autowired the UserServices;
    @Autowired
    private UserService userService;

    @GetMapping ("/Users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //Create User
    //@RequestBody Annotation
    //@PostMapping Annotation
    @PostMapping("/Users")
    public User createUser(@RequestBody User user){
        return userService.createUsers(user);
    }

    //Get user by id
    // @PathVariable

    public Optional<User> getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    //update user ById
    @PutMapping("/Users/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUser(id,user);
    }

    //Delete user By Id
    @DeleteMapping("/Users/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        return userService.deleteUserById(id);
    }

    //find by username
    @GetMapping("/Users/ByUsername/{username}")
    public User findByUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }
}
