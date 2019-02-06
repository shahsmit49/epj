package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SumRootToLeaf {

  public static int targetSum = 0;
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    
	inorderTrav(tree, -1);
	int temp = targetSum;
	targetSum = 0;
	return temp;
  }
  
  public static void inorderTrav(BinaryTreeNode<Integer> tree, int dataMSB) {
	  
	  if(tree == null) return;
	  
	  else {
		  
		  int data = tree.data;
		  if(dataMSB != -1) {
			  dataMSB = dataMSB << 1;
			  dataMSB = dataMSB | data;
		  }
		  if(dataMSB == -1) {
			  dataMSB = data;
		  }
		  inorderTrav(tree.left, dataMSB);
		  
		  inorderTrav(tree.right, dataMSB);
		  if(tree.left == null && tree.right == null) {
			  targetSum = targetSum + dataMSB;
		  }
	  }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
