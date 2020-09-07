package com.stacksimplify.restservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@Validated
@RequestMapping(value="/jacksonfilter/users")
public class UserMappingJacksonController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    //Static HashSet
    @GetMapping("/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> userOptional= userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User is not found, Please provide a valid user");
        }
        Optional<User> userOptional1 = userService.getUserById(id);
        User user=userOptional1.get();
        Set<String> fields = new HashSet<String>();
        fields.add("userid");
        fields.add("username");
        fields.add("ssn");
        fields.add("orders");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
        MappingJacksonValue mapper=new MappingJacksonValue(user);
        mapper.setFilters(filterProvider);
        return mapper;
    }
    //Dynamic HashSet
    @GetMapping("params/{id}")
    public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields) throws UserNotFoundException {
        Optional<User> userOptional= userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User is not found, Please provide a valid user");
        }
        Optional<User> userOptional1 = userService.getUserById(id);
        User user=userOptional1.get();
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
        MappingJacksonValue mapper=new MappingJacksonValue(user);
        mapper.setFilters(filterProvider);
        return mapper;
    }
}
