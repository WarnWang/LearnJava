package LeetCode;

/**
 * Created by warn on 8/2/2016.
 * <p>
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * <p>
 * For example:
 * <p>
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 * <p>
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 */
public class AddDigits {
    public int addDigits(int num) {
        if (num > 0) {
            if (num % 9 != 0) return num % 9;
            else return 9;
        }
        return 0;
    }
}
