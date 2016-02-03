package Codility;

/**
 * Created by warn on 3/2/2016.
 * Two positive integers N and M are given. Integer N represents the number of chocolates arranged in a circle, numbered from 0 to N − 1.
 * <p>
 * You start to eat the chocolates. After eating a chocolate you leave only a wrapper.
 * <p>
 * You begin with eating chocolate number 0. Then you omit the next M − 1 chocolates or wrappers on the circle, and eat the following one.
 * <p>
 * More precisely, if you ate chocolate number X, then you will next eat the chocolate with number (X + M) modulo N (remainder of division).
 * <p>
 * You stop eating when you encounter an empty wrapper.
 * <p>
 * For example, given integers N = 10 and M = 4. You will eat the following chocolates: 0, 4, 8, 2, 6.
 * <p>
 * The goal is to count the number of chocolates that you will eat, following the above rules.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int N, int M); }
 * <p>
 * that, given two positive integers N and M, returns the number of chocolates that you will eat.
 * <p>
 * For example, given integers N = 10 and M = 4. the function should return 5, as explained above.
 * <p>
 * Assume that:
 * <p>
 * N and M are integers within the range [1..1,000,000,000].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(log(N+M));
 * expected worst-case space complexity is O(log(N+M)).
 */

public class ChocolatesByNumbers {
    public static void main(String[] args) {
        // put your codes here
        ChocolatesByNumbers test = new ChocolatesByNumbers();
        test.solution(1000000, 1);
        test.gcdSolution(1000000, 1);
    }

    public int solution(int N, int M) {
        // write your code in Java SE 8
        final long startTime = System.currentTimeMillis();
        int[] container = new int[N];
        int i = 0;
        int counter = 0;
        while (container[i] == 0) {
            counter++;
            container[i] = -1;
            i = (i + M) % N;
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));

        return counter;

    }

    public int gcdSolution(int N, int M) {

        final long startTime = System.currentTimeMillis();
        int a = N;
        int b = M;
        int res = 1;
        while (a != b) {
            if (a % 2 == 0 && b % 2 == 0) {
                res *= 2;
                a /= 2;
                b /= 2;
            } else if (a % 2 == 0) {
                a /= 2;
            } else if (b % 2 == 0) {
                b /= 2;
            } else if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime));

        return N / (res * a);
    }
}