package com.example.demo.IO;

public interface DenyPolicy {

    public void reject();

    class OutOfRunnableQueueException extends RuntimeException{
        OutOfRunnableQueueException(String msg){
            super(msg);
        }
    }
}
