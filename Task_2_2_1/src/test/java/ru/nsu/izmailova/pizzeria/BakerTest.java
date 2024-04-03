package ru.nsu.izmailova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.baker.Baker;
import ru.nsu.izmailova.order.Order;
import ru.nsu.izmailova.queue.DataQueue;

/**
 * Test for Baker class.
 */
public class BakerTest {
    @Test
    void testConsumer() {
        Order order = new Order();
        DataQueue ordersQueue = new DataQueue();
        order.setOrderNumber(1);
        ordersQueue.add(order);

        DataQueue deliveryQueue = new DataQueue();
        Baker baker = new Baker(ordersQueue, deliveryQueue, 1000);

        baker.consumer();
        assertTrue(ordersQueue.isEmpty());
        assertEquals(1, baker.deliveryCounter);
    }

    @Test
    void testProducer() {
        DataQueue ordersQueue = new DataQueue();
        Order expOrder = new Order();
        expOrder.setOrderNumber(1);
        expOrder.setOrderStatus("On the way");
        ordersQueue.add(expOrder);

        DataQueue deliveryQueue = new DataQueue();
        Baker baker = new Baker(ordersQueue, deliveryQueue, 100);
        baker.deliveryCounter = 1;

        baker.producer();
        Order actOrder = deliveryQueue.remove();

        assertEquals(expOrder.getOrderNumber(), actOrder.getOrderNumber());
        assertEquals(expOrder.getOrderStatus(), actOrder.getOrderStatus());
    }

    @Test
    void testStopConsume() {
        DataQueue ordersQueue = new DataQueue();
        Order order = new Order();
        order.setOrderNumber(1);
        ordersQueue.add(order);
        DataQueue deliveryQueue = new DataQueue();
        Baker baker = new Baker(ordersQueue, deliveryQueue, 100);
        baker.stopConsume();
        assertFalse(baker.getFlag());
    }
}
