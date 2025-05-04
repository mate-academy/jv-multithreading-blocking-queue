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
        // write your code here
        while (queue.size() == capacity) {
            this.wait();
        }
        this.notify();
        queue.add(element);
    }

    public synchronized T take() throws InterruptedException {
        // write your code here
        while (queue.isEmpty()) {
            this.wait();
        }
        this.notify();
        return queue.remove();
    }

    public synchronized boolean isEmpty() {
        // write your code here
        return queue.isEmpty();
    }
}
