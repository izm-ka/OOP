package ru.nsu.izmailova.pizzeria;

import java.util.Random;

public class Baker implements Consumer, Producer {
    private final DataQueue orderQueue;
    private final String orderProduceStatus;

    private final DataQueue deliveryQueue;
    private final String orderConsumeStatus;
    private int deliveryCounter;

    private volatile boolean runFlag;
    private final Random random = new Random();
    private int processingTime = 0;

    /**
     * Baker acts as consumer of user's orders and as producer of pizzas.
     *
     * @param orderQueue    queue where baker receives order
     * @param deliveryQueue queue
     */
    public Baker(DataQueue orderQueue, DataQueue deliveryQueue) {
        orderConsumeStatus = "Cooking";
        orderProduceStatus = "On the way";
        this.orderQueue = orderQueue;
        this.deliveryQueue = deliveryQueue;
        runFlag = true;
    }

    /**
     * Starts the consuming and the producing processes.
     */
    @Override
    public void run() {
        while (getFlag()) {
            consumer();
            producer();
        }
    }

    @Override
    public void consumer() {
        while (orderQueue.isEmpty()) {
            if (!runFlag) {
                return;
            }
            try {
                orderQueue.waitOnEmpty();
            } catch (InterruptedException ignored) {
            }
        }
        if (!runFlag) {
            return;
        }
        Order order = orderQueue.remove();
        deliveryCounter = order.getOrderNumber();
        orderQueue.notifyAllForFull();
    }

    public Order generateDelivery() {
        Order order = new Order();
        order.setOrderStatus(orderConsumeStatus);
        order.setOrderNumber(deliveryCounter);
        System.out.println("Order[" + deliveryCounter + "] is " + orderConsumeStatus);
        return order;
    }

    public boolean getFlag() {
        return runFlag;
    }

    @Override
    public void stopConsume() {
        runFlag = false;
        orderQueue.notifyAllForEmpty();
    }

    @Override
    public void producer() {
        while (deliveryQueue.isFull()) {
            if (!getFlag()) {
                return;
            }
            try {
                deliveryQueue.waitOnFull();
            } catch (InterruptedException e) {
                break;
            }
        }
        if (!getFlag()) {
            return;
        }
        Order delivery = generateDelivery();
        try {
            Thread.sleep(random.nextInt(processingTime));
        } catch (InterruptedException ignored) {
        }
        changeOrderStatus(delivery, orderProduceStatus);
        deliveryQueue.add(delivery);
        deliveryQueue.notifyAllForEmpty();
    }
    public void changeOrderStatus(Order order, String status) {
        order.setOrderStatus(status);
        System.out.println("Order[" + order.getOrderNumber() + "] is " + status);
    }

    /**
     * Change the maximum amount of time that baker can spend on making pizza
     *
     * @param time how long making pizza takes
     */
    public void changeProcessingTime(int time) {
        processingTime = time;
    }

    /**
     * Stops producing new pizzas.
     */
    @Override
    public void stopProduce() {
        runFlag = false;
        deliveryQueue.notifyAllForFull();
        orderQueue.notifyAllForEmpty();
    }
}
