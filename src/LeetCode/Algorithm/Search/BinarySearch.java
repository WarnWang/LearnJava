package LeetCode.Algorithm.Search;

/**
 * Created by warn on 16/7/2016.
 * Store solutions to question with tag binary search
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch test = new BinarySearch();
    }

    int guess(int num) {
        return 0;
    }

    public int guessNumber(int n) {
        int upBound = n, lowBound = 1;
        while (upBound >= lowBound) {
            if (guess(upBound) == 0) return upBound;
            else if (guess(lowBound) == 0) return lowBound;
            int middle = upBound / 2 + lowBound / 2;
            int guessMiddle = guess(middle);
            if (guessMiddle == 0) return middle;
            else if (guessMiddle == -1) {
                upBound = middle - 1;
                lowBound++;
            } else {
                upBound--;
                lowBound = middle + 1;
            }
        }
        return 0;
    }

    /**
     * Given a positive integer num, write a function which returns True if num is a perfect square else False.
     * <p>
     * Note: Do not use any built-in library function such as sqrt.
     * <p>
     * Example 1:
     * <p>
     * Input: 16
     * Returns: True
     * Example 2:
     * <p>
     * Input: 14
     * Returns: False
     *
     * @param num a positive integer num
     * @return whether this number is perfect square or not
     */
    public boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        if (num <= 1) return true;
        int start = 1;
        int end = Math.min(num, 46340);
        while (start < end) {
            if (start * start == num || end * end == num) return true;
            else if (start * start > num || end * end < num) return false;
            int middle = start / 2 + end / 2;
            if (middle * middle == num) return true;
            else if (middle * middle > num) end = middle;
            else start = middle;
        }
        return false;
    }
}
