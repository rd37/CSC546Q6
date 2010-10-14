package csc546;

public class CSC546PMF {

	public double getPMF(int n,int k,double p){
		double S=k*p*Math.pow(1.0-p,k-1);
		//System.out.println("n:"+n+" k:"+k+" p:"+p+" S:"+S);
		double result=0;
		for(int i=k;i<=n;i++){
			long pathCount=this.getChoice(n-1, i-1);
			//print(n+"nCi:"+i+" ,pc:"+pathCount);
			double Prob=Math.pow(S, i)*Math.pow(1.0-S, n-i);
			
			double numRatio=0;
			for(int t=0;t<=(k-1);t++){
				double sign=Math.pow(-1.0, (double)t);
				long iChoice=getChoice(k-1,k-1-t);
				numRatio+=sign*((double)iChoice)*Math.pow((k-1-t), i-1);
			}
			double numK=getChoice(k,k-1);
			double den=Math.pow(k, i);
			double tmp =((double)pathCount)*Prob*numRatio*numK/den;
			//System.out.println("At i:"+i+" pc:"+pathCount+" Prob:"+Prob+" NumRatio:"+numRatio+" DenRation:"+den);
			result+=tmp;
		}
		return result;
	}
	
	public long getChoice(int n,int k){
		int split=5;
		if(n>split){
			int nOver=n/split;
			int kOver=k/split;
			int n_kOver=(n-k)/split;
			double result=1;
			
			long[] numNarray = new long[nOver+1];
			for(int i=0;i<nOver;i++){
				long partialChoice=getFactorialIndex(split, n-i*split);
				numNarray[i]=partialChoice;
			}
			int remN=n%split;//may be 0 to split-1
			numNarray[nOver]=getFactorial(remN);
			
			long[] denKarray = new long[kOver+1];
			for(int i=0;i<kOver;i++){
				long partialChoice=getFactorialIndex(split, k-i*split);
				denKarray[i]=partialChoice;
			}
			int remk=k%split;//may be 0 to split-1
			denKarray[kOver]=getFactorial(remk);
			
			long[] denN_Karray = new long[n_kOver+1];
			for(int i=0;i<n_kOver;i++){
				long partialChoice=getFactorialIndex(split, (n-k)-i*split);
				denN_Karray[i]=partialChoice;
			}
			int remn_k=(n-k)%split;//may be 0 to split-1
			denN_Karray[n_kOver]=getFactorial(remn_k);
			
			for(int i=0;i<numNarray.length;i++){
				result*=numNarray[i];
				//print("result=result*"+numNarray[i]+" = "+result);
				if(i<denKarray.length){
					result/=denKarray[i];
					//print("result=result/"+denKarray[i]+" = "+result);
				}
				if(i<denN_Karray.length){
					result/=denN_Karray[i];
					//print("result=result/"+denN_Karray[i]+" = "+result);
				}
			}
			
			/*print("NArray:"+numNarray.length+" Karray:"+denKarray.length+" N_Karray:"+denN_Karray.length);
			for(int i=0;i<numNarray.length;i++){
				System.out.print(":"+numNarray[i]);
			}
			print(" ");print(" ");
			for(int i=0;i<denKarray.length;i++){
				System.out.print(":"+denKarray[i]);
			}
			print(" ");print(" ");
			for(int i=0;i<denN_Karray.length;i++){
				System.out.print(":"+denN_Karray[i]);
			}
			print(" ");print(" ");*/
			return (long)result;
		}else{
			long num1=getFactorial(n);
			long den1=getFactorial(k);
			long den2=getFactorial(n-k);
			return num1/(den1*den2);
		}
	}
	
	public long getFactorialIndex(int f,int startIndex){
		long res=1;
		if(f==0){
			return 1;
		}
		for(int i=0;i<f;i++){
			res=res*(startIndex-i);
		}
		return res;
	}
	
	public long getFactorial(int f){
		long res=1;
		if(f==0){
			return 1;
		}
		for(;f>1;f--){
			res=res*f;
		}
		return res;
	}
	
	private void print(String msg){
		System.out.println(msg);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CSC546PMF pmf = new CSC546PMF();
		int timeslots = (new Integer(args[0])).intValue();
		int k = (new Integer(args[1])).intValue();
		double probability=(new Double(args[2]));
		double expectation=0;
		double expectationSq=0;
		for(int i=0;i<timeslots;i++){
			double d=pmf.getPMF(i, k, probability);
			//
			System.out.print(d+" ");
			expectation+=(i*d);
			expectationSq+=(Math.pow((double)i,2.0))*d;
		}
		System.out.println("E[X]="+expectation);
		//System.out.println(""+expectationSq+"-"+Math.pow(expectation, 2.0));
		System.out.println("SD="+Math.pow((expectationSq-Math.pow(expectation, 2.0)),0.5));
		//double choice = pmf.getChoice(25,8);
		//double d=pmf.getPMF(5, 3, 0.1);
		//System.out.println("choice:"+choice);
	}

}
