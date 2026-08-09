package core.basesyntax.thread;

import core.basesyntax.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer value = blockingQueue.take();
                if (value == null) {
                    break;
                }
                System.out.println("Consumed " + value);
            }
            System.out.println("Consumer finished.");
        } catch (InterruptedException e) {
            System.out.println("Consumer was interrupted!");
        }
    }
}
