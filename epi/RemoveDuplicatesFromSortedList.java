package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    
	if (L == null) {
		return null;
	}
	ListNode<Integer> iter = L;
	int temp = iter.data;
	while(iter != null && iter.next != null) {
		
		if(temp != iter.next.data) {
			iter = iter.next;
			temp = iter.data;
		}
		else {
			iter.next = iter.next.next;
		}
		
	}
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
