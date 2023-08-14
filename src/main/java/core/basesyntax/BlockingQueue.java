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
                throw new RuntimeException("Can't wait when put new element in queue");
            }
        }
        System.out.println(
                "Add element to queue to position: " + queue.size() + " with size " + capacity);
        queue.add(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Can't wait when put new element in queue");
            }
        }
        System.out.println(
                "Remove element to queue to position: " + queue.size() + " with size " + capacity);
        notify();
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        notify();
        return queue.isEmpty();
    }
}
