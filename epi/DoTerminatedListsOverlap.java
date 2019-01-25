package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
	  // TODO - you fill in here.
	  ListNode<Integer> dummy0 = l0;
	  ListNode<Integer> dummy1 = l1;
	  int countOfL0 = 1;
	  int countOfL1 = 1;
	  int countDiff = -1;
	  
	  if((l1==null) || (l0==null)) {
		  return null;
	  }
	  while((dummy0.next != null) || (dummy1.next != null)) {
		  if(dummy0.next != null) {
			  dummy0 = dummy0.next;
			  countOfL0++;
		  }
		  if(dummy1.next != null) {
			  dummy1 = dummy1.next;
			  countOfL1++;
		  }
		  if(dummy0 == dummy1) {
			  countDiff = Math.abs((countOfL0 - countOfL1));
		  }
	  }
	  if(countDiff == -1) {
		  return null;
	  }
	  else if(countOfL0 >= countOfL1) {
		  return returnAns(l0,l1,countDiff);
	  }
	  else {
		  return returnAns(l1,l0,countDiff);
	  }
  }
  
  public static ListNode<Integer> returnAns(ListNode<Integer> larger, ListNode<Integer> smaller, int diff) {
	  
	  while(diff-- > 0) {
		  larger = larger.next;
	  }
	  
	  while(larger.next != smaller.next) {
		  larger = larger.next;
		  smaller = smaller.next;
	  }
	  
	  return smaller.next;
	  
  }
  
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
