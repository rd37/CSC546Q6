package csc546;

public class CSC546Q6 {
	private int k=3;
	private double p=0.4;
	
	public double execute(){
		double result=0;
		for(int i=1;i<=k;i++){
			double num=1;
			double den=(k-i+1)*p*Math.pow((1-p),k-1);
			result+=num/den;
		}
		
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CSC546Q6 ass6= new CSC546Q6();
		double result=ass6.execute();
		System.out.println("E[X]="+result);
	}

}
