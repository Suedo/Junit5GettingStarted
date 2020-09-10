package com.webms.demo.controllers;

import com.webms.demo.models.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @GetMapping(path = "/hello/{name}")
    public Hello hello(@PathVariable String name){
        return new Hello("Hello " + name);
    }
}
