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
        System.out.println("Add to queue number " + element);
        queue.offer(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T result = queue.poll();
        System.out.println("Remove element");
        notify();
        return result;
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}