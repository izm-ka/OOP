package ru.nsu.izmailova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.producer.Customer;
import ru.nsu.izmailova.order.Order;
import java.util.ArrayList;
import java.util.List;
import ru.nsu.izmailova.queue.DataQueue;

/**
 * Test for Customer class.
 */
class CustomerTest {

    @Test
    void testGenerateOrder() {
        DataQueue ordersQueue = new DataQueue();
        Customer customer = new Customer(ordersQueue, 1000);
        Order order = customer.generateOrder();

        assertEquals("Unprocessed", order.getOrderStatus());
        assertEquals(1, order.getOrderNumber());
    }

    @Test
    void testAddUnprocessedOrders() {
        DataQueue orderQueue = new DataQueue();

        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setOrderStatus("Unprocessed");
        orderQueue.add(order1);

        Order order2 = new Order();
        order2.setOrderNumber(2);
        order2.setOrderStatus("Processed");
        orderQueue.add(order2);

        List<Order> expOrders = new ArrayList<>();
        expOrders.add(order1);

        Customer customer = new Customer(orderQueue, 1000);
        customer.addUnprocessedOrders(orderQueue);

        assertEquals(expOrders, customer.getUnprocessedOrders());
    }

    @Test
    void testStopProduce() {
        DataQueue orderQueue = new DataQueue();
        Customer customer = new Customer(orderQueue, 1000);

        customer.stopProduce();
        assertFalse(customer.getFlag());
    }

    @Test
    void testProducer() {
        DataQueue orderQueue = new DataQueue();
        Customer customer = new Customer(orderQueue, 1000);

        assertTrue(orderQueue.isEmpty());

        Thread producerThread = new Thread(customer::producer);
        producerThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(!orderQueue.isEmpty());
        customer.stopProduce();

        Order order = orderQueue.remove();
        assertEquals("Unprocessed", order.getOrderStatus());
        assertEquals(1, order.getOrderNumber());
    }
}
