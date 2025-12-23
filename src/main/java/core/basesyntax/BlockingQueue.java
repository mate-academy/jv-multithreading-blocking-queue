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
        // Wait while the queue is full
        while (queue.size() == capacity) {
            wait();
        }

        queue.add(element);
        // Notify waiting consumers
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        // Wait while the queue is empty
        while (queue.isEmpty()) {
            wait();
        }

        T element = queue.poll();
        // Notify waiting producers
        notifyAll();
        return element;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

