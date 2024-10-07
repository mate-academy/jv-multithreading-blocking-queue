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
        while (queue.size() == capacity) {
            wait(); // Wait until space is available
        }
        queue.add(element);
        notifyAll(); // Notify waiting threads that an element is available
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait until an element is available
        }
        T element = queue.poll();
        notifyAll(); // Notify waiting threads that space is available
        return element;
    }

    // Add this method for testing purposes
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
