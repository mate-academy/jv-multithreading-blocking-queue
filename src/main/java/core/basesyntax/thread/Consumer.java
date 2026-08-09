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
            if (!blockingQueue.isEmpty()) {
                try {
                    System.out.println("Took value " + blockingQueue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException("Consumer was interrupted!", e);
                }
            } else if (Thread.currentThread().isInterrupted()) {
                break;
            } else {
                Thread.yield();
            }
        }
    }
}
