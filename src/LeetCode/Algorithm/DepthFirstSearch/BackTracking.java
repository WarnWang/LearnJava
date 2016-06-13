package LeetCode.Algorithm.DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by warn on 13/6/2016.
 * Store solution to test with backtracking
 */
public class BackTracking {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        if (k > n) return combinations;
        combine(n, k, new int[k], 0, combinations);
        return combinations;
    }

    private void combine(int n, int k, int[] path, int index, List<List<Integer>> combinations){
        if (index == k) {
            ArrayList<Integer> result = new ArrayList<>();
            for (int num: path) result.add(num);
            combinations.add(result);
        } else {
            int start, end;
            if (index > 0) start = path[index - 1] + 1;
            else start = 1;
            end = n - k + 2 + index;
            for (int i = start; i < end; i++) {
                path[index] = i;
                combine(n, k, path, index + 1, combinations);
            }
        }
    }
}
