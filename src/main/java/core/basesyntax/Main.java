package core.basesyntax;

import core.basesyntax.thread.Consumer;
import core.basesyntax.thread.Producer;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new BlockingQueue<>(10);
        Thread producer = new Thread(new Producer(queue), "producer");
        Thread consumer = new Thread(new Consumer(queue), "consumer");

        producer.start();
        consumer.start();
    }
}
