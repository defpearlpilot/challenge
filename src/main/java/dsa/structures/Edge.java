package dsa.structures;

public class Edge<T> {
    private Vertex<T> from;
    private Vertex<T> to;

    public Edge(Vertex<T> _from, Vertex<T> _to) {
        this.from = _from;
        this.to = _to;
    }

    public Vertex<T> getFrom() {
        return from;
    }

    public Vertex<T> getTo() {
        return to;
    }
}
