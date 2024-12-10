package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Leetcode1762 {
  public int[] findBuildings(int[] heights) {
    Deque<Integer> s = new LinkedList<>();
    int maxHeight = 0;
    for (int i=heights.length-1; i>=0; i--) {
      if (heights[i] > maxHeight) {
        maxHeight = heights[i];
        s.addFirst(i);
      }
    }

    return s.stream()
        .mapToInt(i -> i)
        .toArray();
  }

  public int[] findBuildingsWithStack(int[] heights) {
    Stack<Integer> s = new Stack<>();
    for (int i=0; i<heights.length; i++) {
      while (!s.isEmpty() && heights[s.peek()] <= heights[i]) {
        s.pop();
      }
      s.push(i);
    }

    return s.stream()
        .mapToInt(i -> i)
        .toArray();
  }

  public static void main(String[] args) {
    Leetcode1762 buildingsWithAnOceanView = new Leetcode1762();
    var buildings = buildingsWithAnOceanView.findBuildingsWithStack(new int[]{4,2,3,1});
    for (int b: buildings)
      System.out.print(b + " ");
    System.out.println("1762. Buildings With an Ocean View");
  }
}
