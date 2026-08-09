package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Puts an element into the queue. If the queue is full, the calling thread
     * will be blocked until there is space available.
     * @param element the element to be added to the queue
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public synchronized void put(T element) throws InterruptedException {
        // The condition to wait for is the queue being full.
        // We must check this condition in a while loop to handle spurious wake-ups.
        while (queue.size() == capacity) {
            wait();
        }

        queue.add(element);

        // After adding an element, the queue is no longer empty.
        // We notify any threads that might be waiting to take an element (consumers).
        // notifyAll() is used to wake up all waiting threads.
        notifyAll();
    }

    /**
     * Retrieves and removes the head of this queue. If the queue is empty, the
     * calling thread will be blocked until an element becomes available.
     * @return the head of this queue
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public synchronized T take() throws InterruptedException {
        // The condition to wait for is the queue being empty.
        // The while loop handles spurious wake-ups.
        while (queue.isEmpty()) {
            wait();
        }

        T element = queue.poll();

        // After taking an element, the queue is no longer full.
        // We notify any threads that might be waiting to put an element (producers).
        notifyAll();
        return element;
    }

    /**
     * Checks if the queue is empty. This method must be synchronized to ensure
     * memory visibility of the queue's state across threads.
     * @return true if the queue is empty, false otherwise
     */
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
