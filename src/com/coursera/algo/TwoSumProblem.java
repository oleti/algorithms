package com.coursera.algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
public class TwoSumProblem {
	
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\iSource\\mcr\\Algorithms\\resources\\HashInt.txt");
		FileReader inputFil = new FileReader(file);
		BufferedReader in = new BufferedReader(inputFil);
		HashSet<Integer> hashSet = new HashSet<Integer>(100000);
		String s =in.readLine();
		int i = 0;
		while(s!=null)
		{
		    hashSet.add(Integer.parseInt(s));
		    s = in.readLine();
		    i++;
		}
		in.close();
		
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(231552);
		numbers.add(234756);
		numbers.add(596873);
		numbers.add(648219);
		numbers.add(726312);
		numbers.add(981237);
		numbers.add(988331);
		numbers.add(1277361);
		numbers.add(1283379);
		
		Iterator<Integer> it = numbers.iterator();
		
		while(it.hasNext()){
			int targetSum = it.next();
			Iterator<Integer> nums = hashSet.iterator();
			int found = 0;
			while(nums.hasNext()){
				int thisNumber = nums.next();
				int targetNumber = targetSum - thisNumber;
				if(hashSet.contains(targetNumber)){
					
					found = 1;
					break;
				}
			}
			System.out.print(found);
		}

	}

}
