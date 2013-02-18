package com.coursera.algo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import com.coursera.algo.MergeSortUtility;

public class KBitClustering {

	public static void main(String args[]) throws Exception{
		
		File file = new File("resources\\clustering2.txt");
		FileReader inputFil = new FileReader(file);
		BufferedReader in = new BufferedReader(inputFil);
		String s =in.readLine();
		long[] node = new long[20000];

		int i = 0;
		while(s!=null)
		{
			node[i] = Long.parseLong(s,2); i++;
		    s = in.readLine();
		}
		in.close();
		
		List<Long> hammingDifferenceNumbers = new ArrayList<Long>();
		generateHammingDistanceNumbers(hammingDifferenceNumbers,1, 24, 0);
		generateHammingDistanceNumbers(hammingDifferenceNumbers,2, 24, 0);
		
		// Sort the input array into increasing order first
		long[] sortedNodes = new long[20000];
		System.arraycopy( node, 0, sortedNodes, 0, node.length );
		MergeSortUtility mergeSortUtility = new MergeSortUtility(sortedNodes, 0, sortedNodes.length-1);
		mergeSortUtility.sort(1); 
		
		
		System.out.println("Total Numbers causing a maximum hamming difference of 2 is ... " + hammingDifferenceNumbers.size() );
		
		HashMap<Long,TreeSet<Long>> clusters = new HashMap<Long,TreeSet<Long>>();
				
		for(i=0; i<hammingDifferenceNumbers.size(); i++){
			
			// For each number causing a hamming difference of less than 2,
			// iterate the main array and identify the list of cluster members
			long currentHammingDifference = hammingDifferenceNumbers.get(i);
			for(int j=0; j < sortedNodes.length; j++){
				
				// we always search FORWARD .... if the XOR of the current node and the current hamming
				// difference is less than the current node, there is no point in searching
				if((sortedNodes[j] ^ currentHammingDifference) < sortedNodes[j])
					continue;
				
				// else do a binary search and find if there is a cluster member...
				int k = 0;				
				if( (k=Arrays.binarySearch(sortedNodes, 0, sortedNodes.length , sortedNodes[j] ^ currentHammingDifference)) > 0){
					// Match found.. 
					// Manage clusters
					TreeSet<Long> leftCluster = clusters.get(sortedNodes[j]);
					TreeSet<Long> rightCluster = clusters.get(sortedNodes[j] ^ currentHammingDifference);
					if(leftCluster == null && rightCluster == null){
						TreeSet<Long> set = new TreeSet<Long>();
						set.add(sortedNodes[j]);
						set.add(sortedNodes[j] ^ currentHammingDifference);
						clusters.put(sortedNodes[j], set);
						clusters.put(sortedNodes[j] ^ currentHammingDifference, set);
					} else if(leftCluster == null){
						rightCluster.add(sortedNodes[j]);
						clusters.put(sortedNodes[j], rightCluster);
					} else if(rightCluster == null){
						leftCluster.add(sortedNodes[j] ^ currentHammingDifference);
						clusters.put(sortedNodes[j] ^ currentHammingDifference, leftCluster);
					} else if(leftCluster == rightCluster) {
						// Same clusters already.. Ignore this case
						
						continue;
					} else{
						leftCluster.addAll(rightCluster);
						for(Long rightClusterNode : rightCluster)
							clusters.put(rightClusterNode, leftCluster);
						rightCluster.clear();
					}
					
				}
				
			}
			
		}
				
		HashSet<TreeSet<Long>> setOfClusters = new HashSet<TreeSet<Long>>(); 
		setOfClusters.addAll(clusters.values());
		long numberOfdistinctClusters = setOfClusters.size();
		
		int unclassifiedNodes=0;
		for(i=0; i< sortedNodes.length; i++){
			if(clusters.get(sortedNodes[i]) == null){
				TreeSet<Long> ts = new TreeSet<Long>();
				ts.add(sortedNodes[i]);
				clusters.put(sortedNodes[i], ts);
				unclassifiedNodes++;
			}
		}
		
		System.out.println((numberOfdistinctClusters + unclassifiedNodes) + " clusters "); 
		
	}
	
	private static void generateHammingDistanceNumbers(List<Long> list, int hammingDistance, int totalBits, long prefix) {
		
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

	static long distanceBetweenNodes(long node1,long node2)
	{
		long i = node1 ^ node2;
	    i = i - ((i >> 1) & 0x55555555);
	    i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
	    return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
	}
	
}
