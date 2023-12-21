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
        while (capacity == queue.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Something wen wrong!", e);
            }
        }
        this.notify();
        queue.add(element);
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Something wen wrong!", e);
            }
        }
        T polledElement = queue.poll();
        Thread.sleep(0, 5);
        this.notify();
        return polledElement;
    }

    public synchronized boolean isEmpty() {
        this.notify();
        return queue.isEmpty();
    }
}
