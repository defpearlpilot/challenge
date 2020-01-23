package dsa.structures;

public class DLNode<T> {
    private T data;
    private Node<T> next;
    private Node<T> previous;

    public DLNode(T data) {
        this.data = data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public Node<T> getPrevious() {
        return this.previous;
    }
}
