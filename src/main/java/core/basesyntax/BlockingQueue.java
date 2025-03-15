package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private Object locker = new Object();
    private int capacity;
    private AtomicInteger size = new AtomicInteger(0);

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) throws InterruptedException {
        synchronized (locker) {
            while (isFull()) {
                locker.wait();
            }

            queue.add(element);
            size.incrementAndGet();
            locker.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        synchronized (locker) {
            while (isEmpty()) {
                locker.wait();
            }

            T peek = queue.peek();
            size.decrementAndGet();
            locker.notifyAll();

            return peek;
        }
    }

    public synchronized boolean isFull() {
        return size.get() == capacity;
    }

    public synchronized boolean isEmpty() {
        return size.get() == 0;
    }
}
