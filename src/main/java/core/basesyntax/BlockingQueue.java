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
        while (this.queue.size() >= this.capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Something went wrong", e);
            }
        }
        this.queue.add(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (this.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Something went wrong", e);
            }
        }
        notify();
        return this.queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
