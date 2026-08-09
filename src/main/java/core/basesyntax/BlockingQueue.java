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
        if (capacity == queue.size()) {
            this.wait();
        }
        queue.add(element);
        this.notify();
        // write your code here
    }

    public synchronized T take() throws InterruptedException {
        if (isEmpty()) {
            this.wait();
        }
        this.notify();
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        // write your code here
        return queue.isEmpty();
    }
}
