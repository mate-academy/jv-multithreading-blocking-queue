package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;
    private int size;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (size == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(Thread.currentThread().getName()
                        + " was interrupted", e);
            }
        }
        queue.add(element);
        size++;
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(Thread.currentThread().getName()
                        + " was interrupted", e);
            }
        }
        T poll = queue.poll();
        size--;
        notify();
        return poll;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }
}
