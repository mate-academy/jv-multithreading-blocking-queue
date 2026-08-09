package core.basesyntax.thread;

import core.basesyntax.BlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Producer implements Runnable {

    private static final Logger logger = LogManager.getLogger(Consumer.class);
    private final BlockingQueue<Integer> blockingQueue;

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                blockingQueue.put(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("Producer interrupted while putting value {}", i, e);
                break;
            }
        }
    }
}
