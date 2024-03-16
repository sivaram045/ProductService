package com.ecomapp.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/say/{name}/{times}")
    public String sayHello(@PathVariable String name,
                           @PathVariable int times) {
        String result = "";
        
        for(int i=0; i<=times; i++) {
            result +=  name +" Saying Hello "+ i + "th time";
            result += "<br>";
        }
        
        return result;
    }
}
