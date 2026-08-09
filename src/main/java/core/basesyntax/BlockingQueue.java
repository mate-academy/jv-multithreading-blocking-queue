package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == capacity) {
                try {
                    System.out.println("Before wait put   " + Thread.currentThread().getName());
                    queue.wait(); // Wait on the queue's intrinsic lock
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.add(element);
            queue.notifyAll(); // Notify other threads waiting on this lock
        }
    }

    public T take() throws InterruptedException {
        synchronized (queue) { // Synchronize on the queue object
            while (queue.isEmpty()) {
                try {
                    System.out.println("Before wait take");
                    queue.wait(); // Wait on the queue's intrinsic lock
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            T element = queue.remove();
            queue.notifyAll(); // Notify other threads waiting on this lock
            return element;
        }
    }

    public synchronized boolean isEmpty() {
        synchronized (queue) { // Synchronize on the queue object
            return queue.isEmpty();
        }
    }
}
