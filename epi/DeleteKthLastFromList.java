package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    
	ListNode<Integer> fast = L;
	ListNode<Integer> slow = L;
	int count = k;
	
	while(count-- > 0) {
		fast = fast.next;
	}
	
	while(fast != null && fast.next != null) {
		slow = slow.next;
		fast = fast.next;	
	}
	
	if(fast == null) {
		L = L.next;
		return L;
	}
	slow.next = slow.next.next;
	
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
