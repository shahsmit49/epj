package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {
	  
	  if(L == null) {
		  return null;
	  }
	  int length = -1;
	  ListNode<Integer> head = new ListNode<Integer>(0, L);
	  ListNode<Integer> iter = head;
	  ListNode<Integer> slow = L;
	  ListNode<Integer> fast = L;
	  while(iter != null) {
		  iter = iter.next;
		  length++;
	  }
	  iter = head;
	  
	  if(k>=length) {
		  k = k % length;
	  }
	  
	  if(k == 0) {
		  return L;
	  }
	  
	  while(k-- > 0) {
		  fast = fast.next;
	  }
	  
	  while(fast.next != null) {
		  slow = slow.next;
		  fast = fast.next;
	  }
	  
	  ListNode<Integer> temp = slow.next;
	  slow.next = null;
	  iter.next = temp;
	  while(iter.next != null) {
		  iter = iter.next;
	  }
	  iter.next = L;
	  
	  return head.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
