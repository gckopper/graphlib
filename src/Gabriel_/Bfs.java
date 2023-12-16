package Gabriel_;

import java.util.*;

public class Bfs {
    public static int[] bfs(ArrayList<Integer>[] adjList, int v) {
        int[] distances = new int[adjList.length];
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adjList.length];
        q.add(v);
        distances[v] = 0;
        visited[v] = true;
        while (!q.isEmpty()) {
            int current = q.remove();
            for (Integer i:
                    adjList[current]) {
                if (visited[i])
                    continue;
                q.add(i);
                visited[i] = true;
                distances[i] = distances[current] + 1;
            }
        }
        return distances;
    }
}
