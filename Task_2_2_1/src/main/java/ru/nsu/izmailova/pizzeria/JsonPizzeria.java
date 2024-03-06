package ru.nsu.izmailova.pizzeria;

public class JsonPizzeria {
    private final int bakersAmount;
    private final int deliverersAmount;
    private final int storageSize;
    private final int ordersDelay;
    private final JsonBaker[] bakers;
    private final JsonDeliverer[] deliverers;

    public JsonPizzeria(int bakersAmount, int deliverersAmount, int storageSize, int ordersDelay,
                        JsonBaker[] bakers, JsonDeliverer[] deliverers) {
        this.bakersAmount = bakersAmount;
        this.deliverersAmount = deliverersAmount;
        this.storageSize = storageSize;
        this.ordersDelay = ordersDelay;
        this.bakers = bakers;
        this.deliverers = deliverers;
    }

    public int[] getBakersSpeed() {
        int[] bakersSpeed = new int[bakersAmount];
        for (int i = 0; i < bakersAmount; i++) {
            bakersSpeed[i] = bakers[i].getCookingTime();
        }
        return bakersSpeed;
    }

    public int[] getDeliverersSpeed() {
        int[] deliverersSpeed = new int[deliverersAmount];
        for (int i = 0; i < deliverersAmount; i++) {
            deliverersSpeed[i] = deliverers[i].getSpeed();
        }
        return deliverersSpeed;
    }

    public int[] getTrunkSize() {
        int[] trunkSize = new int[deliverersAmount];
        for (int i = 0; i < deliverersAmount; i++) {
            trunkSize[i] = deliverers[i].getTrunkSize();
        }
        return trunkSize;
    }

    public int getBakersAmount() {
        return bakersAmount;
    }

    public int getDeliverersAmount() {
        return deliverersAmount;
    }
    public int getStorageSize() {
        return storageSize;
    }

    public int getOrdersDelay() {
        return ordersDelay;
    }
}
