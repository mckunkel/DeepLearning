package breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	public void bfs(Vertex root) {
		Queue<Vertex> queue = new LinkedList<>();
		root.setVisited(true);
		queue.add(root);

		while (!queue.isEmpty()) {
			Vertex actualVertex = queue.remove();
			System.out.println(actualVertex + "  ");

			for (Vertex vertex : actualVertex.getNeighborList()) {
				if (!vertex.isVisited()) {
					vertex.setVisited(true);
					queue.add(vertex);
				}

			}
		}
	}

}
