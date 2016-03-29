package LeetCode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

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
        if (n <= 6) return n;
        Set<Long> uglyNumber = new HashSet<>();
        PriorityQueue<Long> uglyNumberQueue = new PriorityQueue<>();
        uglyNumber.add(1L);
        uglyNumberQueue.add(1L);
        for (int i = 1; i < n; i++) {
            long smallest = uglyNumberQueue.remove();
            long small = 2 * smallest, small2 = 3 * smallest, small3 = 5 * smallest;
            if (!uglyNumber.contains(small) && small > 0) {
                uglyNumberQueue.add(small);
                uglyNumber.add(small);
            }
            if (!uglyNumber.contains(small2) && small2 > 0) {
                uglyNumber.add(small2);
                uglyNumberQueue.add(small2);
            }
            if (!uglyNumber.contains(small3) && small3 > 0) {
                uglyNumber.add(small3);
                uglyNumberQueue.add(small3);
            }
        }
        return uglyNumberQueue.peek().intValue();
    }
}
