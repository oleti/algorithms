package com.coursera.algo2;

public class Job implements Comparable<Job>{
	
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getWeight() {
		return weight;
	}
	public void setWeight(long weight) {
		this.weight = weight;
	}
	private long length;
	private long id;
	private long weight;

	public float getMetric(){
		// ignoring exceptions :)
		return (float)weight/(float)length;
		//return weight - length;
	}
	
	/*@Override
	public int compareTo(Job j2) {
		// Helps schedule jobs in decreasing order of the difference (weight - length)
		// if two jobs have equal difference (weight - length), schedules the job with higher weight first
		if((weight - length) > (j2.getWeight() - j2.getLength()))
			return -1;
		else if((weight - length) < (j2.getWeight() - j2.getLength()))
			return 1;
		else if(weight > j2.getWeight())
			return -1;
		else if(weight < j2.getWeight())
			return 1;
		else{
			// even if weights and lengths are both equal for the job, they should still differ by their ID..
			if(id > j2.getId())
				return -1;
			else if(id < j2.getId())
				return 1;
			else
				return 0;
		}
	}*/

	@Override
	public int compareTo(Job j2) {
		// Helps schedule jobs in decreasing order of the ratio (weight / length)
		// if two jobs have equal difference (weight - length), schedules the job with higher weight first
		if(((float)weight/(float)length) > ((float)j2.getWeight() / (float)j2.getLength()))
			return -1;
		else if(((float)weight / (float)length) < ((float)j2.getWeight() / (float)j2.getLength()))
			return 1;
		else if(weight > j2.getWeight())
			return -1;
		else if(weight < j2.getWeight())
			return 1;
		else{
			// even if weights and lengths are both equal for the job, they should still differ by their ID..
			if(id > j2.getId())
				return -1;
			else if(id < j2.getId())
				return 1;
			else
				return 0;
		}
			
	}
	
	public boolean equals(Job j2){
		if((weight - length) == (j2.getWeight() - j2.getLength()) && weight == j2.getWeight())
			return true;
		else{
			// even if weights and lengths are both equal for the job, they should still differ by their ID..
						if(id != j2.getId())
							return false;
						else
							return true;
		}
			
	}

}
