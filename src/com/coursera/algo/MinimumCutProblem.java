package com.coursera.algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.coursera.algo.Node;
import com.coursera.algo.RandomizedContractionUtility;

public class MinimumCutProblem {
	
	public static void main(String[] args) throws IOException {
		
		int finalValue = 10000000;
		for(int j =0;j<5904;j++){
			
			File file = new File("C:\\iSource\\mcr\\Algorithms\\resources\\kargerAdj.txt");
			FileReader inputFil = new FileReader(file);
			BufferedReader in = new BufferedReader(inputFil);
			
			ArrayList<Node> nodes = new ArrayList<Node>();
			
			// Initialize the arraylist
			for(int i=0;i<40;i++)
				nodes.add(new Node(i+1));		
			
			String s =in.readLine();
			while(s!=null)
			{
				String[] current = s.split("\\s+");  // contiguous space delimiter
			    for(int i=2;i<current.length;i++){
		    		Node node = nodes.get(Integer.parseInt(current[1])-1);
			    	node.addAdjacentVertex(nodes.get(Integer.parseInt(current[i])-1));
			    }
			    s =in.readLine();
			}
			in.close();
			
			
			RandomizedContractionUtility randomizedContractionUtility = new RandomizedContractionUtility(nodes);
		
			int minimumCut = randomizedContractionUtility.minimumCut();
			System.out.println("Got minimum cut = " + minimumCut + " in the iteration " + j );
			if(finalValue > minimumCut)
				finalValue = minimumCut;
		}

		System.out.println("Final Cut = " + finalValue);
		
	}
}
