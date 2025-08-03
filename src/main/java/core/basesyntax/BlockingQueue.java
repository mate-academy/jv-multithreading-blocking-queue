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
            while (queue.size() == capacity) {
                lock.wait();
            }
            queue.add(element);
            lock.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        T result;
        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait();
            }
            result = queue.poll();
            lock.notifyAll();
        }
        return result;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
