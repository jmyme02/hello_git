package com.example.demo.IO;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountFile {
     static int count;

    public static void main(String[] args) {
        CountFile cf = new CountFile();
        File file = new File("D:\\download");
        //String判空
        StringUtils.isEmpty("");

        //递归
//        cf.outFileMsg(file);
        //多线程
        ExecutorService es = Executors.newCachedThreadPool();
        for (File file1 : file.listFiles()) {
            es.submit(new FileThread(file1));
        }


    }

    public synchronized static void outFileMsg(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                outFileMsg(f);
            }
        } else {
            System.out.println(count++ + ". " + file.getName() + ": " + file.length());
        }


    }

    static class FileThread extends Thread {
        File file;

        public FileThread(File file) {
            super();
            this.file = file;
        }
        @Override
        public void run() {
            outFileMsg(file);
        }
    }



}
