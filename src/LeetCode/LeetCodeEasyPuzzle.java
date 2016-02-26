package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by warn on 8/2/2016.
 */
public class LeetCodeEasyPuzzle {

    public static void main(String[] args) {
        LeetCodeEasyPuzzle test = new LeetCodeEasyPuzzle();
//        test.myAtoi("-2147483649");
//        test.rotate1(new int[]{1, 2, 3}, 1);
//        test.rotate2(new int[]{1, 2, 3}, 2);
//        test.addBinary("11", "1");
        System.out.println(test.isPowerOfThree(243));
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

    /**
     * Rotate an array of n elements to the right by k steps.
     * <p>
     * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
     * <p>
     * Note:
     * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
     *
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        int[] temp = nums.clone();
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = temp[i];
        }
        System.out.print(Arrays.toString(nums));
    }

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        if (k == 0) return;
        int kTimes = findGcd(n, k);
        for (int i = 0; i < kTimes; i++) {
            int j = i;
            int newJ = (i + k) % n;
            int temp = nums[newJ];
            nums[newJ] = nums[j];
            j = newJ;
            newJ = (j + k) % n;
            do {
                int newTemp = nums[newJ];
                nums[newJ] = temp;
                temp = newTemp;
                j = newJ;
                newJ = (j + k) % n;
            } while (i != newJ);
            nums[i] = temp;
        }
        System.out.print(Arrays.toString(nums));
    }

    private int findGcd(int m, int n) {
        int mux = 1;
        while (m != n) {
            if (m % 2 == 0 && n % 2 == 0) {
                mux *= 2;
                m /= 2;
                n /= 2;
            } else if (n % 2 == 0) n /= 2;
            else if (m % 2 == 0) m /= 2;
            else {
                int temp = m;
                m = Math.abs(n - m);
                n = temp;
            }
        }
        return n * mux;
    }


    public String addBinary(String a, String b) {
        int nA = a.length();
        int nB = b.length();
        StringBuffer result = new StringBuffer();
        int ai, bi;
        int element = 0;
        while (nA > 0 || nB > 0) {
            nA--;
            nB--;
            if (nA >= 0) ai = a.charAt(nA) - '0';
            else ai = 0;
            if (nB >= 0) bi = b.charAt(nB) - '0';
            else bi = 0;
            result.append((ai + bi + element) % 2);
            element = (ai + bi + element) / 2;
        }
        if (element == 1) result.append(element);
        return result.reverse().toString();
    }

    public int romanToInt(String s) {
        int result = 0;
        int n = s.length();
        if (n == 0) return result;

        int lastNumber = 0;
        for (int j = 0; j < n; j++) {
            char i = s.charAt(j);
            int currentNumber;
            switch (i) {
                case 'M':
                    currentNumber = 1000;
                    break;
                case 'I':
                    currentNumber = 1;
                    break;
                case 'V':
                    currentNumber = 5;
                    break;
                case 'X':
                    currentNumber = 10;
                    break;
                case 'L':
                    currentNumber = 50;
                    break;
                case 'C':
                    currentNumber = 100;
                    break;
                case 'D':
                    currentNumber = 500;
                    break;
                default:
                    currentNumber = 0;
            }
            if (currentNumber <= lastNumber) result += lastNumber;
            else result -= lastNumber;
            lastNumber = currentNumber;
        }
        result += lastNumber;
        return result;
    }

    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * <p>
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     *
     * @param s given string s
     * @return whether this string is valid or not.
     */
    public boolean isValid(String s) {
        Stack<Character> parentheses = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            switch (temp) {
                case '{':
                case '[':
                case '(':
                    parentheses.push(temp);
                    break;
                default:
                    if (parentheses.size() == 0) return false;
                    char temp2 = parentheses.pop();
                    if (temp2 - temp >= 2) return false;
            }
        }
        return parentheses.size() == 0;
    }

    public boolean isPowerOfThree(int n) {
        if (n <= 0 || (n % 3 != 0 && n != 1)) return false;
        boolean result = false;
        int m = (int) Math.round(Math.log(n) / Math.log(3));
        if (n <= Math.pow(3, 19) && n == (int) Math.pow(3, m)) result = true;
        return result;
    }

    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] secretArray = new int[10];
        int[] guessArray = new int[10];

        for (int i = 0, n = secret.length(); i < n; i++) {
            char secretDigit = secret.charAt(i);
            char guessDigit = guess.charAt(i);
            if (guessDigit == secretDigit) bulls++;
            else {
                secretArray[secretDigit - '0']++;
                guessArray[guessDigit - '0']++;
            }
        }

        for (int i = 0; i < 10; i++)
            if (guessArray[i] != 0 && secretArray[i] != 0) cows += Integer.min(guessArray[i], secretArray[i]);
        return bulls + "A" + cows + "B";
    }

    public boolean isSelfCrossing(int[] x) {
        for (int i = 3; i < x.length; i++) {
            if (x[i - 1] <= x[i - 3] && x[i] >= x[i - 2]) return true;
            if (i > 3 && x[i - 1] == x[i - 3] && (x[i - 2] - x[i] <= x[i - 4])) return true;
            if (i > 4 && (x[i] + x[i - 4] >= x[i - 2]) && (x[i - 2] >= x[i - 4]) && (x[i - 3] <= x[i - 5] + x[i - 1])
                    && (x[i - 1] <= x[i - 3])) return true;
        }
        return false;
    /*               i-2
    case 1 : i-1┌─┐
                └─┼─>i
                 i-3

                    i-2
    case 2 : i-1 ┌────┐
                 └─══>┘i-3
                 i  i-4      (i overlapped i-4)

    case 3 :    i-4
               ┌──┐
               │i<┼─┐
            i-3│ i-5│i-1
               └────┘
                i-2

    */
    }

    public boolean isHappy(int n) {
        if (n == 0) return false;
        if (n == 1) return true;
        Set<Integer> exploredDigit = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            if (exploredDigit.contains(n) || n == 4) return false;
            exploredDigit.add(n);
            while (n > 0) {
                int temp = n % 10;
                sum += temp * temp;
                n /= 10;
            }
            n = sum;
        }
        return true;
    }
}
