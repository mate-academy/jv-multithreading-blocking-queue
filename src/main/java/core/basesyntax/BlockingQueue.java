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
        while (element == null || queue.size() >= this.capacity) {
            this.wait();
        }
        queue.add(element);
        this.notify();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty() && queue.size() <= 0) {
            this.wait();
        }
        T element = queue.poll();
        this.notify();
        return element;
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
