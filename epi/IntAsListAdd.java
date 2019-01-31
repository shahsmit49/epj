package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
	  
	  ListNode<Integer> response  = L1;
	  if(L1 == null) {
		  return L2;
	  }
	  if(L2 == null) {
		  return L1;
	  }
	  
	  int carry = 0;
	  int ans = 0;
	  int a = 0,b = 0;
	  while(L1 != null || L2 != null) {
		  
		  a = 0; b = 0; ans = 0;
		  if( L1 != null)
			  a = L1.data;
		  if(L2 != null)
			  b = L2.data;
		  a = a + b + carry;
		  carry = 0;
		  if( a > 9) {
			  carry = a / 10;
			  ans = a % 10;
		  }
		  else {
			  ans = a;
		  }
		  
		  L1.data = ans;
		  
		  if(L1.next == null && L2 != null) {
			  
			  
			  L2 = L2.next;
			  L1.next = L2;
			  if(L2 != null) {
				  L2.data = L2.data + carry;
				  
				  while(L2.data > 9) {
					  carry = L2.data / 10;
					  L2.data = L2.data % 10;
					  ListNode<Integer> temp = L2;
					  L2 = L2.next;
					  if(L2 == null) {
						  ListNode<Integer> newNode = new ListNode<Integer>(carry, null);
						  temp.next = newNode;
						  L2 = temp;
					  }
					  else 
						  L2.data = L2.data + carry;
				  }
			  }
			  else {
				  if(carry > 0) {
					  ListNode<Integer> newNode = new ListNode<Integer>(carry, null);
					  L1.next = newNode;
				  }
			  }

			  break;
		  }
		  if(L1 != null && L1.next == null && L2 == null) {
			  if(carry > 0) {
				  ListNode<Integer> newNode = new ListNode<Integer>(carry, null);
				  L1.next = newNode;
				  carry = 0;
			  }
		  }
		  
		  if( L1 != null)
			  L1 = L1.next;
		  if(L2 != null)
			  L2 = L2.next;
		  
		  
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
