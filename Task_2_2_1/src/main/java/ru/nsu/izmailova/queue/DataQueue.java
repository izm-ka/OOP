package ru.nsu.izmailova.queue;

import java.util.LinkedList;
import java.util.Queue;
import ru.nsu.izmailova.order.Order;

/**
 * Queue that allow to transfer pizza position from the beginning to the end.
 */
public class DataQueue {
    private final Queue<Order> queue = new LinkedList<>();
    private final int maxSize;
    private final Object FULL_QUEUE = new Object();
    private final Object EMPTY_QUEUE = new Object();
    //захватывает мониторы объекта

    /**
     * DataQueue without an upper limit on its size.
     */
    public DataQueue() {
        this.maxSize = Integer.MAX_VALUE;
    }

    /**
     * DataQueue with the specified maximum size.
     *
     * @param maxSize the maximum allowed number of elements in the queue
     */
    public DataQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Waits until queue will be available to interact.
     *
     * @throws InterruptedException can be thrown if the thread is interrupted while waiting
     */
    public void waitOnFull() throws InterruptedException {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.wait();
        }
    }

    /**
     * Notifies all the threads that queue is full.
     */
    public void notifyAllForFull() {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.notifyAll();
        }
    }

    /**
     * Waits until queue will be available to interact.
     *
     * @throws InterruptedException can be thrown if the thread is interrupted while waiting
     */
    public void waitOnEmpty() throws InterruptedException {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.wait();
        }
    }

    /**
     * Notifies all the thread that queue is empty.
     */
    public void notifyAllForEmpty() {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.notify();
        }
    }

    /**
     * Add new order to the queue.
     *
     * @param order you want to add
     */
    public void add(Order order) {
        synchronized (queue) {
            queue.add(order);
        }
    }

    /**
     * Remove order from the queue.
     *
     * @return order
     */
    public Order remove() {
        synchronized (queue) {
            return queue.poll();
        }
    }

    /**
     * Checking whether the queue is full.
     *
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        synchronized (queue) {
            return queue.size() == maxSize;
        }
    }

    /**
     * Checking whether the queue is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        synchronized (queue) {
            return queue.isEmpty();
        }
    }
}
