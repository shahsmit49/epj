package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    int height1 = getHeight(node0);
    int height2 = getHeight(node1);
    int diff = height1 - height2;
    
    if(diff > 0 ) {
    	BinaryTree<Integer> node = makeEqual(node0, diff);
    	return commonHead(node, node1);
    }
    else {
    	BinaryTree<Integer> node = makeEqual(node1, Math.abs(diff));
    	return commonHead(node, node0);
    }
  }
  
  public static int getHeight(BinaryTree<Integer> node) {
	  
	  int height = 0 ;
	  
	  while(node.parent != null) {
		  node = node.parent;
		  height++;
	  }
	  return height;
	  
//	  if(node == null) return 1;
//	  int height = getHeight(node.parent);
//	  return height + 1;
	  
  }
  
  public static BinaryTree<Integer> makeEqual(BinaryTree<Integer> node, int steps) {
	  
	  while(steps-- > 0 && node.parent != null) {
		  node = node.parent;
	  }
	  return node;
  }
  
  public static BinaryTree<Integer> commonHead(BinaryTree<Integer> node0, BinaryTree<Integer> node1) {
	  
	  while(node0 != node1) {
		  node0 = node0.parent;
		  node1 = node1.parent;
	  }
	  return node0;
	  
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
