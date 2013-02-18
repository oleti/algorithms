package com.coursera.algo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



/**
 * This class assumes that edges are already sorted in the increasing order of 
 * startVertex, endVertex
 */
public class FloydWarshall {
	
	private List<Edge> listOfEdges = null;
	private long numOfEdges = 0;
	private int numOfVertices = 0;
	
	@SuppressWarnings("unchecked")
	List<Integer>[] adjacentVertexList = new List[1000];
	List<Integer>[] edgeCostList = new List[1000];
	
	int[][] a;
	
	public FloydWarshall(String filePath) throws Exception{
		
		File file = new File(filePath);
		FileReader inputFil = new FileReader(file);
		BufferedReader in = new BufferedReader(inputFil);
		String s =in.readLine();
		numOfVertices = Integer.parseInt(s.split(" ")[0]);
		numOfEdges = Long.parseLong(s.split(" ")[1]);
		System.out.println("Vertices = " + numOfVertices + " Edges = " + numOfEdges);
		s =in.readLine();
		while(s!=null)
		{
			String[] s1 = s.split(" ");
			int startVertex = Integer.parseInt(s1[0]) - 1;
			int endVertex = Integer.parseInt(s1[1]) - 1;
			int cost = Integer.parseInt(s1[2]);
			
			if(adjacentVertexList[startVertex] == null){
				adjacentVertexList[startVertex] = new ArrayList<Integer>();
				edgeCostList[startVertex] = new ArrayList<Integer>();
			}
			adjacentVertexList[startVertex].add(endVertex);
			edgeCostList[startVertex].add(cost);
			
		    s = in.readLine();
		}
		in.close();
		
	}
	
	public int[][] allPairsShortestPath(){
		a = new int[numOfVertices][numOfVertices];
		
		// Base Case
		for(int i=0; i < numOfVertices; i++ ){
			for(int j=0;j<numOfVertices;j++){
				if(i==j)
					a[i][j] = 0;
				else if(adjacentVertexList[i].contains(j))
					a[i][j] = edgeCostList[i].get(adjacentVertexList[i].indexOf(j));
				else
					a[i][j] = 99999999; // sufficiently large integer (for +infinity)
			}
		}	
		
		
		// Algorithm
		for(int k=0; k<numOfVertices; k++){
			int[][] b = new int[numOfVertices][numOfVertices];
			for(int i=0; i < numOfVertices; i++){
				for(int j=0; j < numOfVertices; j++){
					
					// Array A represents the shortest paths in the previous round of k
					// Array B represents the array for the current round
					// we have to compute b[i][j]
					if(a[i][k] + a[k][j] < a[i][j])
						b[i][j] = a[i][k] + a[k][j];
					else
						b[i][j] = a[i][j];
					
				}
			}
			// Array B should become array A for the next round
			a = b;
		}
		
		// Check for negative cycle
		for(int i=0;i<numOfVertices; i++)
			if(a[i][i] < 0)
				return null;
		
		return a;
	}
	
	public int shortestPath(){
		int shortestPath = 9999999;
		for(int i=0;i<numOfVertices; i++)
			for(int j=0;j<numOfVertices; j++)
				if(i!=j && a[i][j] < shortestPath){
					shortestPath = a[i][j];
				}
		return shortestPath;
	}
			
	public static void main(String[] args) throws Exception {
		
		FloydWarshall algorithmForGraph1 = new FloydWarshall("resources\\APSP-Graph1.txt");
		if(algorithmForGraph1.allPairsShortestPath()!=null)
			System.out.println("Length of shortest Path for Graph 1 = " + algorithmForGraph1.shortestPath());
		else
			System.out.println("Graph 1 has a negative cost cycle");
		
		FloydWarshall algorithmForGraph2 = new FloydWarshall("resources\\APSP-Graph2.txt");
		if(algorithmForGraph2.allPairsShortestPath()!=null)
			System.out.println("Length of shortest Path for Graph 2 = " + algorithmForGraph2.shortestPath());
		else
			System.out.println("Graph 2 has a negative cost cycle");
		
		FloydWarshall algorithmForGraph3 = new FloydWarshall("resources\\APSP-Graph3.txt");
		if(algorithmForGraph3.allPairsShortestPath()!=null)
			System.out.println("Length of shortest Path for Graph 3 = " + algorithmForGraph3.shortestPath());
		else
			System.out.println("Graph 3 has a negative cost cycle");
		
	}

}
