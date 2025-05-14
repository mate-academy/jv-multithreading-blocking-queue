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
        try {
            while (queue.size() == capacity) {
                wait();
            }
            queue.add(element);
        } finally {
            notify();
        }
    }

    public synchronized T take() throws InterruptedException {
        try {
            while (queue.isEmpty()) {
                wait();
            }
            return queue.poll();
        } finally {
            notify();
        }
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
