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
        while (queue.size() == capacity) {
            this.wait();
        }
        notify();
        queue.add(element);
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        notify();
        return queue.peek();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
