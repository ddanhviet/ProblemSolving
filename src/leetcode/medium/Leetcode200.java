package leetcode.medium;

import java.util.HashSet;
import java.util.Set;

public class Leetcode200 {
    class QuickUnion {
        int[] qu;
        int[] size;
        int n;

        QuickUnion(int n) {
            this.n = n;
            this.qu = new int[n];
            this.size = new int[n];
            for (int i=0; i<n; i++) {
                qu[i] = i;
                size[i] = 1;
            }
        }

        void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if (size[rootP] >= size[rootQ]) {
                qu[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                qu[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
        }

        boolean find(int p, int q) {
            return root(p) == root(q);
        }

        int root(int p) {
            while(qu[p] != p) {
                qu[p] = qu[qu[p]];
                p = qu[p];
            }
            return qu[p];
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        QuickUnion qu = new QuickUnion(n * m);

        int zeroCount = 0;

        int[] xDirection = new int[]{1, 0, -1, 0};
        int[] yDirection = new int[]{0, 1, 0, -1};
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == '0') {
                    zeroCount++;
                    continue;
                }

                int val = value(i, j, m);
                for (int k=0; k<4; k++) {
                    int newX = i + xDirection[k];
                    int newY = j + yDirection[k];
                    if (newX < 0 || newY < 0 || newX >= n || newY >= m)
                        continue;
                    if (grid[newX][newY] == '1') {
                        qu.union(val, value(newX, newY, m));
                    }
                }
            }
        }

        Set<Integer> islands = new HashSet<>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                System.out.print(qu.root(value(i, j, m)) + " ");
                islands.add(qu.root(value(i, j, m)));
            }
            System.out.println();
        }
        System.out.println(islands.size() + " " + zeroCount);
        return islands.size() - zeroCount;
    }

    int value(int i, int j, int m) {
        return i*m + j;
    }

    public static void main(String[] args) {
        System.out.println("Number of Islands");

        char[][] grid = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        Leetcode200 lc200 = new Leetcode200();
        System.out.println(lc200.numIslands(grid));
    }
}
