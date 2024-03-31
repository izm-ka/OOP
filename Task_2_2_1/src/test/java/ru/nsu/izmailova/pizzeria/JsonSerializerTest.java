package ru.nsu.izmailova.pizzeria;
import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.pizzeria.Pizzeria;
import ru.nsu.izmailova.order.OrderSerializer;
import ru.nsu.izmailova.order.Order;
import ru.nsu.izmailova.queue.DataQueue;
import ru.nsu.izmailova.json.JsonPizzeria;
import ru.nsu.izmailova.json.JsonHandler;
import ru.nsu.izmailova.queue.DataQueue;
import ru.nsu.izmailova.producer.Customer;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonSerializerTest {
    private static final String orderPath = "src/main/resources/orders.json";
    Pizzeria pizzeria = new Pizzeria(2, new int[]{1000, 1000},1, new int[]{1}, 5, new int[]{5}, 100, orderPath);

    @Test
    void testUnprocessedOrders() throws InterruptedException {
        Order testOrder = new Order();
        testOrder.setOrderNumber(1);
        testOrder.setOrderStatus("Unprocessed");
        pizzeria.ordersQueue.add(testOrder);
        pizzeria.pizzeriaStart();
        pizzeria.pizzeriaStop();
        List<Order> loadedOrders = pizzeria.customers.getUnprocessedOrders();
        assertTrue(loadedOrders.contains(testOrder));
    }
}
