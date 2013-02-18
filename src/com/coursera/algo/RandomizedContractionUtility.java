package com.coursera.algo;

import java.util.ArrayList;
import java.util.Random;

public class RandomizedContractionUtility {
	
	private ArrayList<Node> nodes = null;
	
	private static Random rn = new Random();

	public RandomizedContractionUtility(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public int minimumCut() {
		
		// If the size of nodes list is two, stop recursion.
		if(nodes.size() == 2){
			return nodes.get(0).getAdjacentVertices().size();
		}
		
		// Pick a node at random
		int firstVertexIndex = rn.nextInt(nodes.size());
		//System.out.println(firstVertexIndex);
		Node firstVertex = nodes.get(firstVertexIndex);
		// Pick a second node from the list of first node's adjacent vertices
		int secondVertexIndex = rn.nextInt(firstVertex.getAdjacentVertices().size());
		//System.out.println(secondVertexIndex);
		Node secondVertex = firstVertex.getAdjacentVertices().get(secondVertexIndex);

		// Merge the two nodes
		firstVertex.merge(secondVertex);
		
		// Remove the second vertex from the main list
		nodes.remove(secondVertex);
		
		// Recurse
		return minimumCut();
		
	}

}
