package ru.nsu.izmailova.pizzeria;

public class JsonBaker {
    private int cookingTime;

    /**
     * Constructor of the JsonBaker class.
     *
     * @param cookingTime cooking time for the baker
     */
    public JsonBaker(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }
}
