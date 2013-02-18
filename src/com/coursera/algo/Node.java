package com.coursera.algo;

import java.util.ArrayList;

public class Node {
	
	int number = 0;

	private String list = "";
	
	private ArrayList<Node> adjacentVertices = new ArrayList<Node>();
	
	public Node(int number){
		this.number = number;
		this.list = this.list + number;
	}
	
	public void merge(Node node){
		
		// Remove first the links in the current node to the incoming node (self loops)
		while(this.adjacentVertices.remove(node));
		
		// Add the arrayList in the current node to the array list from incoming node
		for(Node n : node.getAdjacentVertices()){
			if(n == this)
				continue; // skip self loops
			n.addAdjacentVertex(this);
			while(n.getAdjacentVertices().remove(node));
			this.addAdjacentVertex(n);
		}
		
		// Merge the list
		this.list = this.list + "," + node.getNumber();
		
		// Incoming node is got rid of in the parent call
		
	}
	
	public ArrayList<Node> getAdjacentVertices() {
		return adjacentVertices;
	}

	public void setAdjacentVertices(ArrayList<Node> adjacentVertices) {
		this.adjacentVertices = adjacentVertices;
	}
	
	public void addAdjacentVertex(Node adjacentVertex) {
		adjacentVertices.add(adjacentVertex);
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

}
