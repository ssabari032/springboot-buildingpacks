package com.stacksimplify.restservices.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.entity.Views;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/jsonviews/users")
public class UserJsonViewController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/external/{id}")
    @JsonView(Views.External.class)
    public Optional<User> getByUserIdExternal(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> userOptional= userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("user not found, please provide the valid user");
        }
        return userService.getUserById(id);
    }

    @GetMapping("/internal/{id}")
    @JsonView(Views.Internal.class)
    public Optional<User> getByUserIdInternal(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> userOptional= userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("user not found, please provide the valid user");
        }
        return userService.getUserById(id);
    }
}

