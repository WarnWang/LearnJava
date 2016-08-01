package LeetCode.Algorithm.Math;

import java.util.Arrays;

/**
 * Created by warn on 1/8/2016.
 * Save function with tag math
 */
public class Solution2 {

    /**
     * Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large positive integer
     * given in the form of an array.
     * <p>
     * Example1:
     * <p>
     * a = 2
     * b = [3]
     * <p>
     * Result: 8
     * Example2:
     * <p>
     * a = 2
     * b = [1,0]
     * <p>
     * Result: 1024
     *
     * @param a a positive integer
     * @param b an extremely large positive
     * @return the result of a^b mod 1337
     */
    public int superPow(int a, int[] b) {
        if (b == null || b.length == 0 || a % 1337 == 0) return 0;
        if (isTwoArrayEqual(b, new int[b.length])) return 1;
        if (a > 1337) a %= 1337;
        int mod = a;
        int length = 1;
        while (true) {
            mod = (a * mod) % 1337;
            if (mod == a) break;
            else length++;
        }

        int n = arrayModInteger(b, length);

        for (int i = 1; i < n; i++) {
            mod = (a * mod) % 1337;
        }
        return mod;
    }

    private void increaseNumArray(int[] numArray, int increase) {
        int c = increase;
        for (int i = numArray.length - 1; i >= 0 && c != 0; i--) {
            numArray[i] += c;
            c = numArray[i] / 10;
            numArray[i] %= 10;
        }
        if (c != 0) Arrays.fill(numArray, 9);
    }

    private boolean isTwoArrayEqual(int[] a, int[] b) {
        return Arrays.toString(a).equals(Arrays.toString(b));
    }

    private int compareTwoIntArray(int[] a, int[] b) {
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] < b[i]) return -1;
            else if (b[i] < a[i]) return 1;
        }
        return 0;
    }

    private int[] differential(int[] a, int[] b) {
        int[] result = new int[a.length];
        for (int i = a.length - 1, c = 0; i >= 0; i--) {
            if (a[i] >= b[i] + c) {
                c = 0;
                result[i] = a[i] - b[i] - c;
            } else {
                result[i] = 10 + a[i] - b[i] - c;
                c = 1;
            }
        }
        return result;
    }

    private int arrayModInteger(int[] nums, int mod) {
        int n = 0;
        for (int i = 0, len = nums.length; i < len; i++) {
            n = (n * 10 + nums[i]) % mod;
        }
        return n;
    }
}
