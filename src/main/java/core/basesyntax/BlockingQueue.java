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
            this.wait();
        }

        queue.add(element);
        this.notify();
        System.out.println("inserted el: " + element + " and size is : " + queue.size());
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }

        System.out.println("size is : " + queue.size());
        this.notify();
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
