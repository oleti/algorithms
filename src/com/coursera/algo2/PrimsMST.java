package com.coursera.algo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class PrimsMST {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("resources\\edges.txt");
		FileReader inputFil = new FileReader(file);
		BufferedReader in = new BufferedReader(inputFil);
		String s =in.readLine();
		int numNodes = Integer.parseInt(s.split(" ")[0]);
		int numEdges = Integer.parseInt(s.split(" ")[1]);
		TreeSet<Edge> set = new TreeSet<Edge>();
		int i = 0;
		s = in.readLine();
		while(s!=null)
		{
			Edge e = new Edge();
			String[] s1 = s.split(" ");
			e.setStartVertex(Integer.parseInt(s1[0]));
			e.setEndVertex(Integer.parseInt(s1[1]));
			e.setCost(Integer.parseInt(s1[2]));
			e.setId(i+1);
			set.add(e);
		    s = in.readLine();
		    i++;
		}
		in.close();
		
		List<Integer> visitedVertices = new ArrayList<Integer>();
		// Initialize to the first vertex in the first edge of the set
		visitedVertices.add(set.first().getStartVertex());
		
		long overAllCost = 0;
		while(visitedVertices.size() != numNodes){
			
			// Find the minimum cost Edge out of all the candidate edges
			
			for(Edge e: set){
				if(visitedVertices.contains(e.getStartVertex()))
					if(!visitedVertices.contains(e.getEndVertex())){
						// Candidate Edge.. suck it up
						overAllCost += e.getCost();
						visitedVertices.add(e.getEndVertex());
						break; // break the loop
					}
					else{
						// Do nothing... already visited vertex .. continue search
					}
				else { 	//if(!visitedVertices.contains(e.getStartVertex()))
					if(visitedVertices.contains(e.getEndVertex())){
						// Candidate Edge.. suck it up
						overAllCost += e.getCost();
						visitedVertices.add(e.getStartVertex());
						break;
					}
					else{
						// Do nothing... totally unknown vertices .. continue search
					}
				}
			}
		}
		
		System.out.println("Overall cost of minimum spanning tree = " + overAllCost);
		System.out.println("Number of edges = " + set.size());
	}

}
