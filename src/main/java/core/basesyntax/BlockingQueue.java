package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;

    private final Object lock = new Object();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) throws InterruptedException {
        // write your code here
        synchronized (lock) {
            while (queue.size() == capacity) {
                lock.wait();
            }
            if (queue.size() < capacity) {
                queue.add(element);
            }
            lock.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        // write your code here
        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait();
            }
            T element = queue.poll();
            lock.notifyAll();
            return element;
        }
    }

    public synchronized boolean isEmpty() {
        // write your code here
        return queue.isEmpty();
    }
}
