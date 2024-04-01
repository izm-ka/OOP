package ru.nsu.izmailova.baker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.izmailova.order.Order;
import ru.nsu.izmailova.queue.DataQueue;

/**
 * Represents a baker in the pizzeria who acts as a consumer of user's
 * orders and as a producer of pizzas.
 */
public class Baker extends Employee {
    private final DataQueue orderQueue;
    private final String orderProduceStatus;
    private static final Logger logger = LogManager.getLogger();
    private final DataQueue deliveryQueue;
    private final String orderConsumeStatus;
    public int deliveryCounter;
    private final Thread cookingThread;
    private final int cookingTime;

    /**
     * Baker acts as consumer of user's orders and as producer of pizzas.
     *
     * @param orderQueue    queue where baker receives order
     * @param deliveryQueue queue
     * @param cookingTime   time the baker takes to cook
     */
    public Baker(DataQueue orderQueue, DataQueue deliveryQueue, int cookingTime) {
        orderConsumeStatus = "Cooking";
        orderProduceStatus = "On the way";
        this.orderQueue = orderQueue;
        this.deliveryQueue = deliveryQueue;
        this.cookingTime = cookingTime;
        cookingThread = new Thread(this);
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
     * runFlag - флаг для управления процессами потребления и производства.
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
        orderQueue.notifyAllForFull();
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
        logger.info("Order[{}] is {}", deliveryCounter, orderConsumeStatus);
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
            Thread.sleep(cookingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
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
        logger.info("Order[{}] is {}", order.getOrderNumber(), status);
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
