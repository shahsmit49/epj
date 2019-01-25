package epi;
import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
	  // TODO - you fill in here.
	  ArrayList<Integer> pos = new ArrayList<>();
	  int i = -1;
	  long j = 3;
	  long temp = 0;
	  long temp1 = 0;
	  while(true) {
		  i = i +1;
		  temp = x & (long)Math.pow(2, i);
		  temp1 = (x & (long)Math.pow(2, i+1)) >>> 1;
		  if(temp != temp1) {
			  pos.add(i);
			  break;
		  }
	  }
//	  System.out.println(pos.get(0));
	  j = j << (pos.get(0));
	  x = x ^ j;
	  return x;
  }

  public static void main(String[] args) {
//	closestIntSameBitCount(39698800462691L);
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
