package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreeInorder {
  @EpiTest(testDataFile = "tree_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {

	  boolean flag = true;
	  List<Integer> answer = new ArrayList<>();
	  Deque<BinaryTreeNode<Integer>> treeStore = new LinkedList<>();
	  treeStore.addFirst(tree);
	  while(tree != null) {
		  if(tree.left != null && flag == true) {
			  tree = tree.left;
			  treeStore.addFirst(tree);
		  } 
		  else {
			  if(tree.right != null) {
				  answer.add(tree.data);
				  tree = tree.right;
				  treeStore.removeFirst();
				  treeStore.addFirst(tree);
				  flag = true;
			  }
			  else {
				  answer.add(tree.data);
				  tree = treeStore.removeFirst();
				  tree = treeStore.peekFirst();
				  flag = false;
			  }
		  }
	  }
    return answer;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
