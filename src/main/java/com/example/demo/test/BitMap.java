package com.example.demo.test;

public class BitMap {
    private int[] BitArray;

    //构造函数
    public BitMap(int size) {
        BitArray = new int[size / 32 + 1];
    }

    //将num的对应位置置1
    public void set1(int num) {
        //定位
        int index = num / 32;
        int index2 = num % 32;
        BitArray[index] = BitArray[index] | 1 << index2;
    }

    //将num的对应位置置0
    public void set0(int num) {
        //定位
        int index = num / 32;
        int index2 = num % 32;
        BitArray[index] = BitArray[index] & ~(1 << index2);
    }

    //判断num是否在数组内
    public boolean isContain(int num) {
        //定位
        int index = num / 32;
        int index2 = num % 32;
        return ((BitArray[index] & (1 << index2)) != 0) ? true : false;
    }

}
