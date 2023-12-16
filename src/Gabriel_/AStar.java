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
     * @param h Function that calculates the heuristic
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

    public record Vert(int x, int y, int distance) {}

    private static  Vert[] summer = {
            new Vert(0,1, 0),
            new Vert(1,0, 0),
            new Vert(-1,0, 0),
            new Vert(0,-1, 0)};
    public static int aStar(boolean[][] graph, int vX, int vY, int wX, int wY) {
        PriorityQueue<Vert> pq = new PriorityQueue<>(Comparator.comparingInt(Vert::distance));
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        int[][] minDist = new int[graph.length][graph[0].length];
        int[][] ancestor = new int[graph.length][graph[0].length];
        for (int i = 0; i <graph.length; i++) {
            Arrays.fill(minDist[i], Integer.MAX_VALUE);
        }

        minDist[vY][vX] = 0;
        pq.add(new Vert(vX, vY, 0));
        while (!pq.isEmpty()) {
            Vert current = pq.remove();
            int currentDist = minDist[current.y][current.x] + 1;
            if (current.x == wX && current.y == wY) {
                return minDist[wY][wX];
            }
            visited[current.y][current.x] = true;

            for (Vert vert : summer) {
                int x = current.x + vert.x;
                int y = current.y + vert.y;
                if (x >= graph[0].length || y >= graph.length || x < 0 || y < 0)
                    continue;
                if (visited[y][x] || !graph[y][x])
                    continue;

                if (minDist[y][x] <= currentDist) {
                    continue;
                }
                int heu = (wX - x)*(wX - x) + (wY - y)*(wY - y);
                if (minDist[y][x] != Integer.MAX_VALUE) {
                    pq.remove(new Vert(x, y, minDist[y][x] + heu));
                }
                minDist[y][x] = currentDist;
                pq.add(new Vert(x, y, minDist[y][x] + heu));
            }

        }
        return Integer.MAX_VALUE;
    }
}
