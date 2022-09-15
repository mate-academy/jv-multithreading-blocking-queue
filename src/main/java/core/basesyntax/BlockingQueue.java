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
        while (capacity == 0) {
            wait();
        }
        queue.add(element);
        capacity--;
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T peek = queue.peek();
        notify();
        return peek;
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
