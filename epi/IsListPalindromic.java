package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
	  
	  
	  if(L == null || L.next == null) {
		  return true;
	  }
	  
	  ListNode<Integer> fast = L;
	  ListNode<Integer> slow = L;
	  
	  while(fast != null && fast.next != null) {
		  fast = fast.next.next;
		  slow = slow.next;
	  }
	  
	  ListNode<Integer> lastNode = reverseList(slow);
	  
	  ListNode<Integer> firstNode = L;
	  
	  while(lastNode != null) {
		  if(!lastNode.data.equals(firstNode.data)) {
			  return false;
		  }
		  lastNode = lastNode.next;
		  firstNode = firstNode.next;
	  }
	  return true;
  }
  
  public static ListNode<Integer> reverseList(ListNode<Integer> slow) {
	  
	  ListNode<Integer> previous = slow;
	  ListNode<Integer> nextNode = slow.next;
	  
	  while(nextNode != null) {
		  
		  ListNode<Integer> temp = nextNode.next;
		  nextNode.next = previous;
		  previous = nextNode;
		  nextNode = temp;
	  }
	  slow.next = null;
	  return previous;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
