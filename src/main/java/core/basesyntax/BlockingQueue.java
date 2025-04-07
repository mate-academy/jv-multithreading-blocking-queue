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
        while (queue.size() == capacity) {
            wait(); // Чекаємо, поки з'явиться місце
        }
        queue.add(element);
        notifyAll(); // Повідомляємо інші потоки
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Чекаємо, поки з'явиться елемент
        }
        T item = queue.poll();
        notifyAll(); // Повідомляємо інші потоки
        return item;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
