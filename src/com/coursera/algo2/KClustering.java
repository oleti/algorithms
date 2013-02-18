package com.coursera.algo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class KClustering {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		File file = new File("resources\\clustering1.txt");
		FileReader inputFil = new FileReader(file);
		BufferedReader in = new BufferedReader(inputFil);
		String s =in.readLine();
		TreeSet<Edge> set = new TreeSet<Edge>();
		int i = 0;
		while(s!=null)
		{
			Edge e = new Edge();
			String[] s1 = s.split(" ");
			e.setStartVertex(Integer.parseInt(s1[0]) - 1);
			e.setEndVertex(Integer.parseInt(s1[1]) - 1);
			e.setCost(Integer.parseInt(s1[2]));
			e.setId(i+1);
			set.add(e);
		    s = in.readLine();
		    i++;
		}
		in.close();
		
		int[] vertexCluster = new int[500];  // index of the array is the vertex itself and the value is the cluster number 
		// initialize the cluster number to the vertex itself
		
		System.out.println("Number of vertices = " + vertexCluster.length);
		
		for(i=0; i< vertexCluster.length; i++)
			vertexCluster[i] = i;
		
		// Initially the number of clusters = total number of nodes
		int numOfClusters = vertexCluster.length;
		int targetNumOfClusters = 4;
		
		int currentMaxSpacing = 0;
		
		while(numOfClusters >= targetNumOfClusters){
			
			// Pick the smallest edge
			Edge selectedEdge = set.first();
			currentMaxSpacing = selectedEdge.getCost();
			
			// See if this edge mandates joining two clusters?
			if(vertexCluster[selectedEdge.getStartVertex()] != vertexCluster[selectedEdge.getEndVertex()]){
				
				if(numOfClusters == targetNumOfClusters)
					break;
				
				int currentClusterNumber = vertexCluster[selectedEdge.getStartVertex()];
				int higherClusterNumber = vertexCluster[selectedEdge.getEndVertex()];
				// Choose the smallest cluster number
				if(currentClusterNumber > higherClusterNumber){
					currentClusterNumber = vertexCluster[selectedEdge.getEndVertex()];
					higherClusterNumber = vertexCluster[selectedEdge.getStartVertex()];
				}
				
				// Make sure all nodes in the cluster having the higher cluster number are also modified
				for(i=higherClusterNumber; i<vertexCluster.length;i++){
					if(vertexCluster[i] == higherClusterNumber)
						vertexCluster[i] = currentClusterNumber;
				}
				
				// We have one cluster less
				numOfClusters--;
			}
			
			// Finally remove the edge from the set
			set.remove(selectedEdge);
			
		}
		
		// Once we are done clustering, the size of first edge in the remaining set is the max spacing
		
		Set<Integer> clusterNums = new HashSet<Integer>();
		System.out.println(currentMaxSpacing + " is the desired spacing");
		for(i = 0; i< vertexCluster.length; i++)
			clusterNums.add(vertexCluster[i]);
		
		System.out.println(" Total numb of clusters = " + clusterNums.size());
		
	}

}
