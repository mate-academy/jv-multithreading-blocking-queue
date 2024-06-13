package core.basesyntax;

import java.io.File;
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
            this.wait(); // Main thread waits here
            System.out.println("The put method is waiting");
        }
        queue.add(element);
        this.notifyAll();
    }

    public synchronized T take() throws InterruptedException {
       while (isEmpty()) {
           this.wait(); // Main thread waits here
           System.out.println("The take method is waiting");
       }
       try {
           return queue.poll();
       } finally {
           this.notifyAll();
       }
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
