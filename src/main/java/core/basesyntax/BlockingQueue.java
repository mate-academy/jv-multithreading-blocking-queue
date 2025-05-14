package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;
    private final Object lock = new Object();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == capacity) {
                System.out.println("Queue is full, waiting for space");
                System.out.println("Put method: current capacity: " + queue.size());
                lock.wait();
            }
            lock.notifyAll();
            queue.add(element);
        }
    }

    public T take() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty, waiting for element");
                System.out.println("take method: current capacity: " + queue.size());
                lock.wait();
            }
            lock.notifyAll();
            return queue.remove();
        }
    }

    public boolean isEmpty() {
        System.out.println("Checking is queue empty...");
        System.out.println("isEmpty method: current capacity: " + queue.size());
        return queue.isEmpty();
    }
}
