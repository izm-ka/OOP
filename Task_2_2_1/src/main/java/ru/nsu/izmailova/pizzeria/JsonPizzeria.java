package ru.nsu.izmailova.pizzeria;

/**
 * Represents a JSON structure for storing data about a pizzeria.
 */
public class JsonPizzeria {
    private final int bakersAmount;
    private final int deliverersAmount;
    private final int storageSize;
    private final int ordersDelay;
    private final JsonBaker[] bakers;
    private final JsonDeliverer[] deliverers;

    /**
     * Constructs a JsonPizzeria with the specified parameters.
     *
     * @param bakersAmount     the number of bakers in the pizzeria
     * @param deliverersAmount the number of deliverers in the pizzeria
     * @param storageSize      the size of the storage from which deliverers take their orders
     * @param ordersDelay      the maximum delay between two orders
     * @param bakers           an array of JsonBaker objects representing bakers' data
     * @param deliverers       an array of JsonDeliverer objects representing deliverers' data
     */
    public JsonPizzeria(int bakersAmount, int deliverersAmount, int storageSize, int ordersDelay,
                        JsonBaker[] bakers, JsonDeliverer[] deliverers) {
        this.bakersAmount = bakersAmount;
        this.deliverersAmount = deliverersAmount;
        this.storageSize = storageSize;
        this.ordersDelay = ordersDelay;
        this.bakers = bakers;
        this.deliverers = deliverers;
    }

    /**
     * Gets the array of cooking times for the bakers.
     *
     * @return an array containing the cooking times for the bakers
     */
    public int[] getBakersSpeed() {
        int[] bakersSpeed = new int[bakersAmount];
        for (int i = 0; i < bakersAmount; i++) {
            bakersSpeed[i] = bakers[i].getCookingTime();
        }
        return bakersSpeed;
    }

    /**
     * Gets the array of delivery speeds for the deliverers.
     *
     * @return an array containing the delivery speeds for the deliverers
     */
    public int[] getDeliverersSpeed() {
        int[] deliverersSpeed = new int[deliverersAmount];
        for (int i = 0; i < deliverersAmount; i++) {
            deliverersSpeed[i] = deliverers[i].getSpeed();
        }
        return deliverersSpeed;
    }

    /**
     * Gets the array of trunk sizes for the deliverers.
     *
     * @return an array containing the trunk sizes for the deliverers
     */
    public int[] getTrunkSize() {
        int[] trunkSize = new int[deliverersAmount];
        for (int i = 0; i < deliverersAmount; i++) {
            trunkSize[i] = deliverers[i].getTrunkSize();
        }
        return trunkSize;
    }

    /**
     * Gets the number of bakers in the pizzeria.
     *
     * @return the number of bakers
     */
    public int getBakersAmount() {
        return bakersAmount;
    }

    /**
     * Gets the number of deliverers in the pizzeria.
     *
     * @return the number of deliverers
     */
    public int getDeliverersAmount() {
        return deliverersAmount;
    }

    /**
     * Gets the size of the storage from which deliverers take their orders.
     *
     * @return the storage size
     */
    public int getStorageSize() {
        return storageSize;
    }

    /**
     * Gets the maximum delay between two orders.
     *
     * @return the orders delay
     */
    public int getOrdersDelay() {
        return ordersDelay;
    }
}
