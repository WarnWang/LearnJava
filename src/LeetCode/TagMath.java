package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by warn on 28/3/2016.
 * Use to store those with tag math
 */
public class TagMath {
    /**
     * Write a program to check whether a given number is an ugly number.
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14
     * is not ugly since it includes another prime factor 7.
     * Note that 1 is typically treated as an ugly number.
     *
     * @param num a given number
     * @return whether this number is ugly
     */
    public boolean isUgly(int num) {
        if (num <= 0) return false;
        if (num <= 3) return true;
        while ((num & 1) == 0) num >>= 1;
        while (num % 5 == 0) num /= 5;
        while (num % 3 == 0) num /= 3;
        return num == 1;
    }

    /**
     * Write a program to find the n-th ugly number.
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9,
     * 10, 12 is the sequence of the first 10 ugly numbers.
     * Note that 1 is typically treated as an ugly number.
     *
     * @param n the number of ugly number index
     * @return the n-th ugly number
     */
    public int nthUglyNumber(int n) {
        if (n <= 5) return n;
        List<Integer> uglyNumber = new ArrayList<>(n);
        for (int i = 0; i < 5; i++) uglyNumber.add(i + 1);
        for (int i = 5; i < n; i++) {
            int nextUgly = uglyNumber.get(i - 1), temp;
            do {
                temp = ++nextUgly;
                if (nextUgly % 3 == 0) temp /= 3;
                else if (nextUgly % 2 == 0) temp /= 2;
                else if (nextUgly % 5 == 0) temp /= 5;
            } while (uglyNumber.indexOf(temp) < 0);
            uglyNumber.add(nextUgly);
        }
        return uglyNumber.get(n - 1);
    }
}
