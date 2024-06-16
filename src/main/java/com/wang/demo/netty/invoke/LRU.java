package com.wang.demo.netty.invoke;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/17 07:30
 */
public class LRU<K,V> extends LinkedHashMap<K,V> {

    private int maxSize;

    public LRU(int maxSize,
               int initialCapacity,
               float loadFactor,
               boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }

}
