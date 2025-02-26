package core.basesyntax.thread;

import core.basesyntax.BlockingQueue;

public class Producer implements Runnable {
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
                throw new RuntimeException("Producer was interrupted!", e);
            }
        }
    }
}
