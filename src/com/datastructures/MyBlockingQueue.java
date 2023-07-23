package com.datastructures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue {

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private Queue<Integer> queue;
    private int size =16;

    public MyBlockingQueue(int size){
        queue = new LinkedList<>();
        this.size=size;
    }
    void put(Integer e) throws InterruptedException {
        lock.lock();
        try {
        while (queue.size()==size){
            notFull.await();
        }
        queue.offer(e);
        notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    Integer take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size()==0){
                notEmpty.await();
            }
            notFull.signalAll();
            return queue.poll();
        }finally {
            lock.unlock();
        }
    }
}
