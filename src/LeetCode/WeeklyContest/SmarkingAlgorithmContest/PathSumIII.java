package LeetCode.WeeklyContest.SmarkingAlgorithmContest;

import LeetCode.DataTypes.TreeNode;

import java.util.ArrayList;

/**
 * Created by warn on 23/10/2016.
 * You are given a binary tree in which each node contains an integer value.
 * <p>
 * Find the number of paths that sum to a given value.
 * <p>
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent
 * nodes to child nodes).
 * <p>
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * <p>
 * Example:
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <p>
 * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 * <p>
 * Return 3. The paths that sum to 8 are:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 */
public class PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        return pathSum(root, new ArrayList<>(), sum);
    }

    private int pathSum(TreeNode root, ArrayList<Integer> path, int sum) {
        if (root == null) return 0;
        ArrayList<Integer> nextPath = new ArrayList<>();
        int count = 0;
        for (int i: path){
            int result = i + root.val;
            if (result == sum) count++;
            nextPath.add(result);
        }
        if (root.val == sum) count++;
        nextPath.add(root.val);
        return count + pathSum(root.left, nextPath, sum) + pathSum(root.right, nextPath, sum);
    }
}
