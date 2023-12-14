package Festa_;

import java.util.ArrayList;
import java.util.LinkedList;

public class Bfs {

	public static void main(String[] args) {

		int[][] edges = { { 1, 0 }, { 3, 0 }, { 1, 2 }, { 3, 4 }, { 3, 5 }, { 4, 5 }, { 4, 6 }, { 5, 7 }, { 6, 7 } };
		int[][] edges1 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 4 }, { 2, 5 } };
		int[][] edges2 = { { 0, 1 }, { 0, 6 }, { 0, 8 }, { 8, 6 }, { 1, 8 }, { 6, 2 }, { 4, 5 }, { 8, 5 }, { 1, 3 },
				{ 4, 7 }, { 3, 7 }, { 1, 4 } };
		res(9, edges2);
	}

	static void res(int countV, int[][] edges) {
		ArrayList<Integer>[] graph = new ArrayList[countV];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < edges.length; i++) {
			graph[edges[i][0]].add(edges[i][1]);
			graph[edges[i][1]].add(edges[i][0]);
		}

		boolean[] visitados = new boolean[countV];
		LinkedList<Integer> list = new LinkedList<>();
		bfs(0, visitados, graph, list);
	}

	static void bfs(int u, boolean[] visitados, ArrayList<Integer>[] graph, LinkedList<Integer> list) {
		if (!visitados[u]) {
			visitados[u] = true;
			System.out.println(u);
			for (int i = 0; i < graph[u].size(); i++) {
				if (!visitados[graph[u].get(i)]) {
					list.add(graph[u].get(i));
				}
			}
			while (!list.isEmpty()) {
				bfs(list.remove(0), visitados, graph, list);
			}
		}
	}

}
