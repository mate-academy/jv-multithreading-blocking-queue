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
        queue.add(element);
        if (queue.size() == capacity) {
            wait();
        }
        isEmpty();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.remove();
    }

    public synchronized boolean isEmpty() {
        if (!queue.isEmpty()) {
            notifyAll();
            return false;
        }
        return true;
    }
}
