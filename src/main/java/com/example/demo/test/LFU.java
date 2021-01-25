package com.example.demo.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LFU<K, V> extends HashMap<K, V> {
    private int max;
    Map<K, HitRate> km = new HashMap<>();

    @Override
    public V get(Object key) {
        V v = super.get(key);
        if(v != null){
            HitRate hitRate = km.get(key);
            hitRate.atime = System.nanoTime();
            hitRate.hitcount = hitRate.hitcount + 1;
        }
        return v;
    }

    @Override
    public V put(K key, V value) {
       while (km.size() > max){
            K k = getLFU();
            km.remove(k);
            this.remove(k);
       }
       km.put(key,new HitRate(key,1,System.nanoTime()));
       V v = super.put(key,value);
       return v;

    }

    //获取要被删除的节点
    public K getLFU(){
        HitRate res = Collections.min(km.values());
        return res.key;
    }
    //实现统计K的点击率和时间的类
    class HitRate implements Comparable<HitRate>{
        K key;
        Integer hitcount;
        Long atime;
        public HitRate (K k , Integer hitcount,long atime ){
            this.key = k;
            this.hitcount = hitcount;
            this.atime = atime;
        }

        @Override
        public int compareTo(HitRate o) {
            int res = hitcount.compareTo(o.hitcount);
            if(res != 0){
                return res;
            }else {
                return atime.compareTo(o.atime);
            }
        }
    }


}


