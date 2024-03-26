package ru.nsu.izmailova.json;

/**
 * Structure for taking baker's data from json.
 */
public class JsonBaker {
    private final int cookingTime;

    /**
     * Constructor of the JsonBaker class.
     *
     * @param cookingTime cooking time for the baker
     */
    public JsonBaker(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    /**
     * Gets the cooking time.
     *
     * @return how long does it take for this baker to make a pizza
     */
    public int getCookingTime() {
        return cookingTime;
    }
}
