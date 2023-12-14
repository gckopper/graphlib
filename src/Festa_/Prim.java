package Festa_;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Graph {
	// classe edge
	record Edge(int d, int w) implements Comparable<Edge> {
		@Override
		public int compareTo(Edge edge) {
			return Integer.compare(this.w, edge.w);
		}
	}

	// atributos
	int v;
	ArrayList<Edge>[] graph;

	// contrutor + inicializa grafo
	public Graph(int v) {
		this.v = v;
		this.graph = new ArrayList[v];
		for (int i = 0; i < this.graph.length; i++) {
			this.graph[i] = new ArrayList<>();
		}
	}

	public void adicionarAresta(int u, int d, int w) {
		graph[u].add(new Edge(d, w));
		graph[d].add(new Edge(u, w));
	}

	public void prim(int source) {
		boolean[] visitados = new boolean[v];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int mst = 0;
		for (int i = 0; i < graph[source].size(); i++) {
			pq.add(graph[source].get(i));
		}
		visitados[source] = true;
		while (!pq.isEmpty()) {
			Edge atual = pq.poll();
			if (!visitados[atual.d]) {
				visitados[atual.d] = true;
				mst += atual.w;
				for (int i = 0; i < graph[atual.d].size(); i++) {
					pq.add(graph[atual.d].get(i));
				}
			}
		}
		System.out.println(mst);
	}
}

public class Prim {
	public static void main(String[] args) {
		Graph graph = new Graph(9);
		graph.adicionarAresta(0, 2, 7);
		graph.adicionarAresta(0, 3, 2);
		graph.adicionarAresta(2, 3, 6);
		graph.adicionarAresta(1, 2, 3);
		graph.adicionarAresta(1, 5, 6);
		graph.adicionarAresta(5, 2, 9);
		graph.adicionarAresta(5, 3, 15);
		graph.adicionarAresta(3, 6, 5);
		graph.adicionarAresta(6, 8, 11);
		graph.adicionarAresta(6, 7, 13);
		graph.adicionarAresta(5, 6, 20);
		graph.adicionarAresta(5, 7, 10);
		graph.adicionarAresta(7, 4, 14);
		graph.adicionarAresta(4, 1, 8);
		graph.adicionarAresta(4, 8, 9);
		graph.adicionarAresta(7, 8, 10);
		graph.adicionarAresta(4, 5, 7);

		graph.prim(0);

	}
}