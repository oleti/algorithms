package com.coursera.algo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.BitSet;
import java.util.Random;

public class Papidimitriou2Sat {
	
	private int numOfVariables = 0;
	private int[][] conditions = null;
	private BitSet values = null;
	Random generator = new Random();

	public Papidimitriou2Sat(String filePath) throws Exception{
		File file = new File(filePath);
		FileReader inputFil = new FileReader(file);
		//FileWriter outputFil = new FileWriter(filePath+".edges.txt");
		BufferedReader in = new BufferedReader(inputFil);
		//BufferedWriter out = new BufferedWriter(outputFil);
		String s =in.readLine();
		numOfVariables = Integer.parseInt(s); // To build up a reduction to SCC
		conditions = new int[numOfVariables][2];
		values = new BitSet(numOfVariables);
		
		s = in.readLine();
		int count = 0;
		while(s!=null && !s.equals(""))
		{
			String[] s1 = s.split(" ");
			conditions[count][0] = Integer.parseInt(s1[0]);
			conditions[count][1] = Integer.parseInt(s1[1]);
			count++;
			s = in.readLine();
		}
		in.close();
	}

	public static void main(String[] args) throws Exception{
		long startTime = System.currentTimeMillis();
		Papidimitriou2Sat twoSatProblem = new Papidimitriou2Sat("resources\\2sat1.txt");
		System.out.println("1 = " +twoSatProblem.isSolvable());
		System.out.println((System.currentTimeMillis() - startTime));
		/*twoSatProblem = new Papidimitriou2Sat("resources\\2sat2.txt");
		System.out.println("2 = " +twoSatProblem.isSolvable());
		twoSatProblem = new Papidimitriou2Sat("resources\\2sat3.txt");
		System.out.println("3 = " +twoSatProblem.isSolvable());
		twoSatProblem = new Papidimitriou2Sat("resources\\2sat4.txt");
		System.out.println("4 = " +twoSatProblem.isSolvable());
		twoSatProblem = new Papidimitriou2Sat("resources\\2sat5.txt");
		System.out.println("5 = " +twoSatProblem.isSolvable());
		twoSatProblem = new Papidimitriou2Sat("resources\\2sat6.txt");
		System.out.println("6 = " +twoSatProblem.isSolvable());*/
	}
	
	private void initializeRandomBitSet(){
		int numOfLongValues = (numOfVariables + 63)/64;
		long[] longs = new long[numOfLongValues];
		for(int i=0;i<numOfLongValues;i++)
			longs[i] = generator.nextLong();
		values = BitSet.valueOf(longs);
	}

	private boolean isSolvable() {
		int numOfloops = (int) Math.round(Math.log(numOfVariables)/Math.log(2));
		
		System.out.println("Total random numbers to be tried = " + numOfloops);
		
		for(int i=0;i<numOfloops;i++){
			initializeRandomBitSet();
			long innerLoopCount = 2L*(long)numOfVariables*(long)numOfVariables;
			System.out.println("Trying with Random Number " + i + " " + innerLoopCount);
			long j = 0;
			for(; j < innerLoopCount; j++){
				int k = 0;
				for(; k < numOfVariables ; k++ ){
					/*boolean clauseLeft = conditions[k][0] > 0?values.get(conditions[k][0]):!values.get(-conditions[k][0]);
					boolean clauseRight = conditions[k][1] > 0?values.get(conditions[k][1]):!values.get(-conditions[k][1]);
					if(clauseLeft || clauseRight)
						continue;
					else{
						// flip the bit on one of the clauses
						if(conditions[k][0]>0)
							values.flip(conditions[k][0]);
						else
							values.flip(-conditions[k][0]);
						break; // continue validation for this bit string..
					}	*/
				}
				//if(k==numOfVariables)
					//return true;
			}
			
			//if(j==innerLoopCount)
				//System.out.println("Couldnt find it :( " + j);
		}
		
		return false;
	}
	
}
