package ru.nsu.izmailova;

import java.io.IOException;
import ru.nsu.izmailova.json.JsonHandler;
import ru.nsu.izmailova.json.JsonPizzeria;
import ru.nsu.izmailova.pizzeria.Pizzeria;

/**
 * The main class to start the pizzeria simulation.
 */
public class Main {
    /**
     * The main method to start the pizzeria simulation.
     *
     * @param args command-line arguments
     * @throws InterruptedException if a thread is interrupted while sleeping
     * @throws IOException          if an I/O error occurs while reading JSON data
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        JsonHandler handler = new JsonHandler();
        JsonPizzeria jp = handler.jsonHandle();
        Pizzeria pizzeria = new Pizzeria(jp.getBakersAmount(), jp.getBakersSpeed(),
                jp.getDeliverersAmount(), jp.getDeliverersSpeed(),
                jp.getStorageSize(), jp.getTrunkSize(), jp.getOrdersDelay(),
                "src/main/resources/orders.json", jp.getWorkingTime());
        pizzeria.pizzeriaStart();
        pizzeria.pizzeriaWorking();
        pizzeria.pizzeriaStop();
    }
}
