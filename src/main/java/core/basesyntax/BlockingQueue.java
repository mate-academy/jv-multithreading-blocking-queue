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
        while (capacity > 0 && queue.size() >= capacity) {
            this.wait();
        }
        queue.add(element);
        this.notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            this.wait();
        }
        T polledObj = queue.poll();
        if (queue.size() < capacity) {
            this.notifyAll();
        }
        return polledObj;
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
