package core.basesyntax;

import core.basesyntax.thread.Consumer;
import core.basesyntax.thread.Producer;
import org.junit.Assert;
import org.junit.Test;

public class BlockingQueueTest {
    private BlockingQueue<Integer> blockingQueue;

    @Test
    public void put_emptySpaceLeft_ok() throws InterruptedException {
        blockingQueue = new BlockingQueue<>(100);
        Thread producer = new Thread(new Producer(blockingQueue));

        producer.start();
        Thread.sleep(500);
        Assert.assertNotEquals("Your `put()` method should not put the producer thread "
                + "in the WAITING state if there is still space in the queue",
                Thread.State.WAITING, producer.getState());
    }

    @Test
    public void put_fullCapacity_ok() throws InterruptedException {
        blockingQueue = new BlockingQueue<>(49);
        Thread producer = new Thread(new Producer(blockingQueue));
        Thread consumer = new Thread(new Consumer(blockingQueue));

        producer.start();
        Thread.sleep(1000);
        Assert.assertEquals("Your `put()` method should put the producer thread "
                + "in the WAITING state if there is no space in the queue",
                Thread.State.WAITING, producer.getState());
        consumer.start();
        Thread.sleep(100);
        Assert.assertNotEquals("Your producer thread should continue working "
                + "if some space appeared in the queue",
                Thread.State.WAITING, producer.getState());
    }

    @Test
    public void take_ok() throws InterruptedException {
        blockingQueue = new BlockingQueue<>(50);
        Runnable testConsumer = () -> {
            try {
                blockingQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException("Consumer was interrupted!", e);
            }
        };
        Thread consumer = new Thread(testConsumer);
        consumer.start();
        Thread.sleep(100);
        Assert.assertEquals("Your `take()` method should put the consumer thread "
                + "in the WAITING state if the queue is empty",
                Thread.State.WAITING, consumer.getState());
        Thread producer = new Thread(new Producer(blockingQueue));
        producer.start();
        consumer.join();
    }

    @Test
    public void isEmpty_ok() throws InterruptedException {
        blockingQueue = new BlockingQueue<>(5);
        Thread producer = new Thread(new Producer(blockingQueue));

        Assert.assertTrue("Your `isEmpty()` method should return `true`"
                + " for the initially empty queue", blockingQueue.isEmpty());
        producer.start();
        Thread.sleep(50);
        Assert.assertFalse("Your `isEmpty()` method should return `false`"
                + " when the queue contains elements", blockingQueue.isEmpty());
    }
}
