package dsa.structures;

import java.util.*;
import java.util.stream.Collectors;

public class Graph<T> {
    Set<Vertex<T>> vertexSet = new LinkedHashSet<>();
    Map<Vertex<T>, List<Edge<T>>> adjacencyMap = new HashMap<>();

    public void addVertex(Vertex<T> vertex) {
        vertexSet.add(vertex);
    }

    public void addEdge(Vertex<T> from, Vertex<T> to) {
        Edge<T> edge = new Edge<>(from, to);
        List<Edge<T>> neighbors = adjacencyMap.computeIfAbsent(from, k -> new LinkedList<>());
        neighbors.add(edge);
    }

    public Set<Vertex<T>> getVertices() {
        return vertexSet;
    }

    public Set<Vertex<T>> getAdjacentVertices(Vertex<T> vertex) {
        List<Edge<T>> edges = adjacencyMap.get(vertex);
        if (edges == null) {
            return Set.of();
        }

        return edges
                .stream()
                .map(Edge::getTo)
                .collect(Collectors.toSet());
    }
}
