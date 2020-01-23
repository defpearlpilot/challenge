package dsa.structures;

public class Node<T> {
    T data;
    Node<T> next;

    public Node(T d) {
        data = d;
    }

    public Node<T> setNext(Node<T> next) {
        this.next = next;
        return this.next;
    }

    public Node<T> getNext() {
        return this.next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
