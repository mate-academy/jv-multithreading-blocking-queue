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
        notify();
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(element);
    }

    public synchronized T take() throws InterruptedException {
        notify();
        while (isEmpty()) {
            wait();
        }
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
