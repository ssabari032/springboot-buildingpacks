package com.stacksimplify.restservices.hello;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestControllergit
public class HelloWorldController {
    @GetMapping("/helloworld")
    public String helloworld(){
        return "Hello world!!!";
    }
    @RequestMapping("/test")
    public String test(){
        return "TestPage!!!";
    }


    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean(){
        return new UserDetails("Sabari","Nathan","Chennai");
    }
}
