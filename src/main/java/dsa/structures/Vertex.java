package dsa.structures;

public class Vertex<T> {
    private String name;
    private T data;

    public Vertex(String _name, T _data) {
        this.name = _name;
        this.data = _data;
    }

    public String getName() {
        return name;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
