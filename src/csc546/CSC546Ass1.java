/*
 * Java Program to that generates a random 
 * probability for each of k computers to generate a 
 * packet in a time slot.  If 1 of k computers succeeds in
 * generating a packet and k-1 computers fail, then 
 * there was a successful transmission.  Once all k computers have
 * had at least 1 successful transmission, then the number of slots 
 * it took is returned to main.  main repeats the process 1000 times
 * and prints the average.  
 */
package csc546;

import java.util.Random;

/*
 * var p  is the probability of a computing attempting a transmission.
 * var k  is the number of computers 
 * var txarray[k] keeps track of which of k computers have had success transimission
 * var slot maintains a count of how many attempts where made to complete 
 */
public class CSC546Ass1 {
	private int slot=0;
	private double p=0.1;
	private int k=3;
	private int txarray[];
	private Random range;
	
	public CSC546Ass1(){
		txarray = new int[k];
	}
	
	/*
	 * reset the sim with a new seed
	 */
	public void reset(int ran){
		slot=0;
		for(int i=0;i<txarray.length;i++){
			txarray[i]=0;
		}
		range=new Random(ran);
	}
	
	/*
	 * interate the txarray to determine if all of k computers have
	 * successfully transmitted.  return true if all transmitted, or
	 * false if at least 1 computer has not transmitted. 
	 */
	public boolean allTransmitted(){
		for(int i=0;i<txarray.length;i++){
			if(txarray[i]==0)
				return false;
		}
		return true;
	}
	
	public int execute(){
		while(!allTransmitted()){ //keep generating until all computers succeed
			slot++;
			int txattemptxcnt=0;
			int lastindex=0;
			for(int i=0;i<k;i++){//for each of the k computers, generate a random # and compare to p
				double rand = range.nextDouble();
				if(rand<p){ //if random number is less than p, the computer i will attempt to tx
					txattemptxcnt++;
					lastindex=i;
				}
			}
			if(txattemptxcnt==1){//if only 1 computer tried to transmit, then success
				txarray[lastindex]+=1;//mark the computer at last index to success (greater than zero is success)
			}
		}
		return slot-1;
	}
	
	public static void main(String args[]){
		CSC546Ass1 ass1 = new CSC546Ass1();
		int slottotal=0;
		try{
			int samples=(new Integer(args[0])).intValue();
			//try to run sim 1000 times and take average
			int inputseed=(new Integer(args[3])).intValue();
			
			ass1.k=(new Integer(args[1])).intValue();
			ass1.txarray = new int[ass1.k];
			ass1.p=(new Double(args[2])).doubleValue();
			System.out.println("Execute "+samples+" times with "+ass1.k+" computers with probablity "+ass1.p+" to transmite, using startseed "+inputseed);
			for(int i=0;i<samples;i++){
				ass1.reset(inputseed*i);
				int lastslot = ass1.execute();
				slottotal+=lastslot;
			}
			double ave = ((double)slottotal)/((double)samples);
			System.out.println("AVE:"+ave+" slots");
		}catch(Exception e){
			System.out.println("Usage:    java CSC546Ass1  5 3 0.1  87654  ");
			System.out.println("Where 5 is the number of samples, 3 is number of computers 0.1 is probability of transimitting and 87654 is the start seed");
			e.printStackTrace();
		}
	}
}
