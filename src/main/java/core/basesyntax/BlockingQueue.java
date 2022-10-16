package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private final int capacity;
    private volatile int count = 0;
    private volatile boolean transmissionFlag = false;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        transmissionFlag = true;
    }

    public synchronized void put(T element) throws InterruptedException {
        // write your code here
        System.out.println(Thread.currentThread().getName() + " BlockingQueue.put method: "
                                   + "element=" + element);
        while (count >= capacity) {
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " BlockingQueue.put NOTIFY()");
        queue.add(element);
        count += 1;
        System.out.println(Thread.currentThread().getName() + " Add to Queue element and count="
                                   + count);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        // write your code here
        while (count <= 0) {
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " BlockingQueue.take method: "
                                   + "count=" + count);
        T element = queue.poll();
        System.out.println(Thread.currentThread().getName() + "BlockingQueue.take method "
                                   + "return: "
                                   + "element=" + element);
        count -= 1;
        notify();
        return element;
    }

    public synchronized boolean isEmpty() {
        // write your code here
        System.out.println(Thread.currentThread().getName() + " !!!!! BlockingQueue.isEmpty method "
                                    + " count=" + count
                                    + " transmissionFlag=" + transmissionFlag
                                    + " return " + (count <= 0 && !transmissionFlag));
        return count <= 0;// && !transmissionFlag;
    }

    public synchronized void endTransmission() {
        System.out.println(Thread.currentThread().getName() + " BlockingQueue.endTransmission "
                                   + "method called ........ ");
        transmissionFlag = false;
    }
}
