package ru.nsu.izmailova.pizzeria;

/**
 * Represents a JSON structure for storing data about a deliverer.
 */
public class JsonDeliverer {
    private int deliveryTime;
    private int trunkSize;

    /**
     * Constructs a deliverer with the specified delivery time and trunk size.
     *
     * @param deliveryTime the time to deliver pizza
     * @param trunkSize    the size of the trunk of the deliverer's car
     */
    public JsonDeliverer(int deliveryTime, int trunkSize) {
        this.deliveryTime = deliveryTime;
        this.trunkSize = trunkSize;
    }

    /**
     * Gets delivery time.
     *
     * @return deliveryTime time to deliver pizza
     */
    public int getSpeed() {
        return deliveryTime;
    }

    /**
     * Gets trunk size.
     *
     * @return trunkSize size of the trunk of the deliverer's car
     */
    public int getTrunkSize() {
        return trunkSize;
    }
}
