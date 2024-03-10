package ru.nsu.izmailova.pizzeria;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a pizzeria that produces pizzas.
 */
public class Pizzeria {
    private final List<Baker> bakers;
    private final List<DeliveryGuy> deliverers;
    private final Customer customers;

    /**
     * Constructs a new pizzeria with the specified parameters.
     *
     * @param bakersAmount           the number of bakers in the pizzeria
     * @param bakersProductivity     the productivity of each baker
     * @param deliverersAmount       the number of delivery guys in the pizzeria
     * @param deliverersProductivity the productivity of each delivery guy
     * @param storageSize            the size of the storage from which deliverers take orders
     * @param trunkSizes             the sizes of the trunks of deliverer's cars
     * @param ordersDelay            the maximum delay between two orders
     */
    public Pizzeria(int bakersAmount, int[] bakersProductivity, int deliverersAmount,
                    int[] deliverersProductivity, int storageSize, int[] trunkSizes, int ordersDelay) {
        DataQueue deliveryQueue = new DataQueue(storageSize);
        deliverers = new ArrayList<>();
        for (int i = 0; i < deliverersAmount; i++) {
            DeliveryGuy deliverer = new DeliveryGuy(deliveryQueue, trunkSizes[i]);
            deliverer.changeProcessingTime(deliverersProductivity[i]);
            deliverers.add(deliverer);
        }

        DataQueue ordersQueue = new DataQueue();
        bakers = new ArrayList<>();
        for (int i = 0; i < bakersAmount; i++) {
            Baker baker = new Baker(ordersQueue, deliveryQueue);
            baker.changeProcessingTime(bakersProductivity[i]);
            bakers.add(baker);
        }
        customers = new Customer(ordersQueue);
        customers.changeProcessingTime(ordersDelay);
    }

    /**
     * Starts the pizzeria.
     */
    public void pizzeriaStart() {
        Thread customersThread = new Thread(customers);
        customersThread.start();
        System.out.println("Pizzeria is opened");
        bakers.stream().map(Thread::new).forEach(Thread::start);
        deliverers.stream().map(Thread::new).forEach(Thread::start);
    }

    /**
     * Stops the pizzeria.
     *
     * @throws InterruptedException if any thread is interrupted while sleeping
     */
    public void pizzeriaStop() throws InterruptedException {
        customers.stopProduce();
        Thread.sleep(15 * 1000);
        bakers.forEach(Baker::stopConsume);
        bakers.forEach(Baker::stopProduce);
        Thread.sleep(30 * 1000);
        deliverers.forEach(DeliveryGuy::stopConsume);
        System.out.println("Pizzeria is closed");
    }
}
