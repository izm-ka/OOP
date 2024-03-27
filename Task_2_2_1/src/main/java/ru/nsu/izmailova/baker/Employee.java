package ru.nsu.izmailova.baker;

import ru.nsu.izmailova.consumer.IConsumer;
import ru.nsu.izmailova.producer.IProducer;

public abstract class Employee implements IConsumer, IProducer {
    protected volatile boolean runFlag;

    public void run() {
        while (runFlag) {
            consumer();
            producer();
        }
    }

    public void stopConsume() {
        runFlag = false;
    }

    public void stopProduce() {
        runFlag = false;
    }
}