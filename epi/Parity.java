package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
	
	static int arr[];
    static int[] createTable(int size) {
        size = (int)Math.pow(2,size);
        int arr[] = new int[size];
        for(int i =0 ; i < size ; i++) {
            arr[i] = computeParity(i);
        }
        System.out.println("Done");
        return arr;
    }
    
    static int computeParity(int a) {
        a = (a>>>8)^a;
        a = (a>>>4)^a;
        a = (a>>>2)^a;
        a = (a>>>1)^a;
        return a&1;
        
    }
	
	static int compute(long parityValue) {
	   int ans = arr[(int)(parityValue >>> 48) ] ^
	                arr[(int)(parityValue >>> 32) & (65535)] ^
	                arr[(int)(parityValue >>> 16)  & (65535)] ^
	                arr[(int)parityValue  & (65535)] ;
	   return ans;
	}
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // TODO - you fill in here.
	 
//	    Scanner sc = new Scanner(System.in);
//	    long parityValue = sc.nextLong();
	    int ans = compute(x);
    return (short)ans;
  }

  public static void main(String[] args) {
	  arr = createTable(16);
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
