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
        while (queue.size() >= this.capacity) {
            System.out.println("Queue is overload");
            this.wait();
        }
        queue.add(element);
        this.notifyAll();
        System.out.println("Added element " + element);

    }

    public synchronized T take() throws InterruptedException {
        try {
            while (this.isEmpty()) {
                this.wait();
            }
            T pooled = queue.poll();
            this.notifyAll();
            return pooled;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized boolean isEmpty() {
        // write your code here
        return queue.isEmpty();
    }
}
