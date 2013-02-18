package com.coursera.algo;

public class QuickSortUtility {
	
	private int[] array = null;
	private int startIndex = 0;
	private int endIndex = 0;
	public static final String FIRST_ELEMENT_PIVOT = "FIRST_ELEMENT_PIVOT";
	public static final String LAST_ELEMENT_PIVOT = "LAST_ELEMENT_PIVOT";
	public static final String MEDIAN_ELEMENT_PIVOT = "MEDIAN_ELEMENT_PIVOT";
	private String pivotStrategy = null; 
	
	public QuickSortUtility(int[] array, int startIndex, int endIndex, String pivotStrategy) {
		super();
		this.array = array;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.pivotStrategy = pivotStrategy;
	}
	
	/**
	 * Returns the number of comparisons made
	 * @return
	 */
	public long sort(){
		
		long comparisons = 0;
		
		// Recursion Break
		if(!this.pivotStrategy.equals(MEDIAN_ELEMENT_PIVOT) && startIndex == endIndex)
			return comparisons;
		
		// Base care is different if the pivot strategy is median based
		if(this.pivotStrategy.equals(MEDIAN_ELEMENT_PIVOT) && endIndex-startIndex < 2 ){
			
			if(endIndex == startIndex)
				return 0;
			
			// Just compare the two elements in question and swap them if necessary
			if(array[startIndex]>array[endIndex]){
				int temp = array[startIndex];
				array[startIndex] = array[endIndex];
				array[endIndex] = temp;
			}
			return 1;
		}
		
		int pivotIndex = choosePivot();
		
		// Partition method should return the final position of the pivot
		// If the size of the current SubArray is m, there will be m-1 comparisons during partitioning
		// Size of subArray = endIndex - startIndex + 1
		int pivotPositionAfterPartition = partition(pivotIndex);
		long comparisonsInPartition = endIndex-startIndex;
		
		comparisons += comparisonsInPartition;
		
		if(pivotPositionAfterPartition != startIndex){
			QuickSortUtility quickSortUtility= new QuickSortUtility(array, startIndex, pivotPositionAfterPartition-1, pivotStrategy);
			comparisons += quickSortUtility.sort();
		}
		
		if(pivotPositionAfterPartition != endIndex){
			QuickSortUtility quickSortUtility= new QuickSortUtility(array, pivotPositionAfterPartition+1, endIndex, pivotStrategy);
			comparisons += quickSortUtility.sort();
		}
		
		return comparisons;
	}

	private int partition(int pivotIndex) {
		// Exchange the pivot with the first element of the array if applicable
		if(pivotIndex != startIndex){
			int temp = array[pivotIndex];
			array[pivotIndex] = array[startIndex];
			array[startIndex] = temp;
		}
		
		int i = startIndex+1, j = startIndex + 1;
		for (;j<=endIndex;j++){
			if(array[j] < array[startIndex]){
				int temp = array[j];
				array[j] = array[i];
				array[i] = temp;
				i++;
			}
		}
		
		// Swap the first element with the right most element of the left array
		i--;
		int temp = array[startIndex];
		array[startIndex] = array[i];
		array[i] = temp;
		
		return i;
	}

	private int choosePivot() {
		if(pivotStrategy.equals(FIRST_ELEMENT_PIVOT))
			return startIndex;
		else if(pivotStrategy.equals(LAST_ELEMENT_PIVOT))
			return endIndex;
		else{
			int middleIndex = startIndex + ((endIndex - startIndex)/2);
			if((array[startIndex]<=array[middleIndex] && array[middleIndex]<=array[endIndex]) || 
					(array[endIndex]<=array[middleIndex] && array[middleIndex]<=array[startIndex]))
				return middleIndex;
			else if((array[middleIndex]<=array[startIndex] && array[startIndex]<=array[endIndex]) || 
					(array[endIndex]<=array[startIndex] && array[startIndex]<=array[middleIndex]))
				return startIndex;
			else
				return endIndex;
		}
	}

}
