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
        while (queue.size() >= capacity) {
            wait();
        }
        queue.offer(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        T headElement = queue.poll();
        notify();
        return headElement;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
