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
        if (queue.size() < capacity) {
            queue.add(element);
            notify();
        }
        if (queue.size() == capacity) {
            wait();
            put(element);
        }
    }

    public synchronized T take() throws InterruptedException {
        if (!queue.isEmpty()) {
            T polled = queue.poll();
            notify();
            return polled;
        }
        wait();
        return take();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
