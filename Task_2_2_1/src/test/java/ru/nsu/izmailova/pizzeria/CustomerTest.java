package ru.nsu.izmailova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Test for Customer class.
 */
class CustomerTest {

    @Test
    void customerTest() throws InterruptedException {
        DataQueue ordersQueue = new DataQueue(5);
        Customer customers = new Customer(ordersQueue);
        customers.changeProcessingTime(1000);

        Thread customersThread = new Thread(customers);
        customersThread.start();
        while (!ordersQueue.isFull()) {
        }
        Thread.sleep(3000);
        customers.stopProduce();
        while (!ordersQueue.isEmpty()) {
            assertEquals("Processing", ordersQueue.remove().getOrderStatus());
        }
        Thread.sleep(3000);
    }
}
