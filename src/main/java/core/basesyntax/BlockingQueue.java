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
        System.out.println("Add number to the queue: " + element);
        queue.offer(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T output = queue.poll();
        System.out.println("Take element");
        notify();
        return output;
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
