package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;
    private volatile int count = 0;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (count >= capacity) {
            wait();
        }
        queue.add(element);
        count += 1;
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (count <= 0) {
            wait();
        }
        T element = queue.poll();
        count -= 1;
        notify();
        return element;
    }

    public synchronized boolean isEmpty() {
        return count <= 0;
    }
}
