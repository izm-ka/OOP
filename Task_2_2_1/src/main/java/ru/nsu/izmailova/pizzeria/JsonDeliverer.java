package ru.nsu.izmailova.pizzeria;

public class JsonDeliverer {
    private int deliveryTime;
    private int trunkSize;

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
