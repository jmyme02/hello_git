package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(){
        System.out.println("asdasddddd");
    }


    @ExceptionHandler(value = MyExcepetion.class)
    public void myExcepetionHandler(MyExcepetion e){
        System.out.println("尚未开放");;
    }

}
