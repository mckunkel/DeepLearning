package iterativedeepingdepthfirstsearch;

public class App {

	public static void main(String[] args) {
		Node node0 = new Node("A");
		Node node1 = new Node("B");
		Node node2 = new Node("C");
		Node node3 = new Node("D");
		Node node4 = new Node("E");

		node0.addNeighbor(node1);
		node0.addNeighbor(node2);
		node1.addNeighbor(node3);
		node3.addNeighbor(node4);

		IDDFS iddfs = new IDDFS(node4);
		iddfs.runDeepingSearch(node0);

	}

}
