package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private static final Object lock = new Object();
    private Queue<T> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == capacity) {
                lock.wait();
            }

            queue.add(element);
            lock.notify();
        }
    }

    public T take() throws InterruptedException {
        T value;

        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait();
            }

            value = queue.poll();
            lock.notify();
        }
        return value;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
