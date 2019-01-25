package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  static int arr[] = new int[65536];
  
  public static void createTable() {
	for (int i = 0; i < 65536; i++) {
	//		  brute force approach reversing by selecting first and last bits and creating table
		int bottom = -1;
		int top = 16;
		int ans=i;
			for (int j = 1; j < 9; j++) {
				bottom = bottom + 1;
				top = top - 1;
				int bottonMove = ((i >>> bottom) & 1);
				int topMove = ((i >>> top) & 1);
				if(bottonMove != topMove) {
					int tempNo = ((1 << top) | (1 << bottom));
					ans = (ans ^ tempNo) ;
				}
			}
		arr[i] = ans;
//		System.out.println(i+"::::"+arr[i]);
	}
  }
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
	  // TODO - you fill in here.
	  long y3 = (long)arr[(int)((x>>>48)&65535)] << 0;
	  long y2 = (long)arr[(int)((x>>>32)&65535)] << 16;
	  long y1 = (long)arr[(int)((x>>>16)&65535)] << 32;
	  long y0 = (long)arr[(int)((x>>>0)&65535)] << 48; // if we do not write long here arr will return int and shift 48
	  														// on int won't work
//	  System.out.println(y0);
	  x = y0 | y1 | y2 | y3;
	  return x;
  }

  public static void main(String[] args) {
//	reverseBits(1351510410656L);
	  createTable();
//	  reverseBits(1351510410656L);
	  System.exit(
			  GenericTest
			  .runFromAnnotations(args, "ReverseBits.java",
					  new Object() {}.getClass().getEnclosingClass())
			  .ordinal());
  }	
}
