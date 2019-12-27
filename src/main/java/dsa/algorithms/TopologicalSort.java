package dsa.algorithms;

import dsa.structures.Graph;
import dsa.structures.Vertex;

import java.util.*;

public class TopologicalSort {
    public static Graph<Integer> createGraphA() {
        Graph<Integer> g = new Graph<>();

        Vertex<Integer> vertexA = new Vertex<>("A", 0);
        Vertex<Integer> vertexB = new Vertex<>("B", 1);
        Vertex<Integer> vertexC = new Vertex<>("C", 2);
        Vertex<Integer> vertexD = new Vertex<>("D", 3);
        Vertex<Integer> vertexE = new Vertex<>("E", 4);
        Vertex<Integer> vertexF = new Vertex<>("F", 5);
        Vertex<Integer> vertexG = new Vertex<>("G", 6);

        g.addVertex(vertexA);
        g.addVertex(vertexB);
        g.addVertex(vertexC);
        g.addVertex(vertexD);
        g.addVertex(vertexE);
        g.addVertex(vertexF);
        g.addVertex(vertexG);

        g.addEdge(vertexA, vertexC);

        g.addEdge(vertexB, vertexC);
        g.addEdge(vertexB, vertexE);

        g.addEdge(vertexC, vertexD);

        g.addEdge(vertexD, vertexF);

        g.addEdge(vertexE, vertexF);

        g.addEdge(vertexF, vertexG);

        return g;
    }

    public static Graph<Integer> createGraphB() {
        Graph<Integer> g = new Graph<>();

        Vertex<Integer> vertexA = new Vertex<>("A", 0);
        Vertex<Integer> vertexB = new Vertex<>("B", 1);
        Vertex<Integer> vertexC = new Vertex<>("C", 2);
        Vertex<Integer> vertexD = new Vertex<>("D", 3);
        Vertex<Integer> vertexE = new Vertex<>("E", 4);
        Vertex<Integer> vertexF = new Vertex<>("F", 5);
        Vertex<Integer> vertexG = new Vertex<>("G", 6);
        Vertex<Integer> vertexH = new Vertex<>("H", 6);

        g.addVertex(vertexA);
        g.addVertex(vertexB);
        g.addVertex(vertexC);
        g.addVertex(vertexD);
        g.addVertex(vertexE);
        g.addVertex(vertexF);
        g.addVertex(vertexG);
        g.addVertex(vertexH);

        g.addEdge(vertexA, vertexC);

        g.addEdge(vertexB, vertexC);
        g.addEdge(vertexB, vertexD);

        g.addEdge(vertexC, vertexE);

        g.addEdge(vertexD, vertexF);

        g.addEdge(vertexE, vertexF);
        g.addEdge(vertexE, vertexH);

        g.addEdge(vertexF, vertexG);

        return g;
    }

    static class SortState<T> {
        Deque<Vertex<T>> remaining;

        Set<Vertex<T>> visited;
        Deque<Vertex<T>> stack;

        public SortState(Deque<Vertex<T>> _remaining,
                         Set<Vertex<T>> _visited,
                         Deque<Vertex<T>> _stack) {
            this.remaining = _remaining;
            this.visited = _visited;
            this.stack = _stack;
        }
    }

    public static <T> List<Vertex<T>> topologicalSort(Graph<T> graph) {
        SortState<T> sortState = new SortState<>(new ArrayDeque<>(graph.getVertices()), new HashSet<>(), new ArrayDeque<>());

        while (!sortState.remaining.isEmpty()) {
            Vertex<T> v = sortState.remaining.remove();

            if (sortState.visited.contains(v)) {
                sortState.remaining.remove(v);
                continue;
            }

            sortState.visited.add(v);
            sortState.remaining.remove(v);

            visit(sortState, graph, v);
        }

        List<Vertex<T>> toReturn = new ArrayList<>(sortState.stack.size());
        while(!sortState.stack.isEmpty()) {
            toReturn.add(sortState.stack.removeLast());
        }

        return toReturn;
    }

    private static <T> void visit(SortState<T> sortState, Graph<T> graph, Vertex<T> vertex) {
        Set<Vertex<T>> adjacentVertices = graph.getAdjacentVertices(vertex);
        for (Vertex<T> v: adjacentVertices) {
            if (sortState.visited.contains(v)) {
                continue;
            }

            sortState.visited.add(v);
            sortState.remaining.remove(v);

            visit(sortState, graph, v);
        }

        System.out.println("Pushing " + vertex.getName());
        sortState.stack.push(vertex);
    }

    public static void main(String[] args) {
        Graph<Integer> g = createGraphB();

        List<Vertex<Integer>> sorted = topologicalSort(g);
        System.out.println("Done: " + sorted);
    }
}
