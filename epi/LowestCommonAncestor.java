package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
	
	
	
	private static class Status {
		
		int numOfTargets;
		BinaryTreeNode<Integer> ancestor;
		public Status(int numOfTargets, BinaryTreeNode<Integer> ancestor) {
			super();
			this.numOfTargets = numOfTargets;
			this.ancestor = ancestor;
		}
	}
	
	public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
											BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    
	
		return recursiveTree(tree, node0, node1).ancestor;
	}
	
	public static Status recursiveTree(BinaryTreeNode<Integer> tree,
										BinaryTreeNode<Integer> node0,
										BinaryTreeNode<Integer> node1) {
		
		if(tree == null) return new Status(0, null);
		
		Status statusOfLeft = recursiveTree(tree.left, node0, node1);
		if(statusOfLeft.numOfTargets == 2) {
			return statusOfLeft;
		}
		
		Status statusOfRight = recursiveTree(tree.right, node0, node1);
		if(statusOfRight.numOfTargets == 2) {
			return statusOfRight;
		}
		
		int numOftargets = statusOfLeft.numOfTargets + statusOfRight.numOfTargets + (tree == node0 ? 1 : 0) 
																					+ (tree == node1 ? 1 : 0);
		
		return new Status(numOftargets, numOftargets == 2 ? tree : null);
		
	}
  
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
