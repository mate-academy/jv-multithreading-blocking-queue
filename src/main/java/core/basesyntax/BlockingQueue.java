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
                throw new RuntimeException("InterruptedException in method put");
            }
        }
        queue.add(element); // when there is free space in the queue -> add
        notify(); // notify consumer thread if it is in waiting mode
    }

    public synchronized T take() throws InterruptedException {
        while (this.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("InterruptedException in method take");
            }
        }
        notify();
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
