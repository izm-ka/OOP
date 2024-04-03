package ru.nsu.izmailova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.json.JsonBaker;

/**
 * Test for JsonBaker class.
 */
public class JsonBakerTest {
    @Test
    void testGetCookingTime() {
        int expTime = 20;
        JsonBaker jsonBaker = new JsonBaker(expTime);
        assertEquals(expTime, jsonBaker.getCookingTime());
    }
}
