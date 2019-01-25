package epi;
import java.util.HashSet;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class IsListCyclic {

  public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
	  // TODO - you fill in here.
	  ListNode<Integer> fast = head ; ListNode<Integer> slow = head;
	  
	  while(fast != null && fast.next != null) {
		fast = fast.next.next;
		slow = slow.next;
		if(slow == fast) {
			slow = slow.next;
			int loopCount = 1;
			while(slow != fast) {
				loopCount = loopCount + 1;
				slow = slow.next;
			}
			slow = head;
			int tempCount = loopCount;
			while(tempCount > 0) {
				slow = slow.next;
				tempCount = tempCount-1;
			}
			fast = head;
			while(slow != fast) {
				slow = slow.next;
				fast = fast.next;
			}
			return fast;
		}
	  }
	  return null;
//	  HashSet<Integer> hashMap = new HashSet<>();
//	  while(head.next != null) {
//		  ListNode<Integer> temp = head.next;
//		  if(hashMap.contains(temp.hashCode())) {
//			  return temp;
//		  }
//		  else {
//			  hashMap.add(temp.hashCode());
//			  head = head.next;
//		  }
//	  }
//	  
//	  return null;
  }
  @EpiTest(testDataFile = "is_list_cyclic.tsv")
  public static void HasCycleWrapper(TimedExecutor executor,
                                     ListNode<Integer> head, int cycleIdx)
      throws Exception {
    int cycleLength = 0;
    if (cycleIdx != -1) {
      if (head == null) {
        throw new RuntimeException("Can't cycle empty list");
      }
      ListNode<Integer> cycleStart = null, cursor = head;
      while (cursor.next != null) {
        if (cursor.data == cycleIdx) {
          cycleStart = cursor;
        }
        cursor = cursor.next;
        if (cycleStart != null) {
          cycleLength++;
        }
      }
      if (cursor.data == cycleIdx) {
        cycleStart = cursor;
      }
      if (cycleStart == null) {
        throw new RuntimeException("Can't find a cycle start");
      }
      cursor.next = cycleStart;
      cycleLength++;
    }

    ListNode<Integer> result = executor.run(() -> hasCycle(head));

    if (cycleIdx == -1) {
      if (result != null) {
        throw new TestFailure("Found a non-existing cycle");
      }
    } else {
      if (result == null) {
        throw new TestFailure("Existing cycle was not found");
      }

      ListNode<Integer> cursor = result;
      do {
        cursor = cursor.next;
        cycleLength--;
        if (cursor == null || cycleLength < 0) {
          throw new TestFailure(
              "Returned node does not belong to the cycle or is not the closest node to the head");
        }
      } while (cursor != result);

      if (cycleLength != 0) {
        throw new TestFailure(
            "Returned node does not belong to the cycle or is not the closest node to the head");
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListCyclic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
