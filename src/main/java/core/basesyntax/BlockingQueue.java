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
        }

        synchronized (lock) {
            queue.add(element);
            lock.notify();
        }
    }

    public T take() throws InterruptedException {
        synchronized (lock) {
            while (isEmpty()) {
                lock.wait();
            }
        }

        synchronized (lock) {
            lock.notify();
            return queue.poll();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
