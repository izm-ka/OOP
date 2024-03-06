package ru.nsu.izmailova.pizzeria;

import java.io.IOException;

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
    public static void main(String args[]) throws InterruptedException, IOException {
        JsonHandler handler = new JsonHandler();
        JsonPizzeria jp = handler.jsonHandle();
        Pizzeria pizzeria = new Pizzeria(jp.getBakersAmount(), jp.getBakersSpeed(), jp.getDeliverersAmount(), jp.getDeliverersSpeed(), jp.getStorageSize(), jp.getTrunkSize(), jp.getOrdersDelay());
        pizzeria.pizzeriaStart();
        Thread.sleep(1000 * 60);
        pizzeria.pizzeriaStop();
    }
}
