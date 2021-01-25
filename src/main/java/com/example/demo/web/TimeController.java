package com.example.demo.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TimeController {

    @RequestMapping("/time")
    public String time() throws Exception{
        return new Date().toString();
    }
}
