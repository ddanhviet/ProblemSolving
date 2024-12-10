package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Leetcode2924 {
  public int findChampion(int n, int[][] edges) {
    int[] indegree = new int[n];

    for (int i=0; i<edges.length; i++)
      indegree[edges[i][1]]++;

    int champ = -1;
    for (int i=0; i<n; i++)
      if (indegree[i] == 0) {
        if (champ == -1)
          champ = i;
        else return -1;
      }

    return champ;
  }

  public static void main(String[] args) {
    Leetcode2924 findChampionII = new Leetcode2924();
    findChampionII.findChampion(4, new int[][]{
        {0,2},
        {1,3},
        {1,2}
    });
    System.out.println("Done");
  }
}
