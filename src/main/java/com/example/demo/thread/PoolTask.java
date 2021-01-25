package com.example.demo.thread;

public class PoolTask extends Thread implements Runnable {
    //任务队列
    private final TaskQueue queue;
    //当前线程运行标志位
    private volatile boolean isRunning = true;

    //传入队列
    public PoolTask(TaskQueue queue) {
        this.queue = queue;
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 14:52
     * @Description 除通过 isRunning 标志位可关闭该线程外，interrupt 信号也可关闭该线程
     */
    @Override
    public void run() {
        while (isRunning && !(this.isInterrupted())) {
            try {
                //获取任务并执行
                Runnable runnable = queue.getTask();
                runnable.run();
            } catch (InterruptedException e0) {
                System.out.println(Thread.currentThread().getName() + " 接收到 interrupt 信号，终止执行");
                return;
            } catch (Exception e1) {
//                e1.printStackTrace();
                return;
            }
        }
        System.out.println(Thread.currentThread().getName() + " 终止执行");
    }

    //安全的关闭该线程
    public void shutdown() {
        this.isRunning = false;
        this.interrupt();
    }
}
