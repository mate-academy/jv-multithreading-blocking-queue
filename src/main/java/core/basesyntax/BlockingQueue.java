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
        queue.add(element);
        this.notify();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            this.wait();
        }
        T polledValue = queue.poll();
        this.notify();
        return polledValue;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
