package Codility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by warn on 3/2/2016.
 * A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.
 * <p>
 * A prime D is called a prime divisor of a positive integer P if there exists a positive integer K such that D * K = P. For example, 2 and 5 are prime divisors of 20.
 * <p>
 * You are given two positive integers N and M. The goal is to check whether the sets of prime divisors of integers N and M are exactly the same.
 * <p>
 * For example, given:
 * <p>
 * N = 15 and M = 75, the prime divisors are the same: {3, 5};
 * N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5};
 * N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}.
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A, int[] B); }
 * <p>
 * that, given two non-empty zero-indexed arrays A and B of Z integers, returns the number of positions K for which the prime divisors of A[K] and B[K] are exactly the same.
 * <p>
 * For example, given:
 * <p>
 * A[0] = 15   B[0] = 75
 * A[1] = 10   B[1] = 30
 * A[2] = 3    B[2] = 5
 * the function should return 1, because only one pair (15, 75) has the same set of prime divisors.
 * <p>
 * Assume that:
 * <p>
 * Z is an integer within the range [1..6,000];
 * each element of arrays A, B is an integer within the range [1..2,147,483,647].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(Z*log(max(A)+max(B))2);
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */

public class CommonPrimeDivisors {
    public static void main(String[] args) {
        // put your codes here
        CommonPrimeDivisors test = new CommonPrimeDivisors();
//        int a = 2 * 3 * 5 * 7 * 11 * 13 * 13 * 13 * 11;
//        int b = 2 * 2 *2 * 5 * 3 * 9 * 7 * 11 * 11 * 13;
        int a = (int) Math.pow(2, 3) * 5;
        int b = 2 * (int) Math.pow(5, 3);
//        int a = 1317358350;
//        int b = 175647780;
        System.out.print(test.isSamePrimeDivider2(a, b));
    }


    public int solution(int[] A, int[] B) {
        // write your code in Java SE 8
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result += isSamePrimeDivider2(A[i], B[i]);
        }
        return result;
    }

//    public void deFactor(int n){
//        Set<Integer> factor = new HashSet<>();
//        ArrayList<Integer> prime = new ArrayList<>();
//        prime.add(2);
//        while (n % 2 == 0) {
//            factor.add(2);
//            n /= 2;
//        }
//        for (int i = 3; i <= n; i++) {
//            int flag = 1;
//            for (int j: prime){
//                if (i % j == 0){
//                    flag = 0;
//                }
//            }
//            if (flag == 1) {
//                while (n % i == 0) {
//                    factor.add(i);
//                    n /= i;
//                }
//                prime.add(i);
//            }
//        }
//        for (int i: factor) {
//            System.out.print(i + ",");
//        }
//    }

    public int findGcd(int a, int b) {
        int n = a;
        int m = b;
        int res = 1;
        while (n != m) {
            if (Integer.min(n, m) == 1) {
                n = 1;
                break;
            }
            if (n % 2 == 0 && m % 2 == 0) {
                res *= 2;
                n /= 2;
                m /= 2;
            } else if (n % 2 == 0) {
                n /= 2;
            } else if (m % 2 == 0) {
                m /= 2;
            } else if (n > m) {
                n -= m;
            } else {
                m -= n;
            }
        }
//        deFactor(n * res);
        return n * res;
    }

    public int findGcd2(int a, int b) {
        int m = a;
        int n = b;
        while (m % n != 0) {
            int temp = n;
            n = m % n;
            m = temp;
        }
        return n;
    }

    public int isSamePrimeDivider2(int a, int b) {
        int gcd = findGcd(a, b);
        int tempGcd = gcd;
        while (a != 1) {
            a /= tempGcd;
            tempGcd = findGcd(a, gcd);
            if (tempGcd == 1) break;
        }

        if (a != 1) return 0;

        tempGcd = gcd;
        while (b != 1) {
            b /= tempGcd;
            tempGcd = findGcd(b, gcd);
            if (tempGcd == 1) break;
        }

        if (b != 1) return 0;

        return 1;
    }

    public int isSamePrimeDivider(int a, int b) {
        if (a == b) return 1;
        int lastGcd;
        int currentGcd = findGcd(a, b);
        lastGcd = currentGcd;
        while (currentGcd != 1) {
            lastGcd = currentGcd;
            a /= lastGcd;
            b /= lastGcd;
            currentGcd = findGcd(a, b);
        }
        if (lastGcd % a == 0 && lastGcd % b == 0 || a == b) {
            return 1;
        } else if (lastGcd == -1) {
            return 0;
        }

        if (lastGcd % a != 0) {
            currentGcd = findGcd(a, lastGcd);
            while (currentGcd != 1) {
                a /= currentGcd;
                currentGcd = findGcd(a, lastGcd);
            }
        }

        if (lastGcd % b != 0) {
            currentGcd = findGcd(b, lastGcd);
            while (currentGcd != 1) {
                b /= currentGcd;
                currentGcd = findGcd(b, lastGcd);
            }
        }
        if (a == b) return 1;
        else return 0;
    }
}