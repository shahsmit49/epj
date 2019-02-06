package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PathSum {
  @EpiTest(testDataFile = "path_sum.tsv")

  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int remainingWeight) {
    // TODO - you fill in here.
    return pathSums(tree, remainingWeight) == -1 ? true : false;
  }
  
  public static int pathSums(BinaryTreeNode<Integer> tree,int remainingWeight) {
	  
	  if(tree == null) return 0;
	  
	  else {
		  
		  int data = tree.data;
		  remainingWeight = remainingWeight - data;
		  
		  int leftResult = pathSums(tree.left, remainingWeight);
		  if (leftResult == -1) {
			return -1;
		  }
		  
		  int rightResult = pathSums(tree.right, remainingWeight);
		  if (rightResult == -1) {
				return -1;
		  }
		  
		  if(leftResult == 0 && rightResult == 0) {
			  return remainingWeight == 0 ? -1 : -2; 
		  }
	  }
	  return -2;
	  
	  
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PathSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
