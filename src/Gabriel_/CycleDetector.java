package Gabriel_;

import java.util.ArrayList;
import java.util.Stack;

public class CycleDetector {
    public static boolean isCyclic(ArrayList<Integer>[] adjList) {
        return isCyclic(adjList, 0);
    }
    public static boolean isCyclic(ArrayList<Integer>[] adjList, int v) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adjList.length];
        stack.push(v);
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
}
