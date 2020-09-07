package com.stacksimplify.restservices.hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    private ResourceBundleMessageSource messageSource;

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

    @GetMapping("/hello-int")
    public String getMessageInI18NFormat(@RequestHeader(name = "Accept-Language", required = false) String locale){
    return messageSource.getMessage("label.hello",null, new Locale(locale));
}
    @GetMapping("/hello-int2")
    public String getMessageInI18N2(){
        return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
    }
}