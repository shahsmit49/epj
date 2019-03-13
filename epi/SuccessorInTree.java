package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class SuccessorInTree {

  public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
    // TODO - you fill in here.
	  
	BinaryTree<Integer> temp;
	if(node.right != null) {
		node = node.right;
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	else {
		temp = node.parent;
		if(temp == null) {
			return null;
		}
		if(temp.left == node) {
			return temp;
		}
		else {
			while(temp.left != node) {
				node = temp;
				temp = node.parent;
				if(temp == null) {
					return null;
				}
			}
			return temp;
		}
	}
  }
  @EpiTest(testDataFile = "successor_in_tree.tsv")
  public static int findSuccessorWrapper(TimedExecutor executor,
                                         BinaryTree<Integer> tree, int nodeIdx)
      throws Exception {
    BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

    BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

    return result == null ? -1 : result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SuccessorInTree.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
