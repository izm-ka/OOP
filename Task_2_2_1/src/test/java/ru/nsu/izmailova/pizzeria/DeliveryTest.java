package ru.nsu.izmailova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.order.Order;
import ru.nsu.izmailova.queue.DataQueue;

/**
 * Test for DeliveryGuy class.
 */
/*public class DeliveryTest {
    @Test
    void deliveryGuyTest() throws InterruptedException {
        DataQueue deliveryQueue = new DataQueue(3);
        Order order = new Order();
        order.setOrderNumber(15);
        order.setOrderStatus("in process");
        while (!deliveryQueue.isFull()) {
            deliveryQueue.add(order);
        }
        DeliveryGuy deliveryGuy = new DeliveryGuy(deliveryQueue, 2);
        deliveryGuy.changeProcessingTime(1000);
        Thread delivererThread = new Thread(deliveryGuy);
        delivererThread.start();
        Thread.sleep(3000);
        deliveryGuy.stopConsume();
        assertTrue(deliveryQueue.isEmpty());
    }
}*/
