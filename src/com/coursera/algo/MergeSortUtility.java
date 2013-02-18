package com.coursera.algo;

public class MergeSortUtility {

	private long[] array = null;
	private int startIndex = 0;
	private int endIndex = 0;
	
	public MergeSortUtility(long[] array, int startIndex, int endIndex) {
		super();
		this.array = array;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	/**
	 * Returns the number of inversions till the current level
	 * @return
	 */
	public long sort(int level){
		
		//System.out.println("Trying to process Level = " + level);
		if((endIndex - startIndex) <1)
			return 0;
		
		// Divide Step
		int firstArrayStartIndex = startIndex;
		int firstArrayEndIndex = startIndex + ((endIndex - startIndex)/2);
		int secondArrayStartIndex = firstArrayEndIndex + 1;
		int secondArrayEndIndex = endIndex;
		
		// Conquer step
		long leftInversions = 0; 
		long rightInversions = 0;
		long splitInversions = 0;
		MergeSortUtility leftArrayUtility = new MergeSortUtility(array,firstArrayStartIndex,firstArrayEndIndex);
		MergeSortUtility rightArrayUtility = new MergeSortUtility(array,secondArrayStartIndex,secondArrayEndIndex);
		leftInversions += leftArrayUtility.sort(level + 1);
		rightInversions += rightArrayUtility.sort(level + 1);
		
		// Merge Step
		splitInversions += merge(level);
		
		/*
		if(level == 1){
			System.out.println("Level " + level + " has " + startIndex + " startIndex and " + firstArrayEndIndex + " midpoint and " + endIndex + " endIndex");
			System.out.println("Level " + level + " has " + leftInversions + " LeftInversions and " + rightInversions + " RightInversions and " + splitInversions + " SplitInversions");
			System.out.println("Total Inversions at Level " + level + " = " + (leftInversions + rightInversions + splitInversions));
		}*/
		
		return leftInversions + rightInversions + splitInversions;
	}
	
	/**
	 * Returns the number of inversions at the current level
	 * @return
	 */
	public long merge(int level){

		// Setup indexes
		int firstArrayStartIndex = startIndex;
		int firstArrayEndIndex = startIndex + ((endIndex - startIndex)/2);
		int secondArrayStartIndex = firstArrayEndIndex + 1;
		int secondArrayEndIndex = endIndex;
		
		// Make a copy of the output array
		long sortedArray[] = new long[endIndex - startIndex + 1];
		long splitInversions = 0;
		int k = 0;
		int i = firstArrayStartIndex;
		int j = secondArrayStartIndex;
		
		for(;i<=firstArrayEndIndex && j<=secondArrayEndIndex;k++){
			if(array[i] > array[j]){
				// All numbers in the first array from i will form inversions with this element of the second array
				// splitInversions += (j - secondArrayStartIndex + 1);
				splitInversions += (firstArrayEndIndex-i+1);
				sortedArray[k] = array[j];
				j++;
			} else{
				sortedArray[k] = array[i];
				i++;
			}
			
		}
		
		if(i>firstArrayEndIndex)
			for(;k<(endIndex-startIndex+1);k++)
				sortedArray[k] = array[j++];
		else
			for(;k<(endIndex-startIndex+1);k++)
				sortedArray[k] = array[i++];
		
		// Copy the sortedArray into the original Array
		for(k=0;k<sortedArray.length;k++)
			array[startIndex+k] = sortedArray[k];
				
		return splitInversions;
	}
	
	public long[] getArray() {
		return array;
	}

	public void setArray(long[] array) {
		this.array = array;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
