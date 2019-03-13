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
//	BinaryTree<Integer> parentNode = node.parent;
//	if(parentNode != null) {
//		if(parentNode.left == node) {
//			BinaryTree<Integer> tempNode = parentNode.right;
//			if(tempNode != null) {
//				while(tempNode.left != null) {
//					tempNode = tempNode.left;
//				}
//				return tempNode;
//			}
//			else {
//				return parentNode;
//			}
//		}
//		else {
//			BinaryTree<Integer> tempNode = null;
//			
//		}
//	}
	
//	if(node.parent == null) {
//		return null;
//	}
//	BinaryTree<Integer> tempNode = node;
//	while(tempNode.parent != null) {
//		tempNode = node.parent;
//		if(tempNode.left == node) {
//			return tempNode;
//		}
//		node = tempNode;
//	}
//	
//	if(tempNode.right == null) {
//		return tempNode;
//	}
//	else {
//		tempNode = tempNode.right;
//	}
//	if(tempNode.left == null && tempNode.right == null) {
//		return tempNode;
//	}
//	if(tempNode.left != null) {
//		while(tempNode.left != null) {
//			tempNode = tempNode.left;
//		}
//		return tempNode.parent;
//	}
//	if(tempNode.right == null) {
//		return tempNode;
//	}
//	return null;
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
