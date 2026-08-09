package core.basesyntax;

import core.basesyntax.thread.Consumer;
import core.basesyntax.thread.Producer;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new BlockingQueue<>(10);
        Thread producer = new Thread(new Producer(queue), "Producer Thread");
        Thread consumer = new Thread(new Consumer(queue), "Consumer Thread");

        producer.start();
        consumer.start();
    }
}
