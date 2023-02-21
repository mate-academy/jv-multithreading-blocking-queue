package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;
    private boolean flag = true;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        // write your code here
        while (!flag) {
            wait();
        }
        queue.offer(element);
        if (queue.size() == capacity) {
            flag = false;
            notify();
        }
    }

    public synchronized T take() throws InterruptedException {
        // write your code here
        while (flag) {
            wait();
        }
        if (queue.size() == 1) {
            flag = true;
            notify();
        }
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        // write your code here
        return queue.isEmpty();
    }
}
