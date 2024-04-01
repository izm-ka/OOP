package ru.nsu.izmailova.pizzeria;

import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.json.JsonPizzeria;
import ru.nsu.izmailova.json.JsonDeliverer;
import ru.nsu.izmailova.json.JsonBaker;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for JsonPizzeria class.
 */
public class JsonPizzeriaTest {

    int bakersAmount = 3;
    int deliverersAmount = 3;
    int storageSize = 20;
    int ordersDelay = 100;
    int workingTime = 100;
    JsonBaker[] bakers = new JsonBaker[] {
            new JsonBaker(20),
            new JsonBaker(20),
            new JsonBaker(20)
    };
    JsonDeliverer[] deliverers = new JsonDeliverer[] {
            new JsonDeliverer(50, 2),
            new JsonDeliverer(55, 2),
            new JsonDeliverer(45, 1)
    };

    JsonPizzeria pizzeria = new JsonPizzeria(bakersAmount, deliverersAmount, storageSize, ordersDelay,
            bakers, deliverers, workingTime);

    @Test
    public void testGetWorkingTime() {
        assertEquals(workingTime, pizzeria.getWorkingTime());
    }

    @Test
    public void testGetBakersSpeed() {
        int[] expSpeed = {20, 20, 20};
        int[] actSpeed = pizzeria.getBakersSpeed();
        assertArrayEquals(expSpeed, actSpeed);
    }

    @Test
    public void testGetDeliverersSpeed() {
        int[] expSpeed = {50, 55, 45};
        int[] actSpeed = pizzeria.getDeliverersSpeed();
        assertArrayEquals(expSpeed, actSpeed);
    }

    @Test
    public void testGetTrunkSize() {
        int[] expSize = {2, 2, 1};
        int[] actSize = pizzeria.getTrunkSize();
        assertArrayEquals(expSize, actSize);
    }

    @Test
    public void testGetStorageSize() {
        assertEquals(storageSize, pizzeria.getStorageSize());
    }

    @Test
    public void testGetBakersAmount() {
        assertEquals(bakersAmount, pizzeria.getBakersAmount());
    }

    @Test
    public void testGetDeliverersAmount() {
        assertEquals(deliverersAmount, pizzeria.getDeliverersAmount());
    }

    @Test
    public void testGetOrdersDelay() {
        assertEquals(ordersDelay, pizzeria.getOrdersDelay());
    }
}
