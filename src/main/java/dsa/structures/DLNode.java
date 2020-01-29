package dsa.structures;

public class DLNode<T> {
    private T data;
    private DLNode<T> next;
    private DLNode<T> previous;

    public DLNode(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;

    }

    public void setNext(DLNode<T> next) {
        this.next = next;
    }

    public DLNode<T> getNext() {
        return this.next;
    }

    public void setPrevious(DLNode<T> previous) {
        this.previous = previous;
    }

    public DLNode<T> getPrevious() {
        return this.previous;
    }
}
