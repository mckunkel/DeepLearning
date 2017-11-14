package depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) {
		DFS dfs = new DFS();
		Vertex v1 = new Vertex("One");
		Vertex v2 = new Vertex("Two");
		Vertex v3 = new Vertex("Three");
		Vertex v4 = new Vertex("Four");
		Vertex v5 = new Vertex("Five");

		List<Vertex> aList = new ArrayList<>();

		v1.addNeighborVertex(v2);
		v1.addNeighborVertex(v3);
		v3.addNeighborVertex(v4);
		v4.addNeighborVertex(v5);

		aList.add(v1);
		aList.add(v2);
		aList.add(v3);
		aList.add(v4);
		aList.add(v5);

		dfs.dfs(aList);

	}
}
