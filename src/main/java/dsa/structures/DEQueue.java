package dsa.structures;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DEQueue<T> implements Iterable<T> {
    private final int maxSize;
    private int size;

    private DLNode<T> head;
    private DLNode<T> tail;

    public DEQueue(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
    }

    public DEQueue() {
        this(10);
    }

    public DEQUpdate<T> addFront(T data) {
        DEQUpdate<T> update = new DEQUpdate<>(addNodeFront(new DLNode<>(data)));
        size ++;

        if (size > maxSize) {
            update.removed = removeEndNode();
        }

        return update;
    }

    public DLNode<T> addNodeFront(DLNode<T> toAdd) {
        if (this.head == null) {
            this.head = toAdd;
            this.tail = toAdd;
        } else {
            DLNode<T> previousHead = this.head;

            this.head = toAdd;
            this.head.setNext(previousHead);
            previousHead.setPrevious(this.head);
        }

        return toAdd;
    }

    public T removeFront() {
        DLNode<T> toReturn = removeFrontNode();

        return toReturn == null ? null : toReturn.getData();
    }

    public DLNode<T> removeFrontNode() {
        if (this.head == null) {
            return null;
        }

        DLNode<T> toReturn = this.head;
        this.head = this.head.getNext();

        if (this.head != null) {
            this.head.setPrevious(null);
        } else {
            this.tail = null;
        }

        return toReturn;
    }

    public T peekFront() {
        if (this.head == null) {
            return null;
        }

        return this.head.getData();
    }

    public DEQUpdate<T> addEnd(T data) {
        DEQUpdate<T> update = new DEQUpdate<>(addNodeEnd(new DLNode<>(data)));
        size ++;

        if (size > maxSize) {
            update.removed = removeFrontNode();
        }

        return update;

    }

    public DLNode<T> addNodeEnd(DLNode<T> toAdd) {
        if (tail == null) {
            this.head = toAdd;
            this.tail = toAdd;
        } else {
            DLNode<T> previousTail = this.tail;

            this.tail = toAdd;
            this.tail.setPrevious(previousTail);
            previousTail.setNext(this.tail);
        }

        return toAdd;
    }

    public T removeEnd() {
        DLNode<T> endNode = removeEndNode();
        return endNode == null ? null : endNode.getData();
    }

    private DLNode<T> removeEndNode() {
        if (this.tail == null) {
            return null;
        }

        DLNode<T> toReturn = this.tail;
        this.tail = toReturn.getPrevious();

        if (this.tail != null) {
            this.tail.setNext(null);
        } else {
            this.head = null;
        }

        return toReturn;
    }

    public T peekEnd() {
        if (this.tail == null) {
            return null;
        }

        return this.tail.getData();
    }

    public DLNode<T> removeNode(DLNode<T> toRemove) {
        if (toRemove == this.head) {
            return removeFrontNode();
        } else if (toRemove == this.tail) {
            return removeEndNode();
        }

        DLNode<T> prev = toRemove.getPrevious();
        DLNode<T> next = toRemove.getNext();

        prev.setNext(next);
        next.setPrevious(prev);

        return toRemove;
    }

    public DLNode<T> pushFront(DLNode<T> toPush) {
        DLNode<T> removed = this.removeNode(toPush);
        return this.addNodeEnd(removed);
    }

    public static class DEQUpdate<T> {
        DLNode<T> added;
        DLNode<T> removed;

        DEQUpdate(DLNode<T> added) {
            this.added = added;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DEQueueIterator<>(this.head);
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    public static class DEQueueIterator<T> implements Iterator<T> {
        private DLNode<T> current;

        DEQueueIterator(DLNode<T> node) {
            this.current = node;
        }

        @Override
        public boolean hasNext() {
            return this.current.getNext() != null;
        }

        @Override
        public T next() {
            if (this.current == null) {
                return null;
            }

            T t = this.current.getData();
            this.current = this.current.getNext();

            return t;
        }
    }
}
