package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;
    private boolean isFull = true;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (queue.size() >= capacity) {
            try {
                wait();
            } catch (Exception e) {
                throw new InterruptedException("Something went wrong");
            }
        }
        queue.add(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            try {
                wait();
            } catch (Exception e) {
                throw new InterruptedException("Something went wrong");
            }
        }
        T removed = queue.remove();
        notify();
        return removed;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
