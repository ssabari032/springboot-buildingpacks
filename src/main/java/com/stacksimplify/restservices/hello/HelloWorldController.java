package com.stacksimplify.restservices.hello;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldController {
    @GetMapping("/helloworld")
    public String helloworld(){
        return "Hello world!!!";
    }

    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean(){
        return new UserDetails("Sabari","Nathan","Chennai");
    }
}
