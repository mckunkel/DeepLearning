package iterativedeepingdepthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private String name;
	private int depthLevel;
	private List<Node> ajancenciesList;

	public Node(String name) {
		this.name = name;
		this.ajancenciesList = new ArrayList<>();
	}

	public void addNeighbor(Node node) {
		this.ajancenciesList.add(node);
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDepthLevel() {
		return depthLevel;
	}

	public void setDepthLevel(int depthLevel) {
		this.depthLevel = depthLevel;
	}

	public List<Node> getAjancenciesList() {
		return ajancenciesList;
	}

	public void setAjancenciesList(List<Node> ajancenciesList) {
		this.ajancenciesList = ajancenciesList;
	}

}
