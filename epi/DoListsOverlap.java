package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class DoListsOverlap {

  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
                                                   ListNode<Integer> l1) {
    // TODO - you fill in here.
	  if((l0 == null) || (l1 == null)) {
		  return null;
	  }
	  if(l0 == l1) {
		  return l1;
	  }
	  int lengthofL0 = countLength(l0);
	  int lengthofL1 = countLength(l1);
	  ListNode<Integer> ans;
//	  if((lengthofL0 == 0) || (lengthofL1 == 0)) {
//		  return null;
//	  }
	  
	  if(lengthofL0 >= lengthofL1) {
		  int diff = lengthofL0 - lengthofL1;
		  ListNode<Integer> big = moveBigForward(l0, diff);
		  ans = returnAns(big, l1, lengthofL0 - diff);
		  
	  }
	  else {
		  int diff = lengthofL1 - lengthofL0 ;
		  ListNode<Integer> big = moveBigForward(l1, diff);
		  ans = returnAns(big, l0, lengthofL1 - diff);
	  }
	  
	  return ans;
  }
  
  public static ListNode<Integer> moveBigForward(ListNode<Integer> big , int diff) {
	  
	  while(diff-- > 1) {
		  big = big.next;
	  }
	  return big;
  }
  
  public static ListNode<Integer> returnAns(ListNode<Integer> big, ListNode<Integer> small, int lengthofLong) {
	  
	  while(lengthofLong-- > 0) {
		  if(big != small) {
			  big = big.next;
			  small = small.next;
		  }
		  else {
			  return big;
		  }
	  }
	  return big;
  }
  
  public static int countLength(ListNode<Integer> head) {
	  
	  if(head.next == null) {
		  return 0;
	  }
	  ListNode<Integer> fast = head;
	  ListNode<Integer> slow = head;
	  int countlength = 1;
	  while(fast.next.next != null) {
		  slow = slow.next;
		  fast = fast.next.next;
		  if(slow == fast) {
			  slow = slow.next;
			  while(slow != fast) {
				  slow = slow.next;
				  ++countlength;
			  }
			  return countlength;
		  }
	  }
//	  if(fast.next.next == null) {
		  fast = head;
		  countlength = 1;
		  while(fast.next != null) {
			  fast = fast.next;
			  countlength++;
		  }
//	  }
	  return countlength;
	  
  }
  
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
