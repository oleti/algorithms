package com.coursera.algo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.TreeSet;

public class MinimizeCompletionTimes {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		File file = new File("resources\\jobs.txt");
		FileReader inputFil = new FileReader(file);
		BufferedReader in = new BufferedReader(inputFil);
		String s =in.readLine();
		TreeSet<Job> set = new TreeSet<Job>();
		int i = 0;
		while(s!=null)
		{
			Job j = new Job();
			String[] s1 = s.split(" ");
			j.setWeight(Long.parseLong(s1[0]));
			j.setLength(Long.parseLong(s1[1]));
			j.setId(i+1);
			set.add(j);
		    s = in.readLine();
		    i++;
		}
		in.close();
		
		long time = 0;
		long totalWeigthedCompletionTime=0;
		for(Job j: set){
			System.out.print("Job " + j.getId() + " : metric = " + j.getMetric() + " weight = " + j.getWeight() + " Length = " + j.getLength() + " Start Time = " + time);
			time = time + j.getLength();
			totalWeigthedCompletionTime += (time*j.getWeight());
			System.out.println(" End Time = " + time + " Weighted-Completion = " + (time*j.getWeight()));
		}
		
		System.out.println("Total weighted completion time = " + totalWeigthedCompletionTime);
		System.out.println("Total number of jobs scheduled = " + set.size());
		
	}

}
