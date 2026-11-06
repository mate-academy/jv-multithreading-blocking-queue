package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;
    private Object lock = new Object();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() >= capacity) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.add(element);
            lock.notifyAll();
        }

    }

    public T take() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock.notifyAll();
            return queue.poll();
        }
    }

    public synchronized boolean isEmpty() {
        // write your code here
        return queue.isEmpty();
    }
}
