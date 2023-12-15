package Gabriel_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {
    /**
     * @param adjList Adjacency list for weighted edges
     * @param v Source for the algorithm
     * @param w Destination vertex
     * @return Distance from source to each vertex
     */
    public static int aStar(ArrayList<Main.Edge>[] adjList, int v, int w, Heuristic h) {
        PriorityQueue<Main.Vert> pq = new PriorityQueue<>(Comparator.comparingInt(Main.Vert::distance));
        boolean[] visited = new boolean[adjList.length];
        int[] minDist = new int[adjList.length];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[v] = 0;
        pq.add(new Main.Vert(v, 0));
        while (!pq.isEmpty()) {
            Main.Vert current = pq.remove();
            int currentDist = minDist[current.v()];
            if (current.v() == w) {
                return minDist[w];
            }
            visited[current.v()] = true;
            ArrayList<Main.Edge> adj = adjList[current.v()];

            for (Main.Edge edge : adj) {
                if (visited[edge.d()])
                    continue;
                int totalDistance = edge.weight() + currentDist;

                if (minDist[edge.d()] <= totalDistance) {
                    continue;
                }
                int heu = h.heuristic(w, edge.d());
                if (minDist[edge.d()] != Integer.MAX_VALUE) {
                    pq.remove(new Main.Vert(edge.d(), minDist[edge.d()] + heu));
                }
                minDist[edge.d()] = totalDistance;
                pq.add(new Main.Vert(edge.d(), minDist[edge.d()] + heu));
            }
        }
        return Integer.MAX_VALUE;
    }

    public interface Heuristic {
        int heuristic(int fin, int b);
    }
}
