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
        System.out.println("Trying to put element: " + element);
        while (queue.size() >= capacity) {
            System.out.println("Waiting in put() method");
            wait();
        }
        System.out.println("Put element: " + element);
        queue.add(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        System.out.println("Trying to take element");
        while (queue.isEmpty()) {
            System.out.println("Waiting in take() method");
            wait();
        }
        T result = queue.poll();
        System.out.println("Took element: " + result);
        notify();
        return result;
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
