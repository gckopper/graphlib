import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim {
    public static int[] prim(ArrayList<Main.Edge>[] adjList, int v) {
        PriorityQueue<Main.Vert> pq = new PriorityQueue<>(Comparator.comparingInt(Main.Vert::distance));
        boolean[] visited = new boolean[adjList.length];
        int[] minDist = new int[adjList.length];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[v] = 0;
        pq.add(new Main.Vert(v, 0));
        while (!pq.isEmpty()) {
            Main.Vert current = pq.remove();
            int currentDist = minDist[current.v()];

            if (visited[current.v()])
                continue;
            visited[current.v()] = true;
            ArrayList<Main.Edge> adj = adjList[current.v()];

            for (Main.Edge edge : adj) {
                if (visited[edge.d()])
                    continue;
                int totalDistance = edge.weight();
                if (minDist[edge.d()] == Integer.MAX_VALUE) {
                    minDist[edge.d()] = totalDistance;
                    pq.add(new Main.Vert(edge.d(), minDist[edge.d()]));
                    continue;
                }
                if (minDist[edge.d()] <= totalDistance) {
                    continue;
                }
                pq.remove(new Main.Vert(edge.d(), minDist[edge.d()]));
                minDist[edge.d()] = totalDistance;
                pq.add(new Main.Vert(edge.d(), minDist[edge.d()]));
            }
        }
        return minDist;
    }

    public static int[] prim(ArrayList<Main.Edge>[] adjList) {
        return prim(adjList, 0);
    }
}
