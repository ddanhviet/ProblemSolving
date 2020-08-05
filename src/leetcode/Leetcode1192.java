package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode1192 {

    class Graph {
        List<Integer>[] adj;
        int v;

        Graph(int v) {
            this.v = v;
            this.adj = (List<Integer>[]) new List[v];
        }

        void addEdge(int either, int other) {
            addDirectedEdge(either, other);
            addDirectedEdge(other, either);
        }

        void addDirectedEdge(int either, int other) {
            if (adj[either] == null)
                adj[either] = new ArrayList<>();
            adj[either].add(other);
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Graph g = new Graph(n);

        for (List<Integer> edge : connections)
            g.addEdge(edge.get(0), edge.get(1));

        int[] id = new int[n];
        int[] low = new int[n];
        for (int i=1; i<n; i++) {
            low[i] = n;
            id[i] = -1;
        }

        List<List<Integer>> critical = new ArrayList<>();
        for (int i=0; i<n; i++) {
            if (id[i] == -1)
                dfs(id, low, g, 0, -1, critical);
        }

        return critical;
    }

    int time = 0;

    private void dfs(int[] id, int[] low, Graph g, int v, int pre, List<List<Integer>> critical) {
        id[v] = low[v] = ++time;

        // System.out.println(v + " " + low[v]);
        // System.out.println("id " + id[v]);

        for (int next : g.adj[v]) {
            // System.out.println("next " + next + " ");
            if (id[next] == -1) {

                dfs(id, low, g, next, v, critical);
                low[v] = Math.min(low[next], low[v]);
                if (low[next] > id[v]) {
                    List<Integer> thisEdge = new ArrayList<>();
                    thisEdge.add(v);
                    thisEdge.add(next);
                    critical.add(thisEdge);
                }
            } else if (next != pre) { // next -> v -> next
                low[v] = Math.min(low[next], low[v]);
            }
            // System.out.println(low[v]);
        }

    }

    public static void main(String[] args) {
        Leetcode1192 lc1192 = new Leetcode1192();
        int[][] inputConnections = new int[][] {
                {1,0},{2,0},{3,0},{4,1},{5,3},{6,1},{7,2},{8,1},{9,6},{9,3},
                {3,2},{4,2},{7,4},{6,2},{8,3},{4,0},{8,6},{6,5},{6,3},
                {7,5},{8,0},{8,5},{5,4},{2,1},{9,5},{9,7},{9,4},{4,3}
        };
        List<List<Integer>> connections = new ArrayList<>();
        for (int i=0; i< inputConnections.length; i++) {
            List<Integer> edge = new ArrayList<>();
            edge.add(inputConnections[i][0]);
            edge.add(inputConnections[i][1]);
            connections.add(edge);
        }

        List<List<Integer>> criticalConnections = lc1192.criticalConnections(10, connections);
        for (List<Integer> edge : criticalConnections)
            System.out.println(edge.get(0) + " " + edge.get(1));
    }
}
