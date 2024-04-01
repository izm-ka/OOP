package ru.nsu.izmailova.producer;

/**
 * Interface for making subjects that will produce something.
 */
public interface IProducer extends Runnable{
    void run();

    void producer();

    void stopProduce();
}
