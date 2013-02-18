package com.coursera.algo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class BigKnapsack {

	public static void main(String[] args) throws Exception{
		File file = new File("resources\\knapsack2.txt");
		FileReader inputFil = new FileReader(file);
		BufferedReader in = new BufferedReader(inputFil);
		String s =in.readLine();
		String[] s1 = s.split(" ");
		long knapsackSize = Long.parseLong(s1[0]);
		int numItems = Integer.parseInt(s1[1]);
		int i = 0;
		long[] value = new long[numItems];
		long[] weight = new long[numItems];
		s=in.readLine();
		do
		{
			s1 = s.split(" ");
			value[i] = Long.parseLong(s1[0]);
			weight[i] = Long.parseLong(s1[1]);
			s = in.readLine();
		    i++;
		}while(s!=null);
		in.close();
		
		TreeSet<Long> possibleSubProblemWeights = new TreeSet<Long>();
		possibleSubProblemWeights.add(knapsackSize);
		int[] numOfPossibleWeights = new int[numItems];  
		// the number of possible weights for an item i, is the last numOfPossibleWeights[i] weights from the end of possibleSubProblemWeights list
		for(i=numItems-1;i>=0;i--){
			Iterator<Long> it = possibleSubProblemWeights.iterator();
			List<Long> newSubProblemWeights = new ArrayList<Long>();
			while(it.hasNext()){
				long currentWeight = it.next();
				// And the other possible weight is the one made by removing the current element
				if(currentWeight - weight[i] > 0)
					newSubProblemWeights.add(currentWeight - weight[i]);
			}
			possibleSubProblemWeights.addAll(newSubProblemWeights);
			
			
		}
		
		int totalPossibleSubProblemWeights = possibleSubProblemWeights.size();
		
		// For an item with index i in the original list,
		// The applicable weights that we care about will be the ones 
		System.out.println("Total Sub Problem weights that we care about = " + totalPossibleSubProblemWeights);
		
		// Variable size 2D array only holding best values with weights we care about
		// Enables constant time lookup in the recursion
		Long[][] a = new Long[numItems][possibleSubProblemWeights.size()];
		
		List<Long> possibleSubProblemWeightsList = new ArrayList<Long>(possibleSubProblemWeights);
		
		long maxValue = 0;
		String maxValueIndices = "";
		
		for(i=0;i<numItems;i++){
			
			// for each of the possible sub problem weights that we care about, 
			// compute the best value possible in the knapsack with items (1 through i)
			Iterator<Long> it = possibleSubProblemWeightsList.iterator();
			int j=0;
			while(it.hasNext()){
				long currentKnapsackWeight = it.next(); 
				// we have to compute a[i][a[j]
				long bestValue = 0;
				int lowerIndex = -1;
				if(i-1<0)
					if(currentKnapsackWeight > weight[i])
						bestValue = value[i];
					else
						bestValue = 0;
				else{
					// max of a[i-1][k] and (value[i] + a[i-1][k-1]
					bestValue = a[i-1][j];
					if(possibleSubProblemWeightsList.indexOf(currentKnapsackWeight - weight[i]) >= 0)
						lowerIndex = possibleSubProblemWeightsList.indexOf(currentKnapsackWeight - weight[i]);
					// the other index we should check about is 
					if(lowerIndex>=0 && bestValue < (value[i] + a[i-1][lowerIndex]) )
						bestValue = value[i] + a[i-1][lowerIndex];
				}
				a[i][j] = bestValue;
				j++;
				
				if(maxValue<bestValue){
					maxValue = bestValue;
				}
				
				
			}
			
		}
		
		System.out.println("Final answer = " + a[numItems-1][possibleSubProblemWeightsList.size()-1]);
		System.out.println("maxValue = " + maxValue  + " " + maxValueIndices);
		
	}

}
