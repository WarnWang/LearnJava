package Codility;

/**
 * Created by warn on 29/1/2016.
 * A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.
 * <p>
 * For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int N); }
 * <p>
 * that, given a positive integer N, returns the number of its factors.
 * <p>
 * For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8, 12, 24. There are no other factors of 24.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..2,147,483,647].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(sqrt(N));
 * expected worst-case space complexity is O(1).
 */
public class CountFactors {
    public static void main(String[] args) {
        // put your codes here
        CountFactors test = new CountFactors();
        System.out.println(test.solution(15));
        System.out.println(test.solution(Integer.MAX_VALUE));
        System.out.println(test.solution(25));
    }

    public int solution(int N) {
        // write your code in Java SE 8
        int i = 1;
        int result = 0;
        int sqrtN = (int) Math.sqrt(N);
        while (i <= sqrtN) {
            try {
                if (N % i == 0) result += 2;
            } catch (ArithmeticException e) {
                System.out.println(i);
                return 0;
            }
            i++;
        }
        if (sqrtN * sqrtN == N) {
            result--;
        }
        return result;
    }
}