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
        while (queue.size() >= capacity) {
            System.out.println("will wait in put() method");
            wait();
        }
        queue.add(element);
        System.out.println("added " + element);
        this.notify();
    }

    public synchronized T take() throws InterruptedException {
        T removed = queue.remove();
        this.notify();
        return removed;
    }

    public synchronized boolean isEmpty() {
        while (queue.isEmpty()) {
            try {
                System.out.println("will wait in isEmpty() check");
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
