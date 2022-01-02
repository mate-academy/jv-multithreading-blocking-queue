package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (queue.size() >= capacity) {
            try {
                System.out.println("PUT - size = " + queue.size());
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Something went wrong", e);
            }
        }
        System.out.println("PUT - size = " + queue.size());
        queue.offer(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.size() == 0) {
            try {
                System.out.println("TAKE - size = " + queue.size());
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Something went wrong", e);
            }
        }
        System.out.println("TAKE - size = " + queue.size());
        notify();
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
