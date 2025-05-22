package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;
    private final Object lok = new Object();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        // write your code here
        synchronized (lok) {
            while (queue.size() == capacity) {
                System.out.println("Queue is full, waiting for space");
                System.out.println("Put method: current capacity: " + queue.size());
                lok.wait();
            }
            lok.notifyAll();
            queue.add(element);
        }
    }

    public synchronized T take() throws InterruptedException {
        // write your code here
        synchronized (lok) {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty, waiting for element");
                System.out.println("take methode: current capacity: " + queue.size());
                lok.wait();
            }
            lok.notifyAll();
            return queue.remove();
        }
    }

    public synchronized boolean isEmpty() {
        // write your code here
        System.out.println("Checking is queue empty...");
        System.out.println("isEmpty method: current capacity: " + queue.size());
        return queue.isEmpty();
    }
}
