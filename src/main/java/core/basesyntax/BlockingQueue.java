package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (queue.size() == capacity) {
            this.wait();
        }
        queue.add(element);
        this.notify();
        ArrayBlockingQueue g;
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            this.wait();
        }
        T result = queue.poll();
        this.notify();
        return result;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
