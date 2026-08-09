package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final Object lock = new Object();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) throws InterruptedException {
        synchronized (lock) {
            while (isFull()) {
                lock.wait();
            }
            queue.add(element);
            lock.notify();
        }
    }

    public T take() throws InterruptedException {
        synchronized (lock) {
            while (isEmpty()) {
                lock.wait();
            }
            lock.notify();
            return queue.poll();
        }
    }

    public boolean isEmpty() {
        synchronized (lock) {
            return queue.isEmpty();
        }
    }

    public boolean isFull() {
        synchronized (lock) {
            return queue.size() == capacity;
        }
    }
}
