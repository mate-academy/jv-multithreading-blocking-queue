package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private volatile int capacity;
    private volatile int size;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        // write your code here
        while (capacity == size) {
            this.wait();
        }
        queue.offer(element);
        size++;
        this.notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        // write your code here
        while (isEmpty()) {
            this.wait();
        }
        size--;
        this.notifyAll();
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        // write your code here
        return size == 0;
    }
}
