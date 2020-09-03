package com.stacksimplify.restservices.controller;

import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/Users")
public class UserController {
    //Autowired the UserServices;
    @Autowired
    private UserService userService;

    @GetMapping
    //@RequestMapping(value="/Users",method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //Create User
    //@RequestBody Annotation
    //@PostMapping Annotation
    @PostMapping
    //@RequestMapping(value="/Users",method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder){
        try {
            userService.createUsers(user);
            HttpHeaders headers= new HttpHeaders();
            headers.setLocation(builder.path("/Users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<>(headers,HttpStatus.CREATED);
        }
        catch(UserExistsException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    //Get user by id
    // @PathVariable
    @GetMapping("/{id}")
    //@RequestMapping(value="/Users/{id}",method = RequestMethod.GET)
    public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        }catch(UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
        }
    }

    //update user ById
    @PutMapping("/{id}")
    //@RequestMapping(value="/Users/{id}",method = RequestMethod.PUT)
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        try {
            return userService.updateUser(id, user);
        }
        catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    //Delete user By Id
    @DeleteMapping("/{id}")
    //@RequestMapping(value="/Users/{id}",method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable("id") Long id){
        return userService.deleteUserById(id);
    }

    //find by username
    //Get Username with ExceptionHandler
    @GetMapping("/ByUsername/{username}")
    //@RequestMapping(value="/Users",method = RequestMethod.GET)
    public User findByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
            User user = userService.findByUsername(username);
            if(user == null){
                throw new UserNameNotFoundException("Username: '"+username+"' not found in User Repository");
        }
            return user;
    }
}
