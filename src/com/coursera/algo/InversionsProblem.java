package com.coursera.algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.coursera.algo.MergeSortUtility;

public class InversionsProblem {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("resources\\IntegerArray.txt");
		FileReader inputFil = new FileReader(file);
		BufferedReader in = new BufferedReader(inputFil);
		long [] array = new long[100000];
		String s =in.readLine();
		int i = 0;
		while(s!=null)
		{
		    array[i] = Integer.parseInt(s);
		    s = in.readLine();
		    i++;
		}
		in.close();
		
		System.out.println("Total Integers read = " + array.length);
		long startTime = System.currentTimeMillis();
		System.out.println("Starting to count inversions : " );
		
		MergeSortUtility mergeSortUtility = new MergeSortUtility(array, 0, array.length-1);
		System.out.println("Total number of inversions = " + mergeSortUtility.sort(1));
		
		long endTime = System.currentTimeMillis();
		System.out.println("Completed Counting inversions : " );
		System.out.println("Total time taken : " + (endTime-startTime) + " milliseconds");
		
	}

}
