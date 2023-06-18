package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;
    private volatile int size = 0;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (size >= capacity) {
            wait();
        }

        notify();

        queue.add(element);
        size++;
    }

    public synchronized T take() throws InterruptedException {
        while (size == 0) {
            wait();
        }

        notify();

        size--;
        return queue.remove();
    }

    public synchronized boolean isEmpty() {
       return size == 0;
    }
}
