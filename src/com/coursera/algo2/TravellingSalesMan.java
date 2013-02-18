package com.coursera.algo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class TravellingSalesMan {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
			
			File file = new File("resources\\tsp2.txt");
			FileReader inputFil = new FileReader(file);
			BufferedReader in = new BufferedReader(inputFil);
			String s =in.readLine();
			int numOfVertices = Integer.parseInt(s);
			float[] x = new float[numOfVertices];
			float[] y = new float[numOfVertices];
			s =in.readLine();
			int i = 0;
			while(s!=null && !s.equals(""))
			{
				x[i] = Float.parseFloat((s.split(" ")[0]));
				y[i] = Float.parseFloat((s.split(" ")[1]));
			    i++;
			    s = in.readLine();
			}
			in.close();
			
			// Key idea for the TSP implementation is that subsets of a specific size can be handled as numbers at a given hamming distance away from 0
			int numberOfSubsets = 0;
			
			/*for(i=1;i<numOfVertices;i++){
				Set<Integer> list = new TreeSet<Integer>(); 
				generateHammingDistanceNumbers(list,i, numOfVertices, 0);
				System.out.println("Number of Subsets of Size " + i + "  = " + list.size());
			}*/
			
			// In every iteration, the array "a" represents the array we are currently populating
			// and the array "b" represents the array populated in the previous iteration
			// Numbers at hamming distance 1.. also used in TSP main loop during subtraction
			Set<Integer> hammingDifferencePrevious = new TreeSet<Integer>();
			generateHammingDistanceNumbers(hammingDifferencePrevious,1, numOfVertices, 0);
			float[][] b = new float[hammingDifferencePrevious.size()][numOfVertices];
			int[] indexArray = new int[hammingDifferencePrevious.size()];
			Object[] hammingDifferenceArrayPrevious = hammingDifferencePrevious.toArray(); 
			int index = 0;
			for(Object number: hammingDifferenceArrayPrevious){
				int num = (Integer) number;
				if(num==1)
					b[index][0] = 0;
				else
					b[index][0] = 999999999f; // sufficiently large
				indexArray[index] = num;
				index++;
			}
			
			float[][] a = null;
			
			for(i=2;i<=numOfVertices;i++){ // i is an indicator of subset size (number of bits set).. or the hamming distance
				
				Set<Integer> hammingDifferenceNumbers = new TreeSet<Integer>();
				
				generateHammingDistanceNumbers(hammingDifferenceNumbers,i, numOfVertices, 0);
				
				numberOfSubsets = hammingDifferenceNumbers.size();
				a = new float[numberOfSubsets][numOfVertices];
				Object[] hammingDifferenceArray = hammingDifferenceNumbers.toArray(); 
				
				for(int l=0;l<numberOfSubsets; l++){
					
					// We are interested ONLY in subsets which contain the 1st vertex
					int currentPermutation = (Integer)hammingDifferenceArray[l];
					if(currentPermutation % 2 != 1)
						continue;
					
					for(int j=1;j<numOfVertices;j++){ // The 1st bit is always 1...
						
						if ((currentPermutation & (1 << j)) != 0){ // means that the k'th bit is set
							
							
							
							// we are interested in calculating "a" value for "currentPermutation_k"
							float minimum = 999999999f; // sufficiently large
							for(int k=0;k<numOfVertices;k++){
								if(k!=j && (currentPermutation & (1 << k)) != 0){
									// subset we are interested in
									int subsetAfterSubtraction = currentPermutation - (currentPermutation & (1 << j));  // S-{j}
									
									
									// b for the combination "subsetAfterSubtraction_(numOfVertices-p)" should have been already computed in the past
									try{
										float costOfSubset = 0;
										if(k==0 && subsetAfterSubtraction==1)
											costOfSubset = 0;
										else if(k==0)
											costOfSubset = 999999999f;
										else{
											index = Arrays.binarySearch(hammingDifferenceArrayPrevious, subsetAfterSubtraction);
											costOfSubset = b[index][k];
										}
									float cost = (costOfSubset + (float)Math.sqrt((x[j] - x[k])*(x[j] - x[k]) + (y[j] - y[k])*(y[j] - y[k])));
									if(cost < minimum)
										minimum = cost;
									}catch(Exception e){
										System.out.println("Exception at " + (subsetAfterSubtraction+"_"+k));
										throw e;
									}
								}
							}
							
							a[l][j] = minimum;
							//a.put(currentPermutation+"_"+j, minimum);	
						}
					}
				}
				hammingDifferenceArrayPrevious = hammingDifferenceArray; 
				b = a;
				
				System.out.println("Computed for Subset Size " + i + " (for subset size = " + numberOfSubsets + " )  " + a.length);
			}
			
			float min = 999999999f;
			for(i=1;i<numOfVertices;i++){
				System.out.println("a[0][" + i + "] = " + a[0][i]);
				if(a[0][i] + (float)Math.sqrt((x[i] - x[0])*(x[i] - x[0]) + (y[i] - y[0])*(y[i] - y[0])) < min)
					min = a[0][i] + (float)Math.sqrt((x[i] - x[0])*(x[i] - x[0]) + (y[i] - y[0])*(y[i] - y[0]));
			}
			
			System.out.println("Final Answer = " + min);
		
	}
	
	private static void generateHammingDistanceNumbers(Set<Integer> list, int hammingDistance, int totalBits, int prefix) {
		
		// if hammingDistance still to be computed is 0, the prefix is already candidate..
		// add the prefix to the list and return
		if(hammingDistance == 0) {
			list.add(prefix);
			return;
		} 
		
		if(totalBits == 0){
			//just return;
			return;
		}
		
		// Mark the highest order bit as 0
		// And compute the hamming distances of the remaining
		generateHammingDistanceNumbers(list, hammingDistance, totalBits - 1, prefix);
		
		// Mark the highest order bit as 1
		// And compute the hamming distances of the remaining
		generateHammingDistanceNumbers(list, hammingDistance - 1, totalBits - 1, prefix + (1<<(totalBits-1)));
		
	}

}
