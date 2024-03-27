package ru.nsu.izmailova.consumer;

/**
 * Interface for making subjects that will consume elements.
 */
public interface IConsumer extends Runnable {
    void run();

    void consumer();

    void stopConsume();
}
