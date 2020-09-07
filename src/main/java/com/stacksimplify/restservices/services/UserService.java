package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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
    public User createUsers(User user) throws UserExistsException {
        // Existing or not user
        User existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser != null){
            throw new UserExistsException("User already exists!!");
        }
        return userRepository.save(user);
    }
    // Get user by id
    public Optional<User> getUserById(Long id) throws UserNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User is not found in User Repository");
        }
        return user;
    }

    //Update user By Id
    public User updateUser(Long id, User user) throws UserNotFoundException{
        Optional<User> Optionaluser = userRepository.findById(id);
        if(!Optionaluser.isPresent()){
            throw new UserNotFoundException("User is not found in User Repository, Provide the correct user id");
        }
        user.setUserid(id);
        return userRepository.save(user);
    }

    //Delete Users ById
    public String deleteUserById(Long id){
       // if(userRepository.findById(id).isPresent()) {
        //    userRepository.deleteById(id);
        //}
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User is not found, please provide the correct user id");
        }
        userRepository.deleteById(id);
        return "User has been deleted";
    }

    //FindByUser Username
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
