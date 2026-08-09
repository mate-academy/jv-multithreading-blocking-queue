package core.basesyntax.thread;

import core.basesyntax.BlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Consumer implements Runnable {

    private static final Logger logger = LogManager.getLogger(Consumer.class);
    private final BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Integer value = blockingQueue.take();
                System.out.println("Took value " + value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("Thread was interrupted while taking from queue", e);
                break;
            }
        }
    }
}
