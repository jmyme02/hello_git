package com.example.demo.test;

import java.util.*;

class RandomizedSet {
    //用来实现快速的插入和删除
    Map<Integer,Integer> map;
    //用来实现随机从set中获取一个值
    LinkedList<Integer> list;
    Random random = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new LinkedList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }else {
            map.put(val,list.size());
            list.add(val);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(map.containsKey(val)){
            int tmp = list.get(list.size() - 1);
            int index = map.get(val);
            list.set(index,tmp);
            map.put(tmp,index);
            list.remove(list.size()-1);
            map.remove(val);

            return true;
        }else {
            return false;
        }

    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
