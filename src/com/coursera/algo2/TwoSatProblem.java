package com.coursera.algo2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coursera.algo.StronglyConnectedComponents;

public class TwoSatProblem {

	private int numOfVariables = 0;
	List<Integer>[] adjacentVertices = null;
	List<Integer>[] incomingVertices = null;
	
	Set<String> knownEdges = new HashSet<String>();
	
	@SuppressWarnings("unchecked")
	public TwoSatProblem(String filePath) throws Exception{
		
		File file = new File(filePath);
		FileReader inputFil = new FileReader(file);
		FileWriter outputFil = new FileWriter(filePath+".edges.txt");
		BufferedReader in = new BufferedReader(inputFil);
		BufferedWriter out = new BufferedWriter(outputFil);
		String s =in.readLine();
		numOfVariables = Integer.parseInt(s); // To build up a reduction to SCC
		adjacentVertices = new List[2*numOfVariables + 1]; // One vertex is wasted / unused
		incomingVertices = new List[2*numOfVariables + 1];
		
		s =in.readLine();
		while(s!=null && !s.equals(""))
		{
			String[] s1 = s.split(" ");
			
			// Each Line is of the form (variable1 V variable2) ... V stands for OR operator
			// We try to reduce the 2-Sat to SCC with the below logic
			// Each variable is represented by two vertices (a and ~a)... 
			// vertices in the range -numOfVariables thru -1 are representing negated forms
			// vertices through 1 to numOfVariables are representing the non-negated forms
			// The clause (a OR b) will be represented by 2 directed edges
			// ~a ==> b , ~b ==> a   where ==> is the implies operator
			
			int variable1 = Integer.parseInt(s1[0]);
			int variable2 = Integer.parseInt(s1[1]);
			
			// So the current clause is (variable1 OR variable2)
			
			// One edge is -variable1 ==> variable2
			if(addEdge(-variable1, variable2))
				out.write(((-variable1) + numOfVariables + 1) + " " + ((variable2) + numOfVariables + 1) + "\n");
			// The other edge is -variable2 ==> variable1
			if(addEdge(-variable2, variable1))
				out.write(((-variable2) + numOfVariables + 1) + " " + ((variable1) + numOfVariables + 1) + "\n");
			
			s = in.readLine();
		}
		in.close();
		out.close();
		
		knownEdges = null;
		
		if(adjacentVertices[numOfVariables] == null && adjacentVertices[numOfVariables] == null)
			System.out.println("Graph validated successfully");
		else
			System.out.println("Graph validation failed");
		
	}
	
	private boolean addEdge(int variable1, int variable2){
		
		String edge = variable1 + " " + variable2;
		
		if(!knownEdges.contains(edge))
			knownEdges.add(edge);
		else
			return false;
		
		int startVertexIndex = variable1 + numOfVariables;
		int endVertexIndex = variable2 + numOfVariables;
		List<Integer> adjacentVertexList = adjacentVertices[startVertexIndex];
		if(adjacentVertexList == null)
			adjacentVertexList = new ArrayList<Integer>();
		if(!adjacentVertexList.contains(endVertexIndex)){
			adjacentVertexList.add(endVertexIndex);
			adjacentVertices[startVertexIndex] = adjacentVertexList;
		}
		
		List<Integer> incomingVertexList = incomingVertices[endVertexIndex];
		if(incomingVertexList == null)
			incomingVertexList = new ArrayList<Integer>();
		if(!incomingVertexList.contains(startVertexIndex)){
			incomingVertexList.add(startVertexIndex);
			incomingVertices[endVertexIndex] = incomingVertexList;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		TwoSatProblem twoSatProblem= new TwoSatProblem("resources\\2sat6.txt");
		System.out.println("1 = " +twoSatProblem.isSolvable());
		twoSatProblem = new TwoSatProblem("resources\\2sat2.txt");
		System.out.println("2 = " +twoSatProblem.isSolvable());
		twoSatProblem = new TwoSatProblem("resources\\2sat3.txt");
		System.out.println("3 = " +twoSatProblem.isSolvable());
		twoSatProblem = new TwoSatProblem("resources\\2sat4.txt");
		System.out.println("4 = " +twoSatProblem.isSolvable());
		twoSatProblem = new TwoSatProblem("resources\\2sat5.txt");
		System.out.println("5 = " +twoSatProblem.isSolvable());
		twoSatProblem = new TwoSatProblem("resources\\2sat6.txt");
		System.out.println("6 = " +twoSatProblem.isSolvable());
		
		/*twoSatProblem = new TwoSatProblem("resources\\test-2sat-scc1.txt");
		System.out.println("1 = " +twoSatProblem.isSolvable());
		twoSatProblem = new TwoSatProblem("resources\\test-2sat-scc2.txt");
		System.out.println("2 = " +twoSatProblem.isSolvable());*/
		
	}

	private boolean isSolvable() {
		
		// Compute Strongly Connected Components 
		List<List<Integer>> stronglyConnectedComponents = (new StronglyConnectedComponents(adjacentVertices,incomingVertices)).getComponents();
		System.out.println("Number of strongly connected Components = " + stronglyConnectedComponents.size());
		
		/*
		int[] componentSizes = new int[stronglyConnectedComponents.size()];
		
		int i=0;
		int totalSize = 0;
		for(List<Integer> component: stronglyConnectedComponents){
			componentSizes[i] = component.size();
			totalSize += componentSizes[i];
			i++;
		}

		Arrays.sort(componentSizes);
		
		System.out.println("Total Nodes from All SCCs = " + totalSize);
		System.out.print("Top Component Sizes = ");
		for(i = componentSizes.length-1; i> componentSizes.length-6 && i>=0;i--)
			System.out.print(componentSizes[i]+",");
		
		System.out.println();
		*/
		
		// and if an SCC has both negated and non-negated forms of a variable
		// then the problem is unsolvable
		for(List<Integer> component : stronglyConnectedComponents){

			if(component.size()==1)
				continue;
			
			for(Integer vertex: component){				
				int counterPart = 2*numOfVariables-vertex;
				if(vertex < numOfVariables && component.contains(counterPart))
					return false;
			}
		}
		
		return true;
		
	}

}
