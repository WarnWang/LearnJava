package LeetCode.Algorithm.Math;

import java.util.ArrayList;
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

    /**
     * QuestionEditorial Solution  My Submissions
     * Total Accepted: 51892 Total Submissions: 419161 Difficulty: Hard
     * Validate if a given string is numeric.
     * <p>
     * Some examples:
     * "0" => true
     * " 0.1 " => true
     * "abc" => false
     * "1 a" => false
     * "2e10" => true
     * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front
     * before implementing one.
     *
     * @param s a string s
     * @return whether this string is a number or not
     */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        s = s.trim();
        if (s.length() == 0) return false;
        char[] chars;
        if (s.startsWith("-") || s.startsWith("+")) chars = s.substring(1).toCharArray();
        else chars = s.toCharArray();
        boolean containE = false, containPoint = false, containsDigit = false;
        if ((chars.length == 1 && !Character.isDigit(chars[0])) || !(Character.isDigit(chars[0]) || chars[0] == '.')
                || !(Character.isDigit(chars[chars.length - 1]) || chars[chars.length - 1] == '.'))
            return false;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == 'e') {
                if (containE || !containsDigit || i == chars.length - 1) return false;
                if (chars[i + 1] == '+' || chars[i + 1] == '-') i++;
                containE = true;
                containPoint = true;
            } else if (c == '.') {
                if (containPoint) return false;
                containPoint = true;
            } else if (!Character.isDigit(c)) return false;
            else containsDigit = true;
        }
        return containsDigit;
    }

    /**
     * The set [1,2,3,…,n] contains a total of n! unique permutations.
     * <p>
     * By listing and labeling all of the permutations in order,
     * We get the following sequence (ie, for n = 3):
     * <p>
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     * <p>
     * Note: Given n will be between 1 and 9 inclusive.
     *
     * @param n a total of n! unique permutations
     * @param k the index of the sequence
     * @return the kth permutation sequence
     */
    public String getPermutation(int n, int k) {
        char[] result = new char[n];
        ArrayList<Character> remain = new ArrayList<>();
        for (char c = '1', i = 0; i < n; i++, c++) {
            remain.add(c);
        }

        int[] permutationSequence = new int[n];
        permutationSequence[0] = 1;
        for (int i = 1; i < n; i++) {
            permutationSequence[i] = permutationSequence[i - 1] * (i);
        }
        int i = 0;
        for (; i < n - 1; i++) {
            int permutationsNum = permutationSequence[n - i - 1];
            int charIndex;
            if (k == 0) charIndex = 0;
            else if (k % permutationsNum == 0) {
                charIndex = k / permutationsNum - 1;
                k -= charIndex * permutationsNum;
            } else {
                charIndex = k / permutationsNum;
                k -= charIndex * permutationsNum;
            }

            result[i] = remain.get(charIndex);
            remain.remove(charIndex);
        }
        result[i] = remain.get(0);
        return new String(result);
    }

    /**
     * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every
     * other number afterward until you reach the end of the list.
     * <p>
     * Repeat the previous step again, but this time from right to left, remove the right most number and every other
     * number from the remaining numbers.
     * <p>
     * We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
     * <p>
     * Find the last number that remains starting with a list of length n.
     *
     * @param n initial number
     * @return the last number that remains starting with a list of length n.
     */
    public int lastRemaining(int n) {
        boolean fromStart = true;
        int min = 1, max = n, step = 2;

        while (min != max) {
            System.out.println(min + ", " + max + ", " + step);
            if (fromStart) {
                if ((max - min) % step == 0) max -= step;
                min += step;
            } else {
                if ((max - min) % step == 0) min += step;
                max -= step;
            }
            fromStart = !fromStart;
            step *= 2;
        }
        return min;
    }
}
