package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Object lock = new Object();
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
            lock.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        synchronized (lock) {
            while (isEmpty()) {
                lock.wait();
            }
            T item = queue.poll();
            lock.notifyAll();
            return item;
        }
    }

    public boolean isEmpty() {
        synchronized (lock) {
            return queue.isEmpty();
        }
    }
}
