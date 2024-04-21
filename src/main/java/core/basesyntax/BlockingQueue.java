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
        while (true) {
            if (queue.size() < capacity) {
                queue.add(element);
                notify();
                break;
            }
            {
                wait();
            }
        }
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T polled = queue.poll();
        notify();
        return polled;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
