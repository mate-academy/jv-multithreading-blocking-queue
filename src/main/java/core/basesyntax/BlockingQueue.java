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
            System.out.println("Try to put but queue are full");
            wait();
        }
        queue.add(element);
        notify();
        System.out.println("Put element in queue");
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Try to take but queue are empty");
            wait();
        }
        T element = queue.poll();
        notify();
        System.out.println("Take element");
        return element;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
