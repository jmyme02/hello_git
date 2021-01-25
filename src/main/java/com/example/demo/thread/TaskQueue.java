package com.example.demo.thread;

public interface TaskQueue {
    //新任务追加到队列结尾
    void putTask(Runnable runnable);

    //获取任务,该方法是阻塞的，应当向上抛出 InterruptException 使调用方做出阻塞期间对 interrupt 信号的响应
    Runnable getTask() throws InterruptedException;

    //获取当前任务数
    int getSize();
}
