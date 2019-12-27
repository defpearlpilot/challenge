package dsalgo;

import java.util.*;

public class Dijkstra {
    static class Vertex implements Comparator<Vertex> {
        int name;
        int weight;

        public Vertex(int name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        @Override
        public int compare(Vertex o1, Vertex o2) {
            if (o1.weight < o2.weight) {
                return -1;
            } else if (o1.weight > o2.weight) {
                return 1;
            }

            return 0;
        }
    }

    public Dijkstra() {

    }

//    public void search(int start, Map<Integer, List<Vertex>> adjacency) {
//        for (int i = 0; i < V; i++)
//            dist[i] = Integer.MAX_VALUE;
//
//        // Add source node to the priority queue
//        pq.add(new Node(src, 0));
//
//        // Distance to the source is 0
//        dist[src] = 0;
//        while (settled.size() != V) {
//
//            // remove the minimum distance node
//            // from the priority queue
//            int u = pq.remove().node;
//
//            // adding the node whose distance is
//            // finalized
//            settled.add(u);
//
//            e_Neighbours(u);
//        }
//    }

    public static void main(String[] args) {
        int[] dist;
        Set<Integer> settled;
        PriorityQueue<Vertex> pq;
        int numVertices = 5; // Number of vertices

        Map<Integer, List<Vertex>> adjacency = new HashMap<>(numVertices);

        List<Vertex> zeroList = new LinkedList<>();
        zeroList.add(new Vertex(1, 9));
        zeroList.add(new Vertex(2, 6));
        zeroList.add(new Vertex(3, 5));
        zeroList.add(new Vertex(4, 3));

        adjacency.put(0, zeroList);

        List<Vertex> twoList = new LinkedList<>();

        twoList.add(new Vertex(1, 2));
        twoList.add(new Vertex(3, 4));



    }
}
