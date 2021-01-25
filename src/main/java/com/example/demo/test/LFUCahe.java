package com.example.demo.test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
//实现LFU算法首先要继承HASHMAP
//维护两个属性 size和map；其中map用来记录key和点击率，时间的关系
//实现内部类继承compare类，用来记录点击率和时间
//重写get方法，每次get时，V不为空的时候，要更新点击率和时间
//重写put方法，每次put时先判断km和size的大小，put时记得同时维护km
public class LFUCahe<K, V> extends HashMap<K, V> {
    private int size;
    //内部维护一个key和点击率和时间的关系的map
    private Map<K, Ratehit> km = new HashMap<>();
    //重写get,put方法


    @Override
    public V get(Object key) {
        V v = super.get(key);
        if(v != null){
            Ratehit ratehit = km.get(key);
            ratehit.count = ratehit.count++;
            ratehit.atime = System.nanoTime();
            km.put((K) key,ratehit);
        }
        return v;
    }

    @Override
    public V put(K key, V value) {
        while (km.size() > size){
            K k = getD();
            km.remove(k);
            super.remove(k);
        }
        Ratehit r = new Ratehit();
        r.key = key;
        r.count = 1;
        r.atime = System.nanoTime();
        km.put(key,r);
        return super.put(key, value);
    }

    private K getD(){
        Ratehit r=  Collections.min(km.values());
        return r.key;
    }

    class Ratehit implements Comparable<Ratehit> {
        K key;
        Integer count;
        Long atime;

        @Override
        public int compareTo(Ratehit o) {
            int res = count.compareTo(o.count);
            if (res != 0) {
                return res;
            }else {
                return atime.compareTo(o.atime);
            }
        }
    }


}
