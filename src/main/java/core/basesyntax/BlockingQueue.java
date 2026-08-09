package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private static final Object lock = new Object();
    private Queue<T> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        if (capacity <= queue.size()) {
            this.wait();
        }
        queue.add(element);
        this.notify();
    }

    public synchronized T take() throws InterruptedException {
        if (queue.isEmpty()) {
            this.wait();
        }
        this.notify();
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
