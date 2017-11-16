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
			if (!vertex.isVisited()) {
				vertex.setVisited(true);
				// dfsWithStack(vertex);
				dfsRecursive(vertex);
			}
		}

	}

	private void dfsRecursive(Vertex v) {
		System.out.print(v + " ");
		for (Vertex vertex : v.getNeighborList()) {
			if (!vertex.isVisited()) {
				vertex.setVisited(true);
				dfsRecursive(vertex);
			}
		}
	}

	private void dfsWithStack(Vertex vertex) {
		this.stack.add(vertex);
		vertex.setVisited(true);
		while (!stack.isEmpty()) {
			Vertex actualVertex = this.stack.pop();
			System.out.print(actualVertex + " ");

			for (Vertex vertex2 : actualVertex.getNeighborList()) {
				if (!vertex2.isVisited()) {
					vertex2.setVisited(true);
					this.stack.push(vertex2);
				}
			}
		}

	}

}
