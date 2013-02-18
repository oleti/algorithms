package com.coursera.algo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.TreeSet;

public class Knapsack {

	public static void main(String[] args) throws Exception{
		File file = new File("resources\\knapsack1.txt");
		FileReader inputFil = new FileReader(file);
		BufferedReader in = new BufferedReader(inputFil);
		String s =in.readLine();
		String[] s1 = s.split(" ");
		int knapsackSize = Integer.parseInt(s1[0]);
		int numItems = Integer.parseInt(s1[1]);
		int i = 0;
		int[] value = new int[numItems];
		int[] weight = new int[numItems];
		s=in.readLine();
		do
		{
			s1 = s.split(" ");
			value[i] = Integer.parseInt(s1[0]);
			weight[i] = Integer.parseInt(s1[1]);
			s = in.readLine();
		    i++;
		}while(s!=null);
		in.close();
		
		
		int[][] a = new int[numItems+1][knapsackSize+1]; 
		// a[i][j] carries the best solution using first i items in the array with a knapsack size of j

		// If no items are available, initialize to zeroes
		for(i=0;i<=knapsackSize;i++)
			a[0][i] = 0;
		
		for(i=1;i<=numItems;i++){
			
			for(int j=0;j<=knapsackSize;j++){
				int bestValue = a[i-1][j]; // assuming that the ith item is not included in the knapsack
				// check this with the other case.. if the ith item is included in knapsack
				
				if(j-weight[i-1]>=0){ // check first that index is in bounds
					if(value[i-1] + a[i-1][j-weight[i-1]] > bestValue)
						bestValue = value[i-1] + a[i-1][j-weight[i-1]];
				}
				a[i][j] = bestValue;
			}
			
		}
		
		System.out.println("Best Value = " + a[numItems][knapsackSize] + " " + numItems);
				
	}

}
