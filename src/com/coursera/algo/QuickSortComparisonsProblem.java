package com.coursera.algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.coursera.algo.QuickSortUtility;

public class QuickSortComparisonsProblem {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\iSource\\mcr\\Algorithms\\resources\\QuickSortIntegerArray.txt");
		FileReader inputFil = new FileReader(file);
		BufferedReader in = new BufferedReader(inputFil);
		int[] unSortedArray = new int [10000];
		String s =in.readLine();
		int i = 0;
		while(s!=null)
		{
			unSortedArray[i] = Integer.parseInt(s);
		    s = in.readLine();
		    i++;
		}
		in.close();
		
		
		
		int[] array = new int [10000];
		System.arraycopy(unSortedArray, 0, array, 0, unSortedArray.length);
		long startTime = System.currentTimeMillis();
		
		
		QuickSortUtility quickSortUtility = new QuickSortUtility(array, 0, array.length - 1 , QuickSortUtility.FIRST_ELEMENT_PIVOT);
		System.out.println("Total number of comparisons in FIRST_ELEMENT_PIVOT = " + quickSortUtility.sort());
		
		System.arraycopy(unSortedArray, 0, array, 0, unSortedArray.length);
		quickSortUtility = new QuickSortUtility(array, 0, array.length - 1, QuickSortUtility.LAST_ELEMENT_PIVOT);
		System.out.println("Total number of comparisons in LAST_ELEMENT_PIVOT = " + quickSortUtility.sort());
		
		System.arraycopy(unSortedArray, 0, array, 0, unSortedArray.length);
		quickSortUtility = new QuickSortUtility(array, 0, array.length - 1, QuickSortUtility.MEDIAN_ELEMENT_PIVOT);
		System.out.println("Total number of comparisons in MEDIAN_ELEMENT_PIVOT = " + quickSortUtility.sort());
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Total time taken : " + (endTime-startTime) + " milliseconds");
		
	}

}
