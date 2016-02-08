package LeetCode;

/**
 * Created by warn on 8/2/2016.
 */
public class LeetCodeEasyPuzzle {

    public static void main(String[] args) {
        LeetCodeEasyPuzzle test = new LeetCodeEasyPuzzle();
        test.myAtoi("-2147483649");
    }

    /**
     * convert a string to an integer
     * The function first discards as many whitespace characters as necessary until the first non-whitespace character
     * is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many
     * numerical digits as possible, and interprets them as a numerical value.
     * <p>
     * The string can contain additional characters after those that form the integral number, which are ignored and
     * have no effect on the behavior of this function.
     * <p>
     * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence
     * exists because either str is empty or it contains only whitespace characters, no conversion is performed.
     * <p>
     * If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of
     * representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
     *
     * @param str
     * @return the integer of the str.
     */
    public int myAtoi(String str) {
        char[] stringList = str.toCharArray();
        int result = 0;
        int multiplier = -2;
        for (char i : stringList) {
            if (multiplier == -2) {
                if (i == ' ') continue;
                else if (i == '+') multiplier = 1;
                else if (i == '-') multiplier = -1;
                else if (Character.isDigit(i)) {
                    result = i - '0';
                    multiplier = 1;
                } else break;
            } else if (Character.isDigit(i)) {
                int temp = i - '0';
                int maxValue = (multiplier == 1) ? (Integer.MAX_VALUE % 10) : -(Integer.MIN_VALUE % 10);
                if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && temp >= maxValue)) {
                    result = multiplier == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    break;
                }
                result = result * 10 + temp;
            } else break;
        }
        return result * multiplier;
    }
}
