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
            System.out.println("Queue is full, waiting to put element: " + element);
            wait();
        }
        queue.add(element);
        System.out.println("Added element: " + element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        // write your code here
        while (queue.isEmpty()) {
            System.out.println("Queue is empty, waiting to take element");
            wait();
        }
        T element = queue.poll();
        System.out.println("Took element: " + element);
        notify();
        return element;
    }

    public synchronized boolean isEmpty() {
        while (!queue.isEmpty()) {
            return false;
        }
        return true;
    }
}
