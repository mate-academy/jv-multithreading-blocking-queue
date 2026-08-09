package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        System.out.println("Trying to put the new element...");
        while (queue.size() >= capacity) {
            System.out.println(Thread.currentThread().getName() + " is waiting...");
            wait();
        }
        System.out.println(Thread.currentThread().getName()
                + " is putting the new element " + element);
        queue.add(element);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " is waiting...");
            wait();
        }
        System.out.println(Thread.currentThread().getName()
                + " is trying to peek an element: " + queue.peek());
        T t = queue.peek();
        queue.remove();
        notifyAll();
        return t;
    }

    public synchronized boolean isEmpty() {
        System.out.println(Thread.currentThread().getName() + " is trying to check isEmpty()");
        return queue.isEmpty();
    }
}
