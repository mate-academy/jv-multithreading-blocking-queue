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
        if (queue.size() == capacity) {
            wait();
        }

        queue.add(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        if (isEmpty()) {
            wait();
        }

        T item = queue.poll();
        notify();
        return item;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
