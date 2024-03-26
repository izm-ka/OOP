package ru.nsu.izmailova.producer;

import ru.nsu.izmailova.order.Order;
import ru.nsu.izmailova.queue.DataQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a customer who produces orders for the pizzeria.
 */
public class Customer implements IProducer {
    public final DataQueue orderQueue;
    private int orderCounter;
    private final String orderProduceStatus;

    private volatile boolean runFlag;
    private final Random random = new Random();
    private final int processingTime;
    private List<Order> unprocessedOrders;

    public List<Order> getUnprocessedOrders() {
        return unprocessedOrders;
    }
    /**
     * Constructs a Customer with the specified order queue.
     *
     * @param orderQueue the queue where the customer places orders
     */
    public Customer(DataQueue orderQueue, int processingTime) {
        orderProduceStatus = "Processing";
        this.orderQueue = orderQueue;
        this.processingTime = processingTime;
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
        //проверяем если очередь заполнена, то произ-ль ожидает
        //пока в очереди не освободится место для новых заказов
        while (orderQueue.isFull()) {
            try {
                orderQueue.waitOnFull();
            } catch (InterruptedException e) {
                break;
            }
        }
        if (!getFlag()) { //проверяем не должен ли произ-ль прекратить работу
            return;
        }
        //если очередь не полна то создаем заказ и добавляем его в очередь
        Order order = generateOrder();
        orderQueue.add(order);
        orderQueue.notifyAllForEmpty(); //уведомляем потребителя что заказ добавлен в очередь
        try {
            Thread.sleep(random.nextInt(processingTime)); //типа производим заказ
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        order.setOrderStatus("Unprocessed");
        order.setOrderNumber(orderCounter);
        System.out.println("Order[" + orderCounter + "] is " + order.getOrderStatus());
        return order;
    }

    /**
     * Changes the status of an order.
     *
     * @param order  the order whose status is to be changed
     * @param status the new status of the order
     */
    public void addUnprocessedOrders(List<Order> orders) {
        if (unprocessedOrders == null) {
            unprocessedOrders = new ArrayList<>();
        }
        for (Order order : orders) {
            if ((order.getOrderStatus().equals("Unprocessed") )||
                    (order.getOrderStatus().equals("On the way"))){
                unprocessedOrders.add(order);
                System.out.println("UOrder[" + order.getOrderNumber() + "] is " + order.getOrderStatus());
            }
        }
    }

    /**
     * Stops producing new orders.
     */
    @Override
    public void stopProduce() {
        runFlag = false;
        orderQueue.notifyAllForFull();
    }
}
