package Festa_;

import java.util.ArrayList;
import java.util.Collections;

public class Dfs {
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) {
		int[][] edges = { { 1, 0 }, { 6, 0 }, { 0, 8 }, { 8, 6 }, { 1, 8 }, { 6, 2 }, { 4, 5 }, { 8, 5 }, { 3, 1 },
				{ 3, 7 } };
		int countV = 9;
		graph = new ArrayList[countV];
		res(countV, edges);
	}

	static void res(int countV, int[][] edges) {
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < edges.length; i++) {
			graph[edges[i][0]].add(edges[i][1]);
			graph[edges[i][1]].add(edges[i][0]);
		}

		for (int i = 0; i < graph.length; i++) {
			Collections.sort(graph[i]);
		}

		boolean[] visitados = new boolean[countV];
		dfs(0, visitados);
	}

	static void dfs(int u, boolean[] visitados) {
		visitados[u] = true;
		System.out.println(u);
		for (int i = 0; i < graph[u].size(); i++) {
			if (!visitados[graph[u].get(i)]) {
				dfs(graph[u].get(i), visitados);
			}
		}
	}
}
