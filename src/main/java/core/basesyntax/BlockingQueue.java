package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;
    private int size;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        boolean cond = true;
        while (cond) {
            if (capacity > queue.size()) {
                queue.offer(element);
                cond = false;
                notify();
            } else {
                wait();
            }
        }
    }

    public synchronized T take() throws InterruptedException {
        while (true) {
            if (queue.isEmpty()) {
                wait();
            } else {
                notify();
                return queue.poll();
            }
        }
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
