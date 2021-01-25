package com.example.demo.test;

import java.util.*;

//实现LRU，一个元素被调用会就排到队尾，当cache满了后出队头
class LRUCache extends LinkedHashMap{
    private int size;
    //构造函数
    public LRUCache(int size){
        super(size, 0.75F, true);
        this.size = size;
    }
    //get put方法一致
    //重写更新算法，当map满了之后，更新掉头


    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > size;
    }
}