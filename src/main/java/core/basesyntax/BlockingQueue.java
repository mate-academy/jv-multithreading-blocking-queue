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
        System.out.println("Put before " + capacity + " size " + queue.size());
        while (capacity <= queue.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new InterruptedException("put interrupted");
            }
        }
        System.out.println("Put after wait");
        notify();
        queue.add(element);
    }

    public synchronized T take() throws InterruptedException {
        System.out.println("Take before ");
        while (isEmpty() || (capacity > queue.size())) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new InterruptedException("take interrupted");
            }
        }
        // write your code here
        System.out.println("Take after wait");
        notify();
        return queue.element();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
