package com.stacksimplify.restservices.controller;

import com.stacksimplify.restservices.entity.Order;
import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class OrderHateoasController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userid}/orders")
     public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
         Optional<User> useroptional = userRepository.findById(userid);
         if(!useroptional.isPresent()){
             throw new UserNotFoundException("User is not found, please provide a  valid user");
         }
         List<Order> allorders= useroptional.get().getOrders();
         CollectionModel<Order> finalResources= new CollectionModel<>(allorders);
         return finalResources;
     }
}
