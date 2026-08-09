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
        while (this.queue.size() == this.capacity) {
            wait();
        }
        if (this.queue.isEmpty()) {
            notifyAll();
        }
        this.queue.add(element);
    }

    public synchronized T take() throws InterruptedException {
        // write your code here
        while (this.queue.isEmpty()) {
            wait();
        }
        if (this.queue.size() == this.capacity) {
            notifyAll();
        }
        this.queue.remove(0);
        return queue.element();
    }

    public synchronized boolean isEmpty() {
        // write your code here
        return this.queue.isEmpty();
    }
}
