package Gabriel_;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public record Edge(int d, int weight) {}
    public record Vert(int v, int distance) {}
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

}