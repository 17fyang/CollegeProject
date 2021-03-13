package test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {

    private T[] data;
    private int length;
    private Lock produceLock = new ReentrantLock();
    private Lock consumerLock = new ReentrantLock();

    public MyBlockingQueue(int length) {
        this.length = length;
    }

    public void put() {
        
    }

    public T pop() {
        return null;
    }
}
