package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        queue.add(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        T polledElement = queue.poll();
        notify();
        return polledElement;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    private boolean isFull() {
        return queue.size() == capacity;
    }
}
