package ru.nsu.izmailova.pizzeria;

/**
 * Interface for making subjects that will produce something.
 */
public interface Producer extends Runnable{
    void run();

    void producer();

    void stopProduce();
}
