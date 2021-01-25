package com.example.demo.test;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThread{

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        ExecutorService es = Executors.newCachedThreadPool();
        for(String s : names){
            es.submit(new MyThread1(s));
        }
    }




}
