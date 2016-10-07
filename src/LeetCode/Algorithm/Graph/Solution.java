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

        while (n > 2) {
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

    /**
     * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
     * number (floating point number). Given some queries, return the answers. If the answer does not exist, return
     * -1.0.
     * <p>
     * Example:
     * Given a / b = 2.0, b / c = 3.0.
     * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
     * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
     * <p>
     * The input is: vector<pair<string, string>> equations, vector<double>& values,
     * vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive.
     * This represents the equations. Return vector<double>.
     * <p>
     * According to the example above:
     * <p>
     * equations = [ ["a", "b"], ["b", "c"] ],
     * values = [2.0, 3.0],
     * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     * The input is always valid. You may assume that evaluating the queries will result in no division by zero and
     * there is no contradiction.
     *
     * @param equations the object to represent x / y
     * @param values    the result of the above equations
     * @param queries   query
     * @return the result of the query
     */
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (equations == null || equations.length == 0) return new double[0];
        double[] results = new double[queries.length];
        HashMap<String, HashMap<String, Double>> equationGraph = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String a = equations[i][0], b = equations[i][1];
            double result = values[i], verseResult = 1.0 / result;

            if (!equationGraph.containsKey(a)) equationGraph.put(a, new HashMap<>());
            equationGraph.get(a).put(b, result);

            if (!equationGraph.containsKey(b)) equationGraph.put(b, new HashMap<>());
            equationGraph.get(b).put(a, verseResult);
        }

        for (int i = 0; i < results.length; i++) {
            results[i] = findQueryResult(queries[i][0], queries[i][1], equationGraph);
        }
        return results;
    }

    private double findQueryResult(String a, String b, HashMap<String, HashMap<String, Double>> equationMap) {
        if (a.equals(b) && equationMap.containsKey(a)) return 1.0;
        HashSet<String> exploredSet = new HashSet<>();
        ArrayList<String> frontierList = new ArrayList<>();
        ArrayList<Double> resultList = new ArrayList<>();
        frontierList.add(a);
        exploredSet.add(a);
        resultList.add(1.0);
        while (!frontierList.isEmpty()) {
            ArrayList<String> nextLevelStrList = new ArrayList<>();
            ArrayList<Double> nextLevelResList = new ArrayList<>();
            for (int i = 0; i < frontierList.size(); i++) {
                String frontier = frontierList.get(i);
                if (!equationMap.containsKey(frontier)) continue;
                for (String divider : equationMap.get(frontier).keySet()) {
                    if (exploredSet.contains(divider)) continue;
                    exploredSet.add(divider);
                    nextLevelStrList.add(divider);
                    double result = resultList.get(i) * equationMap.get(frontier).get(divider);
                    nextLevelResList.add(result);
                    equationMap.get(a).put(divider, result);
                    if (divider.equals(b)) return result;
                }
            }
            frontierList = nextLevelStrList;
            resultList = nextLevelResList;
        }
        return -1.0;
    }
}
