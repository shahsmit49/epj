package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    
    return tree == null || checkSymmetry(tree.left, tree.right);
  }
  
  public static boolean checkSymmetry(BinaryTreeNode<Integer> treeLeft, BinaryTreeNode<Integer> treeRight) {
	  
	  if(treeLeft == null && treeRight == null) {
		  return true;
	  }
	  else if(treeLeft != null && treeRight != null) {
		  
		  return treeLeft.data == treeRight.data && checkSymmetry(treeLeft.left, treeRight.right)
				  && checkSymmetry(treeLeft.right, treeRight.left);
	  }
	  return false;
	  
  }
  

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
