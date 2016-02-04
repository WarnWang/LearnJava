package Codility;

import java.util.ArrayList;

/**
 * Created by warn on 4/2/2016.
 * The Fibonacci sequence is defined using the following recursive formula:
 * <p>
 * F(0) = 0
 * F(1) = 1
 * F(M) = F(M - 1) + F(M - 2) if M >= 2
 * A small frog wants to get to the other side of a river. The frog is initially located at one bank of the river (position −1) and wants to get to the other bank (position N). The frog can jump over any distance F(K), where F(K) is the K-th Fibonacci number. Luckily, there are many leaves on the river, and the frog can jump between the leaves, but only in the direction of the bank at position N.
 * <p>
 * The leaves on the river are represented in a zero-indexed array A consisting of N integers. Consecutive elements of array A represent consecutive positions from 0 to N − 1 on the river. Array A contains only 0s and/or 1s:
 * <p>
 * 0 represents a position without a leaf;
 * 1 represents a position containing a leaf.
 * The goal is to count the minimum number of jumps in which the frog can get to the other side of the river (from position −1 to position N). The frog can jump between positions −1 and N (the banks of the river) and every position containing a leaf.
 * <p>
 * For example, consider array A such that:
 * <p>
 * A[0] = 0
 * A[1] = 0
 * A[2] = 0
 * A[3] = 1
 * A[4] = 1
 * A[5] = 0
 * A[6] = 1
 * A[7] = 0
 * A[8] = 0
 * A[9] = 0
 * A[10] = 0
 * The frog can make three jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given a zero-indexed array A consisting of N integers, returns the minimum number of jumps by which the frog can get to the other side of the river. If the frog cannot reach the other side of the river, the function should return −1.
 * <p>
 * For example, given:
 * <p>
 * A[0] = 0
 * A[1] = 0
 * A[2] = 0
 * A[3] = 1
 * A[4] = 1
 * A[5] = 0
 * A[6] = 1
 * A[7] = 0
 * A[8] = 0
 * A[9] = 0
 * A[10] = 0
 * the function should return 3, as explained above.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer that can have one of the following values: 0, 1.
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class FibFrog {
    public static void main(String[] args) {
        // put your codes here
        FibFrog test = new FibFrog();
        System.out.println(test.solution(new int[]{0, 1, 0, 1, 0}));
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int n = A.length;
        if (n <= 2) return 1;
        int count = 0;
        ArrayList<Integer> frogJump = getFibonacci(n);
        ArrayList<Integer> possibleNextMove = new ArrayList<>();
        for (int i : frogJump) {
            if (i - 1 == n) return ++count;
            else if (A[i - 1] == 1) {
                possibleNextMove.add(i - 1);
                A[i - 1]++;
            }
        }
        while (possibleNextMove.size() > 0) {
            count++;
            ArrayList<Integer> nextLevel = new ArrayList<>();
            for (int i : possibleNextMove) {
                if (i == n) return count;
                for (int j : frogJump) {
                    int pos = i + j;
                    if (pos == n) return ++count;
                    else if (pos < n && A[pos] == 1) nextLevel.add(pos);
                }
            }
            possibleNextMove = new ArrayList<>(nextLevel);
        }
        return -1;
    }

    public ArrayList<Integer> getFibonacci(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        int f1 = 1;
        int f2 = 1;
        while (f2 <= n + 1) {
            result.add(f2);
            int temp = f1;
            f1 = f2;
            f2 += temp;
        }
        return result;
    }
}