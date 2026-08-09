package core.basesyntax.thread;

import core.basesyntax.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                blockingQueue.put(i);
                System.out.println("Produced " + i);
            }
            blockingQueue.put(null);
        } catch (InterruptedException e) {
            System.out.println("Producer was interrupted!");
        }
    }
}
