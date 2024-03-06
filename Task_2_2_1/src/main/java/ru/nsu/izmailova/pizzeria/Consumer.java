package ru.nsu.izmailova.pizzeria;

/**
 * Interface for making subjects that will consume elements.
 */
public interface Consumer extends Runnable {
    void run();
    void consumer();
    void stopConsume();
}
