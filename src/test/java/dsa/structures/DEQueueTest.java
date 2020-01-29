package dsa.structures;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DEQueueTest {

    @Test
    void addFront() {
        DEQueue<Integer> q = new DEQueue<>();

        q.addFront(1);
        assertSame(1, q.peekFront());
        assertSame(1, q.peekEnd());
    }

    @Test
    void addFrontTwice() {
        DEQueue<Integer> q = new DEQueue<>();

        q.addFront(1);
        q.addFront(2);

        assertSame(1, q.peekEnd());
        assertSame(2, q.peekFront());
    }

    @Test
    void addFrontCapacity() {
        DEQueue<Integer> q = new DEQueue<>(2);

        q.addFront(1);
        q.addFront(2);
        DEQueue.DEQUpdate<Integer> update = q.addFront(3);

        assertSame(2, q.peekEnd());
        assertSame(3, q.peekFront());
        assertSame(1, update.removed.getData());
    }

    @Test
    void removeFront() {
        DEQueue<Integer> q = new DEQueue<>();

        q.addFront(1);
        q.addFront(2);

        assertSame(2, q.removeFront());
    }

    @Test
    void peekFront() {
    }

    @Test
    void addEnd() {
        DEQueue<Integer> q = new DEQueue<>();

        q.addEnd(1);
        q.addEnd(2);
        assertSame(1, q.peekFront());
        assertSame(2, q.peekEnd());
    }

    @Test
    void addEndCapacity() {
        DEQueue<Integer> q = new DEQueue<>(2);

        q.addEnd(1);
        q.addEnd(2);
        DEQueue.DEQUpdate<Integer> update = q.addEnd(3);

        assertSame(2, q.peekFront());
        assertSame(3, q.peekEnd());
        assertSame(1, update.removed.getData());
    }

    @Test
    void removeEnd() {
        DEQueue<Integer> q = new DEQueue<>();

        q.addFront(1);
        q.addFront(2);

        assertSame(1, q.removeEnd());
        assertSame(2, q.peekFront());
        assertSame(2, q.peekEnd());
    }

    @Test
    void peekEnd() {
    }

    @Test
    void removeNode() {
        DEQueue<Integer> q = new DEQueue<>();

        q.addFront(1);
        DEQueue.DEQUpdate<Integer> update = q.addFront(2);
        q.addFront(3);

        Iterator<Integer> iterator = q.iterator();
        assertSame(3, iterator.next());
        assertSame(2, iterator.next());
        assertSame(1, iterator.next());
        assertNull(iterator.next());

        q.removeNode(update.added);

        assertSame(3, q.peekFront());
        assertSame(1, q.peekEnd());

        Iterator<Integer> iteratorR = q.iterator();
        assertSame(3, iteratorR.next());
        assertSame(1, iteratorR.next());
        assertNull(iteratorR.next());
    }
}