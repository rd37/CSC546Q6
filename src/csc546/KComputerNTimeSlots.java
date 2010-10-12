package csc546;

import java.util.LinkedList;

public class KComputerNTimeSlots {
	private int n=3;
	private int k=3;//0 or 1 in the counter array
	private int rows;
	private int array[][];
	private int counter[];
	
	public void printArray(){
		//print array
		for(int i=0;i<rows;i++){
			for(int j=0;j<n;j++){
				System.out.print(array[i][j]+":");
			}
			System.out.println();
		}
	}
	
	public void printNumberTotalCombinations(){
		System.out.println("Possible Rows are "+rows);
	}
	
	public void printValidEntries(){
		int validEntry=0;
		for(int i=0;i<rows;i++){
			int[] numEntries=new int[n];
			for(int j=0;j<n;j++){
				int val=array[i][j];
				numEntries[val]+=1;
			}
			int uniqEntries=0;
			for(int k=0;k<numEntries.length;k++){
				if(numEntries[k]>0)
					uniqEntries++;
			}
			if(uniqEntries>=k)
				validEntry++;
		}
		System.out.println("Valid Rows are "+validEntry);
	}
	public void execute(){
		rows = (int) Math.pow(k, n);
		counter = new int[n];
		array = new int[rows][n];
		//int counter=0;
		for(int i=0;i<rows;i++){
			for(int j=0;j<n;j++){
				array[i][j]=counter[j];
			}
			//inc counter
			counter[0]+=1;
			//ripplecounter
			for(int a=0;a<(n-1);a++){
				if(counter[a]==k){
					counter[a+1]+=1;
					counter[a]=0;
				}
			}
		}
		this.printArray();
		this.printNumberTotalCombinations();
		this.printValidEntries();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KComputerNTimeSlots c = new KComputerNTimeSlots();
		c.execute();
	}

}
