package breadthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	private int data;
	private boolean vistited;
	private List<Vertex> neighborList;

	public Vertex(int data) {
		this.data = data;
		neighborList = new ArrayList<>();
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
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
		return "" + data;
	}

	public void addNeighborVertex(Vertex aVertex) {
		this.neighborList.add(aVertex);
	}

}
