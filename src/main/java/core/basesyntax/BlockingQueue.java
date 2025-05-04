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
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread " + Thread.currentThread().getName()
                        + " was interrupted while waiting to add an element to the queue", e);
            }
        }
        System.out.println(
                "Add element " + element + " queue size before adding = " + queue.size());
        queue.add(element);
        System.out.println("queue size after adding " + queue.size());
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread " + Thread.currentThread().getName()
                        + " was interrupted while waiting for an element", e);
            }
        }
        T element = queue.poll();
        notify();
        return element;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
