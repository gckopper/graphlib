package Gabriel_;

import java.util.ArrayList;
import java.util.Stack;

public class Dfs {
    public static int[][] dfs(ArrayList<Integer>[] adjList) {
        return  dfs(adjList, 0);
    }

    public static int[][] dfs(ArrayList<Integer>[] adjList, int v) {
        int[][] result = new int[2][adjList.length];
        dfs(adjList, v, 1, result[0], result[1]);
        return result;
    }
    // Recursive version
    public static int dfs(ArrayList<Integer>[] adjList, int v, int i, int[] d, int[] f) {
        d[v] = i;
        i++;
        for (int w : adjList[v]) {
            if (d[w] == 0) {
                i = dfs(adjList, w, i, d, f);
            }
        }
        f[v] = i;
        i++;
        return i;
    }

    public static int[] dfsNoRecursion(ArrayList<Integer>[] adjList, int v) {
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
}
