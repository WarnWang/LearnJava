package LeetCode.Algorithm.Math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by warn on 21/6/2016.
 * Save function with tag math
 */
public class Solution {

    public static void main(String args[]) {
        Solution test = new Solution();
        test.canMeasureWater(2, 6, 1);
    }

    /**
     * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
     * <p>
     * Example:
     * Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding
     * [11,22,33,44,55,66,77,88,99])
     * https://leetcode.com/problems/count-numbers-with-unique-digits/
     *
     * @param n a non-negative integer n
     * @return the number of all unique digits
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n < 1) return 1;
        if (n >= 10) n = 10;
        int num = 0;
        for (int i = 0; i <= n; i++) {
            int current = 1;
            for (int j = 0; j < i; j++) {
                if (j == 0) current *= 9;
                else current *= (10 - j);
            }
            num += current;
        }
        return num;
    }

    /**
     * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available.
     * You need to determine whether it is possible to measure exactly z litres using these two jugs.
     * <p>
     * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the
     * end.
     * <p>
     * Operations allowed:
     * <p>
     * Fill any of the jugs completely with water.
     * Empty any of the jugs.
     * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
     * Example 1: (From the famous "Die Hard" example)
     * <p>
     * Input: x = 3, y = 5, z = 4
     * Output: True
     * Example 2:
     * <p>
     * Input: x = 2, y = 6, z = 5
     * Output: False
     *
     * @param x the first jug size
     * @param y the second jug size
     * @param z measure exactly z litres
     * @return possible or not
     */
    public boolean canMeasureWater(int x, int y, int z) {
        if (z > x + y) return false;
        HashSet<Integer> reachLitres = new HashSet<>();
        reachLitres.add(x);
        reachLitres.add(y);
        reachLitres.add(x + y);
        reachLitres.add(0);
        HashSet<String> exploredState = new HashSet<>();
        Stack<int[]> stateStack = new Stack<>();
        stateStack.push(new int[]{0, 0});
        while (!stateStack.isEmpty()) {
            int[] frontier = stateStack.pop();
            if (exploredState.contains(Arrays.toString(frontier))) continue;
            exploredState.add(Arrays.toString(frontier));

            // pour water into jug 0
            if (frontier[0] < x) {
                int[] newState = {x, frontier[1]};
                reachLitres.add(newState[0] + newState[1]);
                if (!exploredState.contains(Arrays.toString(newState))) {
                    stateStack.push(newState);
                }
            }

            // pour water into jug 1
            if (frontier[1] < y) {
                int[] newState = {frontier[0], y};
                reachLitres.add(newState[0] + newState[1]);
                if (!exploredState.contains(Arrays.toString(newState))) {
                    stateStack.push(newState);
                }
            }

            // pour water out of jugs
            if (frontier[0] > 0) {
                int[] newState = {0, frontier[1]};
                reachLitres.add(newState[0] + newState[1]);
                if (!exploredState.contains(Arrays.toString(newState))) {
                    stateStack.push(newState);
                }
            }

            if (frontier[1] > 0) {
                int[] newState = {frontier[0], 0};
                reachLitres.add(newState[0] + newState[1]);
                if (!exploredState.contains(Arrays.toString(newState))) {
                    stateStack.push(newState);
                }
            }

            // pour water from 0 to 1
            if (frontier[0] > 0 && frontier[1] < y) {
                int maxSpace = y - frontier[1];
                int[] newState;
                if (maxSpace >= frontier[0]) newState = new int[]{0, frontier[0] + frontier[1]};
                else newState = new int[]{frontier[0] - maxSpace, y};
                reachLitres.add(newState[0]);
                reachLitres.add(newState[1]);
                reachLitres.add(newState[0] + newState[1]);
                if (!exploredState.contains(Arrays.toString(newState))) {
                    stateStack.push(newState);
                }
            }

            if (frontier[0] < x && frontier[1] > 0) {
                int maxSpace = x - frontier[0];
                int[] newState;
                if (maxSpace >= frontier[1]) newState = new int[]{frontier[0] + frontier[1], 0};
                else newState = new int[]{x, frontier[1] - maxSpace};
                reachLitres.add(newState[0]);
                reachLitres.add(newState[1]);
                reachLitres.add(newState[0] + newState[1]);
                if (!exploredState.contains(Arrays.toString(newState))) {
                    stateStack.push(newState);
                }
            }
            if (reachLitres.contains(z)) return true;
        }
        return false;
    }

    public boolean canMeasureWaterGCD(int x, int y, int z) {
        if (x == z || y == z || z == x + y || z == 0) return true;
        if (z > x + y) return false;
        if (x == 0) return false;
        if (y == 0) return false;
        int a = Math.max(x, y);
        int b = Math.min(x, y);
        int r = a % b;
        if (r == 0) return z % b == 0;
        else return z % getGCD(r, b) == 0;
    }

    private int getGCD(int a, int b) {
        if (a % b == 0) {
            return b;
        } else return getGCD(b, a % b);
    }

    /**
     * Reverse digits of an integer.
     * <p>
     * Example1: x = 123, return 321
     * Example2: x = -123, return -321
     *
     * @param x an digits of the integer
     * @return the inverse integer
     */
    public int reverse(int x) {
        if (x == 0) return 0;
        int maxCap = Integer.MAX_VALUE / 10;
        int reverse = 0;
        int multiplier = (x > 0) ? 1 : (-1);
        for (;;) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
            if (x == 0) break;
            if (reverse > maxCap || reverse < -maxCap) return 0;
        }
        return multiplier * reverse;
    }
}