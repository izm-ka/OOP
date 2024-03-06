package ru.nsu.izmailova.pizzeria;

import java.util.Random;

/**
 * Represents a customer who produces orders for the pizzeria.
 */
public class Customer implements Producer {
    public final DataQueue orderQueue;
    private int orderCounter;
    private final String orderProduceStatus;

    private volatile boolean runFlag;
    private final Random random = new Random();
    private int processingTime = 0;

    /**
     * Constructs a Customer with the specified order queue.
     *
     * @param orderQueue the queue where the customer places orders
     */
    public Customer(DataQueue orderQueue) {
        orderProduceStatus = "Processing";
        this.orderQueue = orderQueue;
        runFlag = true;
    }

    /**
     * Starts the producing process.
     */
    @Override
    public void run() {
        while (getFlag()) {
            producer();
        }
    }

    /**
     * Gets the value of the run flag.
     *
     * @return true if the customer should continue running, otherwise false
     */
    public boolean getFlag() {
        return runFlag;
    }

    /**
     * Produces orders and places them in the order queue.
     */
    @Override
    public void producer() {
        while (orderQueue.isFull()) {
            try {
                orderQueue.waitOnFull();
            } catch (InterruptedException e) {
                break;
            }
        }
        if (!getFlag()) {
            return;
        }
        Order order = generateOrder();
        orderQueue.add(order);
        orderQueue.notifyAllForEmpty();
        try {
            Thread.sleep(random.nextInt(processingTime));
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Generates an order.
     *
     * @return the generated order
     */
    public Order generateOrder() {
        Order order = new Order();
        orderCounter++;
        order.setOrderStatus(orderProduceStatus);
        order.setOrderNumber(orderCounter);
        System.out.println("Order[" + orderCounter + "] is " + orderProduceStatus);
        return order;
    }

    /**
     * Changes the maximum amount of time that the customer can spend on producing an order.
     *
     * @param time the new processing time for producing an order
     */
    public void changeProcessingTime(int time) {
        processingTime = time;
    }

    /**
     * Changes the status of an order.
     *
     * @param order  the order whose status is to be changed
     * @param status the new status of the order
     */
    public void changeOrderStatus(Order order, String status) {
        order.setOrderStatus(status);
        System.out.println("Order[" + order.getOrderNumber() + "] is " + status);
    }

    /**
     * Stops producing new orders
     */
    @Override
    public void stopProduce() {
        runFlag = false;
        orderQueue.notifyAllForFull();
    }
}
