package ru.nsu.izmailova.pizzeria;

import java.util.Random;

/**
 * Represents a baker in the pizzeria who acts as a consumer of user's
 * orders and as a producer of pizzas.
 */
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

    /**
     * Consumes orders from the order queue.
     *
     * orderQueue - очередь заказов клиентов
     * runFlag - булевый флаг для управления процессами потребления и производства.
     *
     */
    @Override
    public void consumer() {
        while (orderQueue.isEmpty()) {
            if (!runFlag) {
                return;
            }
            try {
                orderQueue.waitOnEmpty();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!runFlag) {
            return;
        }
        Order order = orderQueue.remove();
        deliveryCounter = order.getOrderNumber();
        orderQueue.notifyAllForFull(); // пекарь уведомляет другие потоки, ожидающие освобождения места в очереди
    }

    /**
     * Generates a pizza delivery order.
     *
     * @return the generated delivery order
     */
    public Order generateDelivery() {
        Order order = new Order();
        order.setOrderStatus(orderConsumeStatus);
        order.setOrderNumber(deliveryCounter);
        System.out.println("Order[" + deliveryCounter + "] is " + orderConsumeStatus);
        return order;
    }

    /**
     * Gets the value of the run flag.
     *
     * @return true if the baker should continue running, otherwise false
     */
    public boolean getFlag() {
        return runFlag;
    }

    /**
     * Stops consuming orders from the queue.
     */
    @Override
    public void stopConsume() {
        runFlag = false;
        orderQueue.notifyAllForEmpty();
    }

    /**
     * Produces pizzas and put them in the delivery queue.
     */
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changeOrderStatus(delivery, orderProduceStatus);
        deliveryQueue.add(delivery);
        deliveryQueue.notifyAllForEmpty();
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
     * Change the maximum amount of time that baker can spend on making pizza.
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
