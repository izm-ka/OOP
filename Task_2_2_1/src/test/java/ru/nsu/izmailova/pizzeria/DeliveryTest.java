package ru.nsu.izmailova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.order.Order;
import ru.nsu.izmailova.queue.DataQueue;

/**
 * Test for DeliveryGuy class.
 */
public class DeliveryTest {
    @Test
    void deliveryGuyTest() throws InterruptedException {
        DataQueue deliveryQueue = new DataQueue(3);
        Order order1 = new Order();
        order1.setOrderNumber(1);
        deliveryQueue.add(order1);

        Order order2 = new Order();
        order2.setOrderNumber(2);
        deliveryQueue.add(order2);

        DeliveryGuy deliveryGuy = new DeliveryGuy(deliveryQueue, 1, 1000);
        Thread delivererThread = new Thread(deliveryGuy);
        delivererThread.start();
        Thread.sleep(3000);
        deliveryGuy.stopConsume();
        assertTrue(deliveryQueue.isEmpty());
        assertEquals("Delivered", order1.getOrderStatus());
        assertEquals("Delivered", order2.getOrderStatus());
    }
}
