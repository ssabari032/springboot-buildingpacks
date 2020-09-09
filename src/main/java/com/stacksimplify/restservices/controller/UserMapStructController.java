package com.stacksimplify.restservices.controller;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.mapper.UserMapper;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public UserMsDto getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        Optional<User> userOptional = userService.getUserById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User is not found!!");
        }
        User user= userOptional.get();
        return userMapper.UserToUserMsDto(user);
    }

    @GetMapping
    public List<UserMsDto> getAlluser(){
        List<User> users = userRepository.findAll();
        return userMapper.UsersToUserDto(users);
    }

}
