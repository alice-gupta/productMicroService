package com.ms1.productservicemicroservices.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class sampleController {

    @GetMapping("/hello/{name}/{times}")
    public String sayHello(@PathVariable("name") String name,
                           @PathVariable("times")int times) {
        String output="";
        for(int i=0;i<times;i++) {
            output= output + "Hello "+ name              +" "+i;
        }
        return output;
    }
    @GetMapping("/bye")
    public String sayBye() {
        return "Bye ";
    }


}
