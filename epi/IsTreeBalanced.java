package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    
	  int a = treeTraversal(tree);
	  if(a < 0 )
		  return false;
	  return true;
  }
  
  public static int treeTraversal(BinaryTreeNode<Integer> tree) {
	  
	  int leftHeight = 0;
	  int rightHeight = 0;
	  
	  if(tree == null) return 0;
	  
	  else {
		  leftHeight  = treeTraversal(tree.left) + leftHeight;
		  if(leftHeight < 0) {
			  return -10;
		  }
		  rightHeight = treeTraversal(tree.right) + rightHeight;
		  if(Math.abs(leftHeight - rightHeight) > 1) {
			  return -10;  // just to convey that test failed along the recursive stack.
		  }
		  else {
			  
			  return leftHeight >= rightHeight ? leftHeight + 1 : rightHeight + 1;
		  }
	  }
	  
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
