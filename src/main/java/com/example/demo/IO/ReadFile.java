package com.example.demo.IO;

import io.netty.buffer.ByteBuf;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {

    public static void main(String[] args) {
        File file = new File("D:\\download\\设置.ini");
        ReadFile rf = new ReadFile();
//        rf.readByBio(file);
        rf.readByNio(file);

    }


    public void readByNio(File file){

        try {
            FileInputStream fis = new FileInputStream(file);
            FileChannel fc = fis.getChannel();
            ByteBuffer b = ByteBuffer.allocate(1024);
            while (fc.read(b) != -1){
                //滑动读取指针
                b.flip();
                //buff里面有值就输出。
                while (b.hasRemaining()){
                    System.out.print((char) b.get());
                }
                //清空buff
                b.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void readByBio(File file){

        try {
            FileInputStream fis = new FileInputStream(file);
            for(int i = 0;i<fis.available();i++){
                System.out.print((char) fis.read());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
