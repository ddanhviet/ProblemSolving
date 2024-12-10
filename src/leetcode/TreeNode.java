package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode() {}

  public TreeNode(int val) { this.val = val; }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public static TreeNode deserialize(int[] nodeArray) {
    return null;
  }

  public static TreeNode serialize(Integer[] treeArray) {
    if (treeArray.length == 0)
      return null;

    TreeNode root = new TreeNode(treeArray[0]);
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    for (int i=1; i<treeArray.length; i+=2) {
      TreeNode curr = q.poll();
      if (treeArray[i] != null) {
        curr.left = new TreeNode(treeArray[i]);
        q.add(curr.left);
      }
      if (i+1 < treeArray.length && treeArray[i+1] != null) {
        curr.right = new TreeNode(treeArray[i+1]);
        q.add(curr.right);
      }
    }
    return root;
  }
}
