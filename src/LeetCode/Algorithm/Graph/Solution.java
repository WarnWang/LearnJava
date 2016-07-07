package LeetCode.Algorithm.Graph;

import java.util.*;

/**
 * Created by warn on 7/7/2016.
 * Solution to questions with tag graph
 */
public class Solution {
    public static void main(String args[]) {
        Solution test = new Solution();
        test.findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}});
    }

    /**
     * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a
     * rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
     * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
     * <p>
     * Format
     * The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of
     * undirected edges (each edge is a pair of labels).
     * <p>
     * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same
     * as [1, 0] and thus will not appear together in edges.
     * <p>
     * Example 1:
     * <p>
     * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
     * 0
     * |
     * 1
     * / \
     * 2   3
     * return [1]
     * Example 2:
     * <p>
     * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
     * <p>
     * 0  1  2
     * \ | /
     * 3
     * |
     * 4
     * |
     * 5
     * return [3, 4]
     *
     * @param n     total nodes number
     * @param edges edge array
     * @return the nodes with the minimal nodes
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        if (edges == null || edges.length != n - 1) return Collections.emptyList();

        ArrayList<HashSet<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        ArrayList<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) leaves.add(i);
        }

        while (n > 2){
            n -= leaves.size();
            ArrayList<Integer> newLeavels = new ArrayList<>();
            for (int i : leaves) {
                int j = adj.get(i).iterator().next();
                adj.get(j).remove(i);
                if (adj.get(j).size() == 1) newLeavels.add(j);
            }
            leaves = newLeavels;
        }
        return leaves;
    }
}
