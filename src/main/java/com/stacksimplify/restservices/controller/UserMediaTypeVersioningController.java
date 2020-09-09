package com.stacksimplify.restservices.controller;

import com.stacksimplify.restservices.dtos.UserDtoV1;
import com.stacksimplify.restservices.dtos.UserDtoV2;
import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/versioning/mediatype/users")
public class UserMediaTypeVersioningController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}", produces="application/vnd.stacksimplify.app-v1+json")
    public UserDtoV1 getUserByIdV1(@PathVariable("id") Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User is not found, please enter valid user");
        }
        User user = userOptional.get();
        return modelMapper.map(user, UserDtoV1.class);
    }

    @GetMapping(value = "/{id}", produces="application/vnd.stacksimplify.app-v2+json")
    public UserDtoV2 getUserByIdV2(@PathVariable("id") Long id) throws UserNotFoundException {
        Optional<User> userOptional = userService.getUserById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User is not found. Please enter valid user");
        }
        User user = userOptional.get();
        return modelMapper.map(user, UserDtoV2.class);
    }
}