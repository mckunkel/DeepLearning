package depthfirstsearch;

import java.util.List;
import java.util.Stack;

public class DFS {

	private Stack<Vertex> stack;

	public DFS() {
		this.stack = new Stack<>();
	}

	public void dfs(List<Vertex> vertexList) {

		for (Vertex vertex : vertexList) {
			if (!vertex.isVistited()) {
				vertex.setVistited(true);
				// dfsWithStack(vertex);
				dfsRecursive(vertex);
			}
		}

	}

	private void dfsRecursive(Vertex v) {
		System.out.print(v + " ");
		for (Vertex vertex : v.getNeighborList()) {
			if (!vertex.isVistited()) {
				vertex.setVistited(true);
				dfsRecursive(vertex);
			}
		}
	}

	private void dfsWithStack(Vertex rootVertex) {
		this.stack.add(rootVertex);
		rootVertex.setVistited(true);
		while (!this.stack.isEmpty()) {
			Vertex actualVertex = this.stack.pop();
			System.out.print(actualVertex + " ");

			for (Vertex vertex : actualVertex.getNeighborList()) {
				if (!vertex.isVistited()) {
					vertex.setVistited(true);
					this.stack.push(vertex);
				}
			}

		}
	}
}
