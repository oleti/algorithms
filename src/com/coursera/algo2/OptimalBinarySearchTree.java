package com.coursera.algo2;

public class OptimalBinarySearchTree {
	
	public static void main(String[] args){
		double[] frequency = new double[]{0.2,0.05,0.17,0.1,0.2,0.03,0.25};
		//double[] frequency = new double[]{0.05,0.4,0.08,0.04,0.1,0.1,0.23};
		new OptimalBinarySearchTree(frequency);
	}
	
	public OptimalBinarySearchTree(double[] frequency){
		
		int n = frequency.length;
		double[][] a = new double[n][n];
		
		for(int s=0;s<n;s++){
			for(int i=0;i<n;i++){
				// we have to compute a[i][i+s]
				// a[i][i+s] means the optimal binary search tree for 
				// the subproblem set {i through i+s elements} of the original frequency list in the Optimal tree T.
				if(i+s >= n)
					continue;
				
				// We need to do a brute force minimum computation of BST weights with each possible element as root
				double minimum = 10000.0; // something large or... code up more correctly
				// According to the formula (compute the constant factor first)
				double constant = 0.0;
				for(int r=i;r<=i+s && r<n;r++)
					constant += frequency[r];
				
				System.out.print("Possible weights for the subproblem ranging from " + i + " to " + (i+s) + " are ");
				
				for(int r=i;r<=i+s && r<n;r++){
					// Average search time for the tree with rth element as root is
					// weight = frequency[r] + aprime[i][r-1] + aprime[r+1][i+s];
					double weight = constant + (i<=r-1 && r>=1?a[i][r-1]:0.0) + ((r+1<=n && r+1<=i+s)?a[r+1][i+s]:0.0);
					System.out.printf("%3.2f ",weight);
					if(weight < minimum)
						minimum = weight;
				}
				
				a[i][i+s] = minimum;
				System.out.println("a[" + i + "][" + (i+s) + "] = " + a[i][i+s]);
			}
		}
		
		System.out.println("Final answer is " + a[0][n-1]);
		
		
		for(int i=n-1;i>=0;i--){
			for(int j=0;j<=i;j++)
				System.out.printf(" %3.2f" , a[j][i]);
			System.out.println();
		}
	}

}
