package com.stacksimplify.restservices.controller;

import com.stacksimplify.restservices.dtos.UserDtoV1;
import com.stacksimplify.restservices.dtos.UserDtoV2;
import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/versioning/params/users")
public class UserRequestParameterVersioningController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}",params = "version=1")
    public UserDtoV1 getUserByIdV1(@PathVariable("id") Long id) throws UserNotFoundException {
        Optional<User> userOptional=userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User is not found, please enter valid user");
        }
        User user=userOptional.get();
        return modelMapper.map(user, UserDtoV1.class);
    }
    @GetMapping(value = "/{id}",params="version=2")
    public UserDtoV2 getUserByIdV2(@PathVariable("id") Long id) throws UserNotFoundException {
        Optional<User> userOptional = userService.getUserById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User is not found. Please enter valid user");
        }
        User user=userOptional.get();
        return modelMapper.map(user,UserDtoV2.class);

    }
}
