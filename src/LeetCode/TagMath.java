package LeetCode;

import java.util.*;

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

    /**
     * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
     * which sum to n.
     * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
     *
     * @param n a positive integer n
     * @return the least number of perfect square numbers
     */
    public int numSquares(int n) {
        if (n <= 0) return -1;
        Queue<int[]> remainder = new ArrayDeque<>();
        remainder.add(new int[]{0, n});
        while (!remainder.isEmpty()) {
            int[] frontier = remainder.remove();
            int maxNext = (int) Math.sqrt(frontier[1]);
            for (int i = Integer.max(1, maxNext / 2); i <= maxNext; i++) {
                if (frontier[1] == i * i) return frontier[0] + 1;
                else remainder.add(new int[]{frontier[0] + 1, frontier[1] - i * i});
            }
        }
        return -1;
    }

    // https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem
    public int numSquares4Square(int n) {
        if (n <= 0)
            return 0;

        while (n % 4 == 0)
            n /= 4;

        //4condition
        if (n % 8 == 7)
            return 4;

        //1 or 2 condition
        for (int i = 0; i * i <= n; i++) {
            int j = (int) Math.sqrt(n - i * i);
            if (j * j + i * i == n) {
                return (i > 0 ? 1 : 0) + (j > 0 ? 1 : 0);
            }
        }

        //else 3
        return 3;
    }

    /**
     * mplement a basic calculator to evaluate a simple expression string.
     * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative
     * integers and empty spaces .
     * You may assume that the given expression is always valid.
     *
     * @param s the expression string
     * @return the result of give string.
     */
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] sChars = s.toCharArray();
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int sLen = s.length();
        for (int i = 0; i < sLen; i++) {
            if (Character.isDigit(sChars[i])) {
                int tempInt = sChars[i] - '0';
                for (int j = i + 1; j < sLen; j++) {
                    if (!Character.isDigit(sChars[j])) break;
                    tempInt = 10 * tempInt + sChars[j] - '0';
                    i = j;
                }
                while (!operators.isEmpty() && !numbers.isEmpty()) {
                    char peekOperators = operators.pop();
                    if (peekOperators == '(') {
                        operators.push(peekOperators);
                        break;
                    } else {
                        int anotherInteger = numbers.pop();
                        switch (peekOperators) {
                            case '+':
                                tempInt += anotherInteger;
                                break;
                            default:
                                tempInt -= anotherInteger;
                                tempInt *= -1;
                        }
                    }
                }
                numbers.push(tempInt);
            } else if (sChars[i] != ' ') {
                if (sChars[i] == ')') {
                    operators.pop();
                    if (operators.isEmpty()) continue;
                    int tempInt = numbers.pop();
                    char tempOperator = operators.pop();
                    if (tempOperator == '(') {
                        operators.push(tempOperator);
                    } else {
                        int anotherInteger = numbers.pop();
                        if (tempOperator == '-') {
                            tempInt -= anotherInteger;
                            tempInt *= -1;
                        } else tempInt += anotherInteger;
                    }
                    numbers.push(tempInt);
                } else operators.push(sChars[i]);
            }
        }
        return numbers.peek();
    }

    /**
     * Count the number of prime numbers less than a non-negative number, n.
     *
     * @param n a non-negative number
     * @return the number of prime numbers
     */
    public int countPrimes(int n) {
        if (n <= 2) return 0;
        boolean[] isPrime = new boolean[n - 1];
        int primeNum = 0;
        int sqrtN = (int) Math.sqrt(n);
        for (int i = 1; i < sqrtN; i++) {
            if (!isPrime[i]) {
                primeNum++;
                for (int j = 2 * i + 1; j < n - 1; j += (i + 1)) {
                    isPrime[j] = true;
                }
            }
        }
        for (int i = sqrtN + 1; i < n - 1; i++) {
            if (!isPrime[i]) primeNum++;
        }
        return primeNum;
    }

    public int countPrimesOdd(int n) {
        if (n < 3) return 0;
        int primeNum = n / 2;
        boolean[] isPrime = new boolean[n];
        int sqrtN = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrtN; i += 2) {
            if (!isPrime[i]) {
                for (int j = i * i; j < n; j += i * 2) {
                    primeNum -= (isPrime[j]) ? 0 : 1;
                    isPrime[j] = true;
                }
            }
        }
        return primeNum;
    }

    /**
     * Given a number, check whether it is a power of four
     *
     * @param num a number
     * @return whether this number is power of four or not
     */
    public boolean isPowerOfFour(int num) {
        if (num < 1) return false;
        int binSLen = Integer.toBinaryString(num).length() - 1;
        return (1 << binSLen == num) && (binSLen & 1) == 0;
    }

    public boolean isPowerOfTwo(int n) {
        if (n < 1) return false;

        // First version use binary string length
//        int binSLen = Integer.toBinaryString(n).length() - 1;
//        return 1 << binSLen == n;

        // Second version use math.log
        int m = (int) (Math.log(n) / Math.log(2));
        return 1 << m == n;

        // iteration version
//        while (n > 1) {
//            if ((n & 1) == 1) return false;
//            n >>= 1;
//        }
//        return true;
    }

    /**
     * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of
     * those integers. Return the maximum product you can get.
     * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
     * Note: you may assume that n is not less than 2.
     *
     * @param n: a positive integer
     * @return Max product
     */
    public int integerBreak(int n) {
        if (n < 2) return n;
        int maxValue = 0, maxParts = Integer.max(n / 2, 2);
        for (int nParts = 2; nParts <= maxParts; nParts++) {
            int padding = n % nParts, part = n / nParts;
            maxValue = Integer.max((int) (Math.pow(part + 1, padding) * Math.pow(part, nParts - padding)), maxValue);
        }
        return maxValue;
    }

    /**
     * Additive number is a string whose digits can form additive sequence.
     * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each
     * subsequent number in the sequence must be the sum of the preceding two.
     * For example:
     * "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
     * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
     * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
     * 1 + 99 = 100, 99 + 100 = 199
     * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
     * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
     *
     * @param num a string contains only '0' - '9'
     * @return whether this string is additive number or not
     */
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() == 0) return false;
        char[] numArray = num.toCharArray();
        LinkedList<Long> numList = new LinkedList<>();
        return isAdditiveNumber(numArray, numList, 0);
    }

    private boolean isAdditiveNumber(char[] num, LinkedList<Long> numList, int index) {
        if (index == num.length && numList.size() > 2) return true;
        int maxLength;
        if (numList.size() < 2) {
            maxLength = num.length / 2;
        } else maxLength = num.length - index;
        maxLength = Math.min(16, maxLength);
        maxLength = Math.min(num.length, index + maxLength);
        for (int i = index + 1; i <= maxLength; i++) {
            long newNum = Long.parseLong(new String(num, index, i - index));
            if (numList.size() > 1) {
                int n = numList.size() - 1;
                long a = numList.get(n), b = numList.get(n - 1);
                if (newNum == (a + b)) {
                    numList.add(newNum);
                    if (isAdditiveNumber(num, numList, i)) return true;
                    else numList.removeLast();
                } else if (newNum > (a + b)) return false;
            } else {
                numList.add(newNum);
                if (isAdditiveNumber(num, numList, i)) return true;
                else numList.removeLast();
            }
            if (newNum == 0) return false;
        }
        return false;
    }

    /**
     * Find the total area covered by two rectilinear rectangles in a 2D plane.
     * <p>
     * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
     *
     * @param A, B: down left point of first Rectangle
     * @param C, D: up right point of first Rectangle
     * @param E, F: down left point of first Rectangle
     * @param G, H: up right point of first Rectangle
     * @return the intersection area
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A > C) {
            int temp = C;
            C = A;
            A = temp;
        }
        if (B > D) {
            int temp = B;
            B = D;
            D = temp;
        }
        if (E > G) {
            int temp = E;
            E = G;
            G = temp;
        }
        if (F > H) {
            int temp = F;
            F = H;
            H = temp;
        }
        int rec1 = (C - A) * (D - B), rec2 = (G - E) * (H - F);
        if (Integer.min(G, C) <= Integer.max(A, E) || Integer.min(D, H) <= Integer.max(B, F)) return rec1 + rec2;
        return rec1 + rec2 - (Integer.min(G, C) - Integer.max(A, E)) * (Integer.min(D, H) - Integer.max(B, F));
    }
}
