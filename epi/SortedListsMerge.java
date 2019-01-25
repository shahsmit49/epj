package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    // TODO - you fill in here.
	  
	  ListNode<Integer> dummyhead = new ListNode<Integer>(0, null);
	  ListNode<Integer> current = dummyhead;
	  ListNode<Integer> p1 = L1; ListNode<Integer> p2 = L2;
	  while(p1 != null && p2 != null) {
		  if(p1.data <= p2.data) {
			  current.next = p1;
			  p1 = p1.next;
		  }
		  else {
			  current.next = p2;
			  p2 = p2.next;
		  }
		  current = current.next;
	  }
	  current.next = p1 != null ? p1 : p2;
	  
	  return dummyhead.next;
//	  if(L1 == null) return L2;
//	  if(L2 == null) return L1;
//	  ListNode<Integer> nodeToReturn;
//	  ListNode<Integer> nodeA;
//	  ListNode<Integer> trackA;
//	  ListNode<Integer> nodeB;
//	  ListNode<Integer> trackB;
//	  if(L1.data >= L2.data) {
//		  nodeToReturn = L2;
//		  nodeA = L2.next;
//		  trackA = L2;
//		  nodeB = L1.next;
//		  trackB = L1;
//	  }
//	  else {
//		  nodeToReturn = L1;
//		  nodeA = L1.next;
//		  trackA = L1;
//		  nodeB = L2.next;
//		  trackB = L2;
//	  }
//	  while((nodeA != null) && (trackB != null)) {
//		  int a = nodeA.data;
//		  int b = trackB.data;
//		  if(a>=b) {
//			  trackA.next = trackB;
//			  trackA = trackB;
//			  trackB = nodeB;
//			  if(nodeB != null) nodeB = nodeB.next;
//			  else nodeB = null;
//		  }
//		  else {
//			  trackA.next = nodeA;
//			  trackA = nodeA;
//			  nodeA = nodeA.next;
//		  }
//		  
//	  }
//	  if(trackB != null) {
//		  trackA.next = trackB;
//	  }
//	  if(nodeA != null) {
//		  trackA.next = nodeA;
//	  }
//	  return nodeToReturn;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
