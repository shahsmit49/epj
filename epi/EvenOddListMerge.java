package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
	  
	  if(L == null || L.next == null || L.next.next == null) {
		  return L;
	  }
	  ListNode<Integer> even = L;
	  ListNode<Integer> odd = L.next;
	  ListNode<Integer> iter = odd;
	  while(even.next != null && odd.next != null) {
		  if(even.next.next != null) {
			  even.next = even.next.next;
			  even = even.next;  
		  }
		  if(odd.next.next != null) {
			  odd.next = odd.next.next;
			  odd = odd.next;  
		  }
	  }
	  
	  even.next = iter;
	  odd.next = null;
	  return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
