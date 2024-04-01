package ru.nsu.izmailova.pizzeria;

import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.json.JsonDeliverer;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for JsonDelivery class.
 */
public class JsonDeliveryTest {
    @Test
    void testGetSpeed() {
        int expDeliveryTime = 30;
        int trunkSize = 10;
        JsonDeliverer jsonDeliverer = new JsonDeliverer(expDeliveryTime, trunkSize);
        assertEquals(expDeliveryTime, jsonDeliverer.getSpeed());
    }

    @Test
    void testGetTrunkSize() {
        int deliveryTime = 30;
        int expTrunkSize = 10;
        JsonDeliverer jsonDeliverer = new JsonDeliverer(deliveryTime, expTrunkSize);
        assertEquals(expTrunkSize, jsonDeliverer.getTrunkSize());
    }
}
