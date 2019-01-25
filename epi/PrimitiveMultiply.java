package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    // TODO - you fill in here.
	  
	long addAns = add(7,5);
	System.out.println(addAns);
    return 0;
  }
  
  public static long add(long xx, long yy) {
	  long a = xx;
	  long b = yy;
	  long k = 1L;
	  long carry = 0L;
	  long tempC = 0L;
	  long ans = 1L;
//	  ans = ans >>> 1;
	  while (a!=0 && b!=0) {
		long tempA = a & k;
		long tempB = b & k;
//		if(tempA != 0) {
//			tempA = 1;
//		}
//		if(tempB != 0) {
//			tempB = 1;
//		}
		if(carry == 0) {
			tempC = tempA ^ tempB;
		}
		else {
			tempC = tempA ^ tempB ^ carry;
		}
		if((tempA == 1 && tempB == 1) || (tempB == 1 && carry == 1) || (tempA == 1 && carry == 1)) {
			carry = 1;
		}
		else {
			carry = 0;
		}
//		k = k << 0;
		a = a >>> 1;
		b = b >>> 1;
		if(tempC == 1) {
			ans = ans << 1;
			ans = ans | 1;
		}
		else {
			ans = ans << 1;
		}
	}
	  	if (carry == 1) {
	  		ans = ans << 1;
			ans = ans | 1;
		}
//		ans = ans >>> 1;
	  return ans;
  }

  public static void main(String[] args) {
	  multiply(7, 5);
//    System.exit(
//        GenericTest
//            .runFromAnnotations(args, "PrimitiveMultiply.java",
//                                new Object() {}.getClass().getEnclosingClass())
//            .ordinal());
  }
}
