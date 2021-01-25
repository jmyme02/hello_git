package com.example.demo.web;

import com.example.demo.exception.MyExcepetion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() throws Exception{
        throw new MyExcepetion("");
    }

}