package ru.nsu.izmailova.pizzeria;

/**
 * Represents an order in the pizzeria.
 */
public class Order {
    private int orderNumber;
    private String orderStatus;

    /**
     * Returns order number.
     *
     * @return number of the order
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets order number.
     *
     * @param orderNumber number you want to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Returns order status.
     *
     * @return status of the order
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets order status.
     *
     * @param orderStatus status you want to set
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
