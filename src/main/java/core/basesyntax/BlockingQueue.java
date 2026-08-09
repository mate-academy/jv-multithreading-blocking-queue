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
        while (isFull()) {
            wait();
        }
        if (queue.add(element)) {
            notifyAll();
        }
    }

    private synchronized boolean isFull() {
        return queue.size() == capacity;
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }

        T result = queue.poll();

        if (queue.isEmpty()) {
            notifyAll();
        }
        return result;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

}
