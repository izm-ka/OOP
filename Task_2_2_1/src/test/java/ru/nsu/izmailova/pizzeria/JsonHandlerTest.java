package ru.nsu.izmailova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.json.JsonHandler;
import java.io.IOException;
import ru.nsu.izmailova.json.JsonPizzeria;

/**
 * Test for JsonHandler class.
 */
public class JsonHandlerTest {
    @Test
    public void testJsonHandler() throws IOException {
        JsonHandler jsonHandler = new JsonHandler();
        JsonPizzeria pizzeria = jsonHandler.jsonHandle();

        assertEquals(5, pizzeria.getBakersAmount());
        assertEquals(3, pizzeria.getDeliverersAmount());
        assertEquals(20, pizzeria.getStorageSize());
        assertEquals(100, pizzeria.getOrdersDelay());
        assertEquals(60, pizzeria.getWorkingTime());
    }
}
