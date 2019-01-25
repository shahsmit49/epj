package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
	  if(start == finish) {
		  return L;
	  }
	  ListNode<Integer> dummyHead = new ListNode<Integer>(0, L);
	  ListNode<Integer> P = dummyHead;
	  for(int i = 1 ; i < start ; i++) {
		  P = P.next;
	  }
	  ListNode<Integer> Q = P.next;
	  
	  for(int i = 1 ; i <= (finish - start) ; i++) {
//		  P.next = Q.next;
//		  ListNode<Integer> temp = P.next.next;
//		  P.next.next = Q;
//		  Q.next = temp;
//		  P = P.next;
//		  Q = Q.next;
		  ListNode<Integer> temp2 = P.next;
		  P.next = Q.next;
		  ListNode<Integer> temp = Q.next.next;
		  Q.next.next = temp2;
		  Q.next = temp;
//		  Q = Q.next;
	  }
	  
	  
//	  P.next = Q.next;
//	  ListNode<Integer> temp = P.next.next;
//	  P.next.next = Q;
//	  Q.next = temp;
	  
	  return dummyHead.next;
	  
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
