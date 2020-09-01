package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    // Autowired the user repository
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Create user method
    public User createUsers(User user){
        return userRepository.save(user);
    }
    // Get user by id
    public Optional<User> getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    //Update user By Id
    public User updateUser(Long id, User user){
        user.setId(id);
        return userRepository.save(user);
    }

    //Delete Users ById
    public String deleteUserById(Long id){
        if(userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
        return "User has been deleted";
    }

    //FindByUser Username
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
