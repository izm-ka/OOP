package ru.nsu.izmailova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test for Baker class.
 */
public class BakerTest {
    @Test
    void bakerTest() throws InterruptedException {
        Order order = new Order();
        DataQueue ordersQueue = new DataQueue(3);
        order.setOrderStatus("in process");
        order.setOrderNumber(15);
        while (!ordersQueue.isFull()) {
            ordersQueue.add(order);
        }
        DataQueue deliveryQueue = new DataQueue(3);
        Baker baker = new Baker(ordersQueue, deliveryQueue);
        baker.changeProcessingTime(1000);
        Thread bakerThread = new Thread(baker);
        bakerThread.start();
        while (!deliveryQueue.isFull()) {
        }
        Thread.sleep(3000);
        baker.stopConsume();
        baker.stopProduce();
        while (!deliveryQueue.isEmpty()) {
            order = deliveryQueue.remove();
            assertEquals("On the way", order.getOrderStatus());
            assertEquals(15, order.getOrderNumber());
        }
    }
}
