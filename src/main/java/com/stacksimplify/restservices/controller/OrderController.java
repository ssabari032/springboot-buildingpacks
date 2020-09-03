package com.stacksimplify.restservices.controller;

import com.stacksimplify.restservices.entity.Order;
import com.stacksimplify.restservices.entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;
import io.netty.handler.codec.http2.Http2Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/Users")
public class OrderController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
  //Get all the orders for a user

    @GetMapping("/{userId}/orders")
    public List<Order> getAllOrders(@PathVariable("userId") Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User is not found");
        }
        return userOptional.get().getOrders();
    }

    //Create order
    @PostMapping("/{userId}/orders")
    public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("User is not found, please give correct user id or create the user firest");
        }
        User user = userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);
    }

    //Get Order By OrderID
    @GetMapping("/{userId}/orders/{orderId}")
    public Order getOrderByOrderId(@PathVariable Long userId,@PathVariable Long orderId) throws UserNotFoundException {
        Optional<User> userOptional=userRepository.findById(userId);
       if(!userOptional.isPresent()){
           throw new UserNotFoundException("User is not found");
       }
       Optional<Order> orderOptional=orderRepository.findById(orderId);
       return orderOptional.get();
    }

}
