package com.coursera.algo2;

public class Edge implements Comparable<Edge>{

	public int getStartVertex() {
		return startVertex;
	}
	public void setStartVertex(int startVertex) {
		this.startVertex = startVertex;
	}
	public int getEndVertex() {
		return endVertex;
	}
	public void setEndVertex(int endVertex) {
		this.endVertex = endVertex;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
	protected int startVertex;
	protected int endVertex;
	private int cost;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(Edge o) {
		// For PRIMs algorithm, its beneficial to sort edges based on cost
		if(this.cost < o.cost)
			return -1;
		else if(this.cost > o.cost)
			return 1;
		else{
			// Even if edge costs are equal, they should still be different objects based on the vertices.
			if(startVertex == o.getStartVertex() && endVertex == o.getEndVertex())
				return 0;
			else
				if((startVertex + endVertex) > (o.getStartVertex() + o.getEndVertex()))
					return 1;
				else
					return -1;
		}
		
	}
	
	public boolean equals(Edge o) {
		if(this.cost == o.cost && startVertex == o.getStartVertex() && endVertex == o.getEndVertex())
			return true;
		return false;
	}
}
