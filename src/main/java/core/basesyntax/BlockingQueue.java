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
        boolean lock = true;
        while (lock) {
            if (queue.size() < capacity) {
                queue.offer(element);
                lock = false;
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
        return queue.isEmpty();
    }
}
