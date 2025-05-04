package core.basesyntax;

import core.basesyntax.thread.Consumer;
import core.basesyntax.thread.Producer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(10);
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
        System.out.println(queue);
    }
}
