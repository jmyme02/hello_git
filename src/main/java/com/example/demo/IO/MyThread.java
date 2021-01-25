package com.example.demo.IO;

public class MyThread extends Thread{
    String s;
    public MyThread(String s){
        super();
        this.s = s;
    }
    @Override
    public void run() {
        synchronized (this){
//            doSomething(s);
        }

    }
}
