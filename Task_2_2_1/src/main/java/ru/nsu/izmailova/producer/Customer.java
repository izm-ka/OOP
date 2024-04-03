package ru.nsu.izmailova.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.izmailova.order.Order;
import ru.nsu.izmailova.queue.DataQueue;

/**
 * Represents a customer who produces orders for the pizzeria.
 */
public class Customer implements IProducer {
    public final DataQueue orderQueue;
    private int orderCounter;
    private final String orderProduceStatus;
    private static final Logger logger = LogManager.getLogger();
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
     * @param processingTime time the customer takes to process the order
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
        logger.info("Order[{}] is {}", orderCounter, order.getOrderStatus());
        return order;
    }

    /**
     * Changes the status of an order.
     *
     * @param orderQueue  queue with unprocessed orders
     */
    public void addUnprocessedOrders(DataQueue orderQueue) {
        if (unprocessedOrders == null) {
            unprocessedOrders = new ArrayList<>();
        }
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.remove();
            if ((order.getOrderStatus().equals("Unprocessed"))) {
                unprocessedOrders.add(order);
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
