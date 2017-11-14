package depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private String name;
	private boolean vistited;
	private List<Vertex> neighborList;

	public Vertex(String name) {
		this.name = name;
		neighborList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVistited() {
		return vistited;
	}

	public void setVistited(boolean vistited) {
		this.vistited = vistited;
	}

	public List<Vertex> getNeighborList() {
		return neighborList;
	}

	public void setNeighborList(List<Vertex> neighborList) {
		this.neighborList = neighborList;
	}

	@Override
	public String toString() {
		return name;
	}

	public void addNeighborVertex(Vertex aVertex) {
		this.neighborList.add(aVertex);
	}

}
