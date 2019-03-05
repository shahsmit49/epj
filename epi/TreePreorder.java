package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreePreorder {
  @EpiTest(testDataFile = "tree_preorder.tsv")

  public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
    
	Deque<BinaryTreeNode<Integer>> treeStack = new LinkedList<>();
	List<Integer> answer = new ArrayList<>();
	treeStack.addFirst(tree);
	while(!treeStack.isEmpty()) {
		tree = treeStack.pollFirst();
		if(tree != null) {
			answer.add(tree.data);
			treeStack.addFirst(tree.right);
			treeStack.addFirst(tree.left);
		}
	}
    return answer;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
