package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
	  
	  ListNode<Integer> response  = L1;
	  ListNode<Integer> temp = L1;
	  if(L1 == null) {
		  return L2;
	  }
	  if(L2 == null) {
		  return L1;
	  }
	  
	  int carry = 0;
	  int ans = 0;
	  int sum = 0;
	  while(L1 != null || L2 != null) {
		  
		  sum = 0; ans = 0;
		  if( L1 != null) {
			  sum += L1.data;
			  temp = L1;
			  L1 = L1.next;
		  }
		  if(L2 != null) {
			  sum += L2.data;
			  L2 = L2.next;
		  }
		  sum += carry;
		  carry = 0;
		  if( sum > 9) {
			  carry = sum / 10;
			  ans = sum % 10;
		  }
		  else {
			  ans = sum;
		  }
		  
		  temp.data = ans;
		  
		  if(L1 == null && L2 != null) {

			  temp.next = L2;
			  L2.data = L2.data + carry;
			  
			  while(L2.data > 9) {
				  carry = L2.data / 10;
				  L2.data = L2.data % 10;
				  temp = L2;
				  L2 = L2.next;
				  if(L2 == null) {
					  ListNode<Integer> newNode = new ListNode<Integer>(carry, null);
					  temp.next = newNode;
					  break;
				  }
				  else  {
					  L2.data = L2.data + carry;
				  }
			  }
			  break;
		  }
		  if(L1 == null && L2 == null) {
			  if(carry > 0) {
				  ListNode<Integer> newNode = new ListNode<Integer>(carry, null);
				  temp.next = newNode;
				  carry = 0;
			  }
		  }	  
	  }
	  return response;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
