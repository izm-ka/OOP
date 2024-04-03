package ru.nsu.izmailova.baker;

import ru.nsu.izmailova.consumer.IConsumer;
import ru.nsu.izmailova.producer.IProducer;

/**
 * An abstract class representing an employee in the pizzeria
 * who both consumes and produces items.
 */
public abstract class Employee implements IConsumer, IProducer {
    protected volatile boolean runFlag;

    /**
     * Consuming and producing items while the runFlag is true.
     */
    public void run() {
        while (runFlag) {
            consumer();
            producer();
        }
    }

    /**
     * Stops consuming.
     */
    public void stopConsume() {
        runFlag = false;
    }

    /**
     * Stops producing.
     */
    public void stopProduce() {
        runFlag = false;
    }
}