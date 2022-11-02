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
            System.out.println("Can't put because queue are full");
            wait();
        }
        queue.add(element);
        notify();
        System.out.println("Put element to queue");
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Can't take because queue is empty");
            wait();
        }
        T element = queue.poll();
        notify();
        System.out.println("Take element from queue");
        return element;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
