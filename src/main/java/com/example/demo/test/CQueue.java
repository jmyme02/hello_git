package com.example.demo.test;

import java.util.Stack;

class CQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;


    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        int res = -1;
        if(!stack2.empty()){
            res = stack2.pop();
            while (!stack2.empty()){
                stack1.push(stack2.pop());
            }
        }
        return res;
    }
}

