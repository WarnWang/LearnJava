package LeetCode.DesignPuzzles;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by warn on 1/9/2016.
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and set.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 */
public class LRUCache {
    private LinkedList<Integer> timeList;
    private HashMap<Integer, Integer> timeKeyMap;
    private HashMap<Integer, Integer> keyTimeMap;
    private int time;
    private int capacity;
    private HashMap<Integer, Integer> keyMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyMap = new HashMap<>(capacity);
        time = 0;
        timeList = new LinkedList<>();
        timeKeyMap = new HashMap<>(capacity);
        keyTimeMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (keyMap.containsKey(key)) {
            int originalTime = keyTimeMap.get(key);
            timeKeyMap.remove(originalTime);
            keyTimeMap.put(key, time);
            timeKeyMap.put(time, key);
            timeList.add(time);
            time++;
            return keyMap.get(key);
        } else return -1;
    }

    public void set(int key, int value) {
        if (keyMap.containsKey(key)) {
            int originalTime = keyTimeMap.get(key);
            timeKeyMap.remove(originalTime);
        } else if (keyMap.size() == this.capacity) {
            while (!timeList.isEmpty()) {
                int before = timeList.removeFirst();
                if (timeKeyMap.containsKey(before)) {
                    int keyToRemove = timeKeyMap.get(before);
                    keyMap.remove(keyToRemove);
                    timeKeyMap.remove(before);
                    break;
                }
            }
        }
        keyMap.put(key, value);
        keyTimeMap.put(key, time);
        timeKeyMap.put(time, key);
        timeList.add(time);
        time++;
    }
}
