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
        while (queue.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Something went wrong while adding element "
                        + element, e);
            }
        }
        queue.add(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Something went wrong while taking element", e);
            }
        }
        T element = queue.poll();
        notify();
        return element;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
