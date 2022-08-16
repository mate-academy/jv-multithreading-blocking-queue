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
        while (queue.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException exc) {
                throw new RuntimeException("Cannot put " + element + " to capacity");
            }
        }
        queue.add(element);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException exc) {
                throw new RuntimeException("Cannot take element from queue");
            }
        }
        T current = queue.poll();
        notify();
        return current;
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
