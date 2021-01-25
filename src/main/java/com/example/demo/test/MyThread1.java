package com.example.demo.test;

import org.springframework.util.StringUtils;

public class MyThread1 extends Thread{
    int i;
    String s;

    public MyThread1(String s){
        super();
        this.s = s;
    }


    @Override
    public void run() {
        synchronized (this) {
            //处理s的方法。
            StringUtils.isEmpty(s);
        }
    }


}
