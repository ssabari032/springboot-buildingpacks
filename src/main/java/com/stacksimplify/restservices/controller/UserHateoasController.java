package com.stacksimplify.restservices.controller;

import com.stacksimplify.restservices.entity.Order;
import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.image.ImageWatched;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public EntityModel<User> getByUserId(@PathVariable @Min(1) Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User is not found, please enter the valid user");
        }
        User user= userOptional.get();
        Long userid= user.getUserid();
        Link selfLink= WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
        user.add(selfLink);
        EntityModel<User> finalResource = new EntityModel<User>(user);
        return finalResource;
    }

    @GetMapping()
    public CollectionModel<User> getAllUsers() throws UserNotFoundException {
       List<User> allusers = userService.getAllUsers();

       for (User user: allusers){
           Long userid = user.getUserid();
           Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
           user.add(selfLink);

           CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
           Link orderLinks = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
           user.add(orderLinks);
       }
       Link selfLinkGetAllUser= WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
       CollectionModel<User> finalResource = new CollectionModel<User>(allusers,selfLinkGetAllUser);
       return finalResource;
    }

}
