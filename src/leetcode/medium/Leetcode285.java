package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import leetcode.TreeNode;

public class Leetcode285 {
  private TreeNode traverse(Queue<TreeNode> q, Deque<TreeNode> inorder, int target) {
    if (q.isEmpty()) {
      return null;
    }

    var next = q.poll();
    if (next == null) {
      return null;
    }

    q.add(next.left);
    var found = traverse(q, inorder, target);
    if (found != null) {
      return found;
    }

    var tNode = inorder.peekLast();
    inorder.add(next);
    if (tNode != null && tNode.val == target) {
      return next;
    }

    q.add(next.right);
    return traverse(q, inorder, target);
  }

  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    Deque<TreeNode> inorder = new LinkedList<>();

    return traverse(q, inorder, p.val);
  }
}
