package ru.nsu.izmailova.pizzeria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeliveryTest {
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
}
