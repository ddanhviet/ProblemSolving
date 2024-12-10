package leetcode.hard;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.TreeNode;

public class Leetcode297 {
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    StringBuilder sb = new StringBuilder();
    while (!q.isEmpty()) {
      var next = q.remove();
      if (next != null) {
        sb.append(next.val).append(",");
        q.add(next.left);
        q.add(next.right);
      } else {
        sb.append("n").append(",");
      }
    }

    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    var vals = data.split(",");
    if (vals.length == 0) {
      return null;
    }

    Queue<TreeNode> nodeQ = new LinkedList<>();

    TreeNode root = parseNode(vals[0]);
    if (root == null) {
      return null;
    }
    nodeQ.add(root);

    int index = 1;
    while (!nodeQ.isEmpty()) {
      TreeNode next = nodeQ.remove();

      next.left = parseNode(vals[index]);
      if (next.left != null) {
        nodeQ.add(next.left);
      }
      next.right = parseNode(vals[index + 1]);
      if (next.right != null) {
        nodeQ.add(next.right);
      }

      index += 2;
    }

    return root;
  }

  private TreeNode parseNode(String str) {
    if (str.equals("n")) {
      return null;
    }
    return new TreeNode(Integer.parseInt(str));

  }
}
