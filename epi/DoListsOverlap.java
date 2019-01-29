package epi;
import java.util.HashSet;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoListsOverlap {

  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
                                                   ListNode<Integer> l1) {

	  ListNode<Integer> firstNode = checkifcycle(l0);
	  ListNode<Integer> secondNode = checkifcycle(l1);
	  if(firstNode == null && secondNode == null) {
		  ListNode<Integer> finalAns = overlappingNoCycleList(l0,l1);
		  return finalAns;
	  }
	  else if((firstNode == null && secondNode != null) || (firstNode != null && secondNode == null)) {
		  return null;
	  }
	  boolean ans = isDisJoint(firstNode,secondNode);
	  
	  if(ans) return null;
	  
	  int stemp1length = distance(firstNode,l0); int stemp2length = distance(secondNode,l1);
	  
	  if(stemp1length >= stemp2length) {
		  l0 = advanceListByK(stemp1length - stemp2length, l0);
	  }
	  
	  else {
		  l1 = advanceListByK(stemp2length - stemp1length, l1);
	  }
	  
	  while((l0 != l1) && (l0 != firstNode) && (l1 != secondNode) ) {
		  l1 = l1.next;
		  l0 = l0.next;
	  }
	  
	  return l0 == l1 ? l1:firstNode;
	  
  }
  
  public static ListNode<Integer> advanceListByK(int count, ListNode<Integer> root) {
	  
	  while(count-- > 0) {
		  root = root.next;
	  }
	  return root;
	  
  }
  
  public static int distance(ListNode<Integer> start, ListNode<Integer> root) {
	  
	  int count = 0;
	  while(root != start) {
		  count++;
		  root = root.next;
	  }
	  return count;
	  
  }
  
  public static boolean isDisJoint(ListNode<Integer> firstNode, ListNode<Integer> secondNode) {
	  
	  ListNode<Integer> temp = firstNode.next;
	  while((temp != secondNode) && (temp != firstNode)) {
		  temp = temp.next;
	  }
	  if(temp == secondNode) {
		  return false;
	  }
	  else {
		  return true;
	  }
  }
  
  public static ListNode<Integer> checkifcycle(ListNode<Integer> linkedList) {
	  
	  if(linkedList == null || linkedList.next == null || linkedList.next.next == null) {
		  return null;
	  }
	  
	  ListNode<Integer> fast = linkedList;
	  ListNode<Integer> slow = linkedList;
	  int count = 0;
	  while(fast != null && fast.next != null) {
		  fast = fast.next.next;
		  slow = slow.next;
		  if(slow == fast) {
			  slow = slow.next;
			  count = 1;
			  while(slow != fast) {
				  slow = slow.next;
				  count++;
			  }
			  break;
		  }
	  }
	  
	  if(count == 0)	return null;
	  else {
		  slow = linkedList;
		  fast = linkedList;
		  
		  while(count-- > 0) {
			  slow = slow.next;
		  }
		  
		  while(slow != fast) {
			  slow = slow.next;
			  fast = fast.next;
		  }
		  
		  return slow;
	  }
  }
  
  public static ListNode<Integer> overlappingNoCycleList(ListNode<Integer> l0, ListNode<Integer> l1) {
	  
	  int first = length(l0);
	  int second = length(l1);
	  ListNode<Integer>  big;
	  if(first >= second) {
		  int extraForward = first - second;
		  big = moveForward(extraForward, l0);
		  ListNode<Integer> finalAns = moveTandem(big,l1);
		  return finalAns;
	  }
	  
	  else {
		  int extraForward = second - first;
		  big = moveForward(extraForward, l1);
		  ListNode<Integer> finalAns = moveTandem(big,l0);
		  return finalAns;
	  }
  }
  
  public static int length(ListNode<Integer> list) {
	  
	  int ans = 0;
	  
	  if(list == null) {
		  return 0;
	  }
	  
	  while(list != null) {
		  ans++;
		  list = list.next;
	  }
	  
	  return ans;
	  
  }
  
  public static ListNode<Integer> moveForward(int count, ListNode<Integer> big) {
	  
	  while(count-- > 0 ) {
		  big = big.next;
	  }
	  
	  return big;
  }
  
  public static ListNode<Integer> moveTandem(ListNode<Integer> big, ListNode<Integer> small) {
	  
	  while(big != small) {
		  if(big != null)
			  big = big.next;
		  if(small != null)
			  small = small.next;
	  }
	  return big;
  }
  
//  if((l0 == null) || (l1 == null)) {
//	  return null;
//  }
//  if(l0 == l1) {
//	  return l1;
//  }
//  int lengthofL0 = countLength(l0);
//  int lengthofL1 = countLength(l1);
//  ListNode<Integer> ans;
////  if((lengthofL0 == 0) || (lengthofL1 == 0)) {
////	  return null;
////  }
//  
//  if(lengthofL0 >= lengthofL1) {
//	  int diff = lengthofL0 - lengthofL1;
//	  ListNode<Integer> big = moveBigForward(l0, diff);
//	  ans = returnAns(big, l1, lengthofL0 - diff);
//	  
//  }
//  else {
//	  int diff = lengthofL1 - lengthofL0 ;
//	  ListNode<Integer> big = moveBigForward(l1, diff);
//	  ans = returnAns(big, l0, lengthofL1 - diff);
//  }
//  
//  return ans;
//  
//  public static ListNode<Integer> moveBigForward(ListNode<Integer> big , int diff) {
//	  
//	  while(diff-- > 0) {
//		  big = big.next;
//	  }
//	  return big;
//  }
//  
//  public static ListNode<Integer> returnAns(ListNode<Integer> big, ListNode<Integer> small, int lengthofLong) {
//	  
//	  ListNode<Integer> bigg = big;
//	  ListNode<Integer> smaal = small;
//	  int lengthLLong = lengthofLong;
//	  while(lengthLLong-- > 0) {
//		  if(bigg != smaal) {
//			  bigg = bigg.next;
//			  smaal = smaal.next;
//		  }
//		  else {
//			  return bigg;
//		  }
//	  }
//	  bigg = big;
//	  smaal = small;
//	  lengthLLong = lengthofLong;
//	  
//	  while(lengthLLong-- > 0) {
//		  if(bigg == smaal) {
//			  return bigg;
//		  }
//		  bigg = bigg.next;
//		  
//	  }
//	  return null;
//  }
//  
//  public static int countLength(ListNode<Integer> head) {
//	  
//	  if(head.next == null) {
//		  return 0;
//	  }
//	  ListNode<Integer> fast = head;
//	  ListNode<Integer> slow = head;
//	  int countlength = 1;
//	  while(fast.next.next != null) {
//		  slow = slow.next;
//		  fast = fast.next.next;
//		  if(slow == fast) {
//			  slow = slow.next;
//			  if(slow == fast) {
//				  countlength++;
//			  }
//			  else {
//				  while(slow != fast) {
//					  slow = slow.next;
//					  ++countlength;
//				  }  
//			  }
//		  }
//		  if(countlength>1) {
//			  int temp = countlength;
//			  slow = head;
//			  fast = head;
//			  while(temp-- > 0) {
//				  fast = fast.next;
//			  }
//			  while(fast != slow) {
//				  fast = fast.next;
//				  slow = slow.next;
//				  countlength++;
//			  }
//			  return countlength;
//		  }
//	  }
////	  if(fast.next.next == null) {
//		  fast = head;
//		  countlength = 1;
//		  while(fast.next != null) {
//			  fast = fast.next;
//			  countlength++;
//		  }
////	  }
//	  return countlength;
//	  
//  }
  
  @EpiTest(testDataFile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws Exception {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingLists(finalL0, finalL1));

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
