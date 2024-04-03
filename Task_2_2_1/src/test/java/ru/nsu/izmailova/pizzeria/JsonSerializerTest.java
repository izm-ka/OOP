package ru.nsu.izmailova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.order.Order;

/**
 * Test for JsonSerializer class.
 */
public class JsonSerializerTest {
    private static final String orderPath = "src/main/resources/orders.json";
    Pizzeria pizzeria = new Pizzeria(2, new int[]{1000, 1000}, 1, new int[]{1}, 5,
            new int[]{5}, 100, orderPath, 100);

    @Test
    void testCustomerUnprocessedOrders() {
        Order testOrder = new Order();
        testOrder.setOrderNumber(1);
        testOrder.setOrderStatus("Unprocessed");
        pizzeria.ordersQueue.add(testOrder);
        pizzeria.pizzeriaStart();
        pizzeria.pizzeriaStop();
        List<Order> loadedOrders = pizzeria.customers.getUnprocessedOrders();
        assertTrue(loadedOrders.contains(testOrder));
    }

    @Test
    void testJsonUnprocessedOrders() throws InterruptedException {
        Order testOrder1 = new Order();
        testOrder1.setOrderNumber(5);
        testOrder1.setOrderStatus("Unprocessed");
        pizzeria.ordersQueue.add(testOrder1);
        pizzeria.pizzeriaStart();
        pizzeria.pizzeriaStop();
        File file = new File("src/main/resources/orders.json");
        String actualJson = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            actualJson = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String expectedJson = "[{\"orderNumber\":5,\"orderStatus\":\"Unprocessed\"}]";
        assertEquals(actualJson, expectedJson);
    }
}
