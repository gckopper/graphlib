import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        // int[][] graph = {{1,7}, {0,2}, {1,3}, {2,4}, {3,5}, {4,6}, {5,7}, {6,0}};
        int[][] graph = {{2,4,5}, {2,3}, {0,1,3,5}, {1,2}, {0,5}, {0,2,4}};
        ArrayList<Integer>[] adjList = new ArrayList[graph.length];
        for (int i = 0; i < graph.length; i++) {
            adjList[i] = new ArrayList<Integer>();
            for (int j:
                    graph[i]) {
                adjList[i].add(j);
            }
        }

        int[][] a = dfsSearch(adjList);
        System.out.println(Arrays.toString(a[0]));
        System.out.println(Arrays.toString(a[1]));
        System.out.println("No recursion version");

        int[] b = dfsSearchNoRecursion(adjList, 0);
        System.out.println(Arrays.toString(b));
    }

    public static ArrayList<Integer>[] toAdjList(int[][] edges, int countV) {
        ArrayList<Integer>[] result = new ArrayList[countV];
        for (int i = 0; i < result.length; i++) {
            result[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            result[edge[0]].add(edge[1]);
            result[edge[1]].add(edge[0]);
        }
        for (ArrayList<Integer> integers : result) {
            Collections.sort(integers);
        }
        return result;
    }
    public static ArrayList<Integer>[] toAdjListDirected(int[][] edges, int countV) {
        ArrayList<Integer>[] result = new ArrayList[countV];
        for (int i = 0; i < result.length; i++) {
            result[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            result[edge[0]].add(edge[1]);
        }
        for (ArrayList<Integer> integers : result) {
            Collections.sort(integers);
        }
        return result;
    }
    public static ArrayList<Edge>[] toAdjListDirectedWeighted(int[][] edges, int countV) {
        ArrayList<Edge>[] result = new ArrayList[countV];
        for (int i = 0; i < result.length; i++) {
            result[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            result[edge[0]].add(new Edge(edge[1], edge[2]));
        }
        for (ArrayList<Edge> edgeArrayList : result) {
            edgeArrayList.sort(Comparator.comparingInt(a -> a.weight));
        }
        return result;
    }
    public static int[][] dfsSearch(ArrayList<Integer>[] adjList) {
        return  dfsSearch(adjList, 0);
    }

    public static int[][] dfsSearch(ArrayList<Integer>[] adjList, int v) {
        int[][] result = new int[2][adjList.length];
        dfsSearch(adjList, 0, 1, result[0], result[1]);
        return result;
    }
    // Recursive version
    public static int dfsSearch(ArrayList<Integer>[] adjList, int v, int i, int[] d, int[] f) {
        d[v] = i;
        i++;
        for (int w : adjList[v]) {
            if (d[w] == 0) {
                i = dfsSearch(adjList, w, i, d, f);
            }
        }
        f[v] = i;
        i++;
        return i;
    }

    public static int[] dfsSearchNoRecursion(ArrayList<Integer>[] adjList, int v) {
        Stack<Integer> stack = new Stack<>();
        int[] d = new int[adjList.length];
        int i = 1;
        stack.push(v);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (d[current] != 0)
                continue;
            d[current] = i;
            i++;
            for (int j = adjList[current].size() - 1; j >= 0; j--) {
                if (d[adjList[current].get(j)] == 0)
                    stack.push(adjList[current].get(j));
            }
        }
        return d;
    }

    public static boolean isCyclic(ArrayList<Integer>[] adjList) {
        return isCyclic(adjList, 0);
    }
    public static boolean isCyclic(ArrayList<Integer>[] adjList, int v) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adjList.length];
        stack.push(v);
        int father = -1;
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (visited[current])
                return true;
            visited[current] = true;
            for (int j = adjList[current].size() - 1; j >= 0; j--) {
                if (!visited[adjList[current].get(j)])
                    stack.push(adjList[current].get(j));
            }
        }
        return false;
    }
    public record Edge(int d, int weight) {}
    public record Vert(int v, int distance) {}
    public static int[] dijkstra(ArrayList<Edge>[] adjList, int v) {
        PriorityQueue<Vert> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));
        boolean[] visited = new boolean[adjList.length];
        int[] minDist = new int[adjList.length];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[v] = 0;
        pq.add(new Vert(v, 0));
        while (!pq.isEmpty()) {
            Vert current = pq.remove();
            int currentDist = minDist[current.v];

            if (visited[current.v])
                continue;
            visited[current.v] = true;
            ArrayList<Edge> adj = adjList[current.v];

            for (Edge edge : adj) {
                if (visited[edge.d])
                    continue;
                int totalDistance = edge.weight + currentDist;
                if (minDist[edge.d] == Integer.MAX_VALUE) {
                    minDist[edge.d] = totalDistance;
                    pq.add(new Vert(edge.d, minDist[edge.d]));
                    continue;
                }
                if (minDist[edge.d] <= totalDistance) {
                    continue;
                }
                pq.remove(new Vert(edge.d, minDist[edge.d]));
                minDist[edge.d] = totalDistance;
                pq.add(new Vert(edge.d, minDist[edge.d]));
            }
        }
        return minDist;
    }
}