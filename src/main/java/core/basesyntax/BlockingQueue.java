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
            wait();
        }
        queue.add(element);
        System.out.println(Thread.currentThread().getName() + " add " + element);
        if (isEmpty() || queue.size() != capacity) {
            notify();
        }
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        if (queue.size() == capacity) {
            notify();
        }
        System.out.println(Thread.currentThread().getName() + " remove " + queue.peek());
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
