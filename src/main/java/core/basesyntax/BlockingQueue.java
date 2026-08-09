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
            while (queue.size() == 0) {
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
