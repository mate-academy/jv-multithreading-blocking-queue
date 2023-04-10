package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;
    private int size;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        size = 0;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (size == capacity) {
            wait();
        }
        queue.add(element);
        size++;
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        T element = queue.poll();
        size--;
        notify();
        return element;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }
}
