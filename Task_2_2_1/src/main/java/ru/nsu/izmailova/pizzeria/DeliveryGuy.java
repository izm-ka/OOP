package ru.nsu.izmailova.pizzeria;

import ru.nsu.izmailova.consumer.IConsumer;
import ru.nsu.izmailova.order.Order;
import ru.nsu.izmailova.queue.DataQueue;

import java.util.ArrayDeque;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a delivery person who delivers pizzas.
 */
public class DeliveryGuy implements IConsumer {
    private final Thread deliveryThread;
    private final DataQueue deliveryQueue;
    private final String orderConsumeStatus;
    private final ArrayDeque<Order> trunk = new ArrayDeque<>();
    private final int trunkSize;
    private volatile boolean runFlag;
    private final int processingTime;
    private static final Logger logger = LogManager.getLogger();

    /**
     * Takes as many pizzas from storage, as his trunk could afford.
     *
     * @param deliveryQueue  queue of cooked pizzas
     * @param trunkSize      amount of pizzas that deliverer can take once
     * @param processingTime time the delivery guy takes to delivery
     */
    public DeliveryGuy(DataQueue deliveryQueue, int trunkSize, int processingTime) {
        orderConsumeStatus = "Delivered";
        this.deliveryQueue = deliveryQueue;
        this.processingTime = processingTime;
        this.trunkSize = trunkSize;
        deliveryThread = new Thread(this);
        runFlag = true;
    }

    /**
     * Starts consuming orders from the storage.
     */
    @Override
    public void run() {
        while (getFlag() && !Thread.currentThread().isInterrupted()) {
            consumer();
        }
    }

    public void interrupt() {
        deliveryThread.interrupt();
    }


    /**
     * Delivery person takes pizzas from the queue and delivers them.
     */
    @SuppressWarnings("BusyWait")
    @Override
    public void consumer() {
        for (int i = 0; i < trunkSize && !Thread.currentThread().isInterrupted(); i++) {
            while (deliveryQueue.isEmpty()) {
                if (!runFlag) {
                    return;
                }
                try {
                    deliveryQueue.waitOnEmpty();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!runFlag) {
                return;
            }
            if (!deliveryQueue.isEmpty()) {
                Order order = deliveryQueue.remove();
                trunk.add(order);
                deliveryQueue.notifyAllForFull();
            }
        }
        while (!trunk.isEmpty()) {
            try {
                Thread.sleep(processingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            changeOrderStatus(trunk.remove(), orderConsumeStatus);
        }
    }

    /**
     * This method allows getting flag indicating whether the program is running or not.
     *
     * @return the flag
     */
    public boolean getFlag() {
        return runFlag;
    }

    /**
     * Stops delivering the pizzas.
     */
    @Override
    public void stopConsume() {
        runFlag = false;
        deliveryQueue.notifyAllForEmpty();
    }

    /**
     * This method can be used to change the status of an order.
     *
     * @param order  - the order you want to change
     * @param status - the new status you want to set
     */
    public void changeOrderStatus(Order order, String status) {
        order.setOrderStatus(status);
        logger.info("Order[{}] is {}", order.getOrderNumber(), status);
    }

}
