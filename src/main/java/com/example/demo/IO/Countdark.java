package com.example.demo.IO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Countdark {
    public static void main(String[] args) {

        ThreadLocal<String> key = new ThreadLocal();


        List<String> name = new ArrayList<>();
        LinkedHashMap m = new LinkedHashMap(8,0.75f,true);
        name.stream().distinct();
        ExecutorService es = Executors.newCachedThreadPool();
//        Executor a = new ThreadPoolExecutor();

        for(String s : name){
            es.submit(new MyThread(s));
        }

    }
    public int test_exo () {
     //   throw new NumberFormatException();
        throw new IndexOutOfBoundsException();


    }
}
