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
            wait();
        }
        notify();
        queue.add(element);
    }

    public synchronized T take() throws InterruptedException {
        // write your code here
        while (queue.isEmpty()) {
            wait();
        }
        notify();
        return queue.remove();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
