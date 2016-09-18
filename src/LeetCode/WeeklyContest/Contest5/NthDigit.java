package LeetCode.WeeklyContest.Contest5;

/**
 * Created by warn on 18/9/2016.
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * <p>
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 3
 * <p>
 * Output:
 * 3
 * Example 2:
 * <p>
 * Input:
 * 11
 * <p>
 * Output:
 * 0
 * <p>
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */
public class NthDigit {
    public int findNthDigit(int n) {
        int level = 9;
        int nNum = 1;
        int currentDigitNum = level * nNum;
        int targetDigit = n;
        while (targetDigit > currentDigitNum) {
            targetDigit -= currentDigitNum;
            level *= 10;
            nNum++;
            currentDigitNum = level * nNum;
            if (nNum == 10) break;
        }
        int reminder = (targetDigit - 1) % nNum;
        System.out.println(reminder);
        int base = (int) Math.pow(10, nNum - 1) + (targetDigit - 1) / nNum;
        return Integer.toString(base).charAt(reminder) - '0';
    }
}
