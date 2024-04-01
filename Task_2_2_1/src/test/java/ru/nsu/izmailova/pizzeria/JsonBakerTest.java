package ru.nsu.izmailova.pizzeria;

import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.json.JsonBaker;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonBakerTest {
    @Test
    void testGetCookingTime() {
        int expTime = 20;
        JsonBaker jsonBaker = new JsonBaker(expTime);
        assertEquals(expTime, jsonBaker.getCookingTime());
    }
}