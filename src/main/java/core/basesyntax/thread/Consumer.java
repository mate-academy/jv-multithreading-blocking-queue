package core.basesyntax.thread;

import core.basesyntax.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer value = blockingQueue.take();
                System.out.println("Took value " + value);
            } catch (InterruptedException e) {
                throw new RuntimeException("Consumer was interrupted!", e);
            }
        }
    }
}
