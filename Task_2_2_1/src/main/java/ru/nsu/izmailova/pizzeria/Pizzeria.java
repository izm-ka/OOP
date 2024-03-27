package ru.nsu.izmailova.pizzeria;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.izmailova.baker.Baker;
import ru.nsu.izmailova.producer.Customer;
import ru.nsu.izmailova.queue.DataQueue;
import ru.nsu.izmailova.order.OrderSerializer;
import ru.nsu.izmailova.order.Order;

/**
 * Represents a pizzeria that produces pizzas.
 */
public class Pizzeria {
    private final List<Baker> bakers;
    private final List<DeliveryGuy> deliverers;
    private final Customer customers;
    private final OrderSerializer orderSerializer;
    private final List<Order> unprocessedOrders;

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
                    int[] deliverersProductivity, int storageSize, int[] trunkSizes,
                    int ordersDelay, String ordersPath) {
        DataQueue deliveryQueue = new DataQueue(storageSize);
        deliverers = new ArrayList<>();
        for (int i = 0; i < deliverersAmount; i++) {
            DeliveryGuy deliverer = new DeliveryGuy(deliveryQueue, trunkSizes[i],deliverersProductivity[i]);
            deliverers.add(deliverer);
        }

        unprocessedOrders = new ArrayList<>();

        DataQueue ordersQueue = new DataQueue();
        bakers = new ArrayList<>();
        for (int i = 0; i < bakersAmount; i++) {
            Baker baker = new Baker(ordersQueue, deliveryQueue, bakersProductivity[i]);
            bakers.add(baker);
        }
        customers = new Customer(ordersQueue, ordersDelay);
        orderSerializer = new OrderSerializer(ordersPath);
        //loadUnprocessedOrders();
    }

    /**
     * Starts the pizzeria.
     */
    public void pizzeriaStart() {
        loadUnprocessedOrders();
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
        for (Baker baker : bakers) {
            baker.stopConsume();
            baker.stopProduce();
            baker.interrupt();
        }
        //bakers.forEach(Baker::stopConsume);
        //bakers.forEach(Baker::stopProduce);
        for (DeliveryGuy deliveryGuy : deliverers) {
            deliveryGuy.stopConsume();
            deliveryGuy.interrupt();
        }
        customers.addUnprocessedOrders(unprocessedOrders);
        saveUnprocessedOrders();
        System.out.println("Pizzeria is closed");
    }

    private void saveUnprocessedOrders() {
        List<Order> unprocessedOrders = customers.getUnprocessedOrders();
        orderSerializer.saveOrders(unprocessedOrders);
        }

    public void loadUnprocessedOrders() {
        List<Order> unprocessedOrders = orderSerializer.loadOrders();
        if (unprocessedOrders != null) {
            customers.addUnprocessedOrders(unprocessedOrders);
        }
    }

}
