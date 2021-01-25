package com.example.demo.servers;

import org.springframework.stereotype.Component;

@Component
public class Serverimpl implements Server{


    @Override
    public void f() {
        System.out.println("fffffff");
    }
}
