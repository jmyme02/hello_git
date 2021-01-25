package com.example.demo.thread;

import com.example.demo.test.MyThread;

import java.util.LinkedList;
import java.util.List;

public class MyThreadPool extends Thread{
    //第一步，基本功能，线程池就是将线程池化，维护一个线程list，当请求进来后就在队列中取一个线程执行，执行完事之后归还线程。
    //包括方法：提交、关闭、创建一个活动、关闭一个活动、自旋检查线程数。
    //第二步，创建任务队列类，包括put、get方法，请求进来后先进任务队列。然后工作线程从任务队列中取出线程执行。
    //第三步，弹性的池化，设置池化的最大值和核心值，定时监控，当list中多的时候扩展线程到MAX。
    //第四步，引入拒绝策略，当任务队列满的时候，执行拒绝策略。
    private int initSize;
    private int coreSize;
    private int maxSize;

    private LinkedList<Thread> thread_List = new LinkedList<>(); //线程池链表
    private LinkedList<Runnable> task_List = new LinkedList<>();//任务链表
    private int maxTasknums;
//    private DenyPolicy denyPolicy;//阻塞队列


    public MyThreadPool(int coreSize){
        this.coreSize = coreSize;
        //构造函数
    }

    //线程池初始化

    //静态工厂类用于创建Thread
    static class threadFactory{
        //如果线程池里有
        public static Thread createThread(){
            return new Thread();
        }

    }

    public void submit(Runnable runnable){
        //判断任务队列是否满，没满就入队列，满了就调用阻塞方法。
        if(task_List.size() < maxTasknums){
            task_List.addLast(runnable);
        }else {
            //执行阻塞方法
        }
    }

    class WorkThread extends Thread{
        @Override
        public void run() {
            while(true){
                //如果任务链表不空则，则拿出队首runnable，否则阻塞
                Runnable runnable = task_List.pop();
                //如果线程链接里有则线程则拿出
                Thread thread = thread_List.pop();

                //如果线程池里没有线程，且任务链表满了，并且线程池中的线程池少于最大值，则创建线程，然后拿到。
                Thread thread1 = new Thread();

                thread.start();

            }


        }
    }









}
