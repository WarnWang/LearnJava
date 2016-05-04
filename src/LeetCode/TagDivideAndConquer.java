package LeetCode;

import java.util.*;

/**
 * Created by warn on 16/4/2016.
 * <p>
 * Use to store puzzles with tag Divide and Conquer
 */
public class TagDivideAndConquer {
    /**
     * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
     * (not unary) +, -, or * between the digits so they evaluate to the target value.
     * -1 means +, -2 means -, -3 means bfs version
     *
     * @param num    a string that contains only digits 0-9
     * @param target target value
     * @return all possibilities to add binary operators between the digits so they evaluate to the target value
     */
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) return null;
        List<String> possibleEquations = new ArrayList<>();
        HashSet<String> exploredEquations = new HashSet<>();
        ArrayDeque<ArrayList<Integer>> exploreQueue = new ArrayDeque<>();
        char[] numArray = num.toCharArray();
        int numLength = numArray.length;
        exploreQueue.add(new ArrayList<>(Collections.singletonList(0)));
        exploreQueue.add(new ArrayList<>(Arrays.asList(new Integer[]{0, -1})));
        exploreQueue.add(new ArrayList<>(Arrays.asList(new Integer[]{0, -2})));
        exploreQueue.add(new ArrayList<>(Arrays.asList(new Integer[]{0, -3})));
        while (!exploreQueue.isEmpty()) {
            ArrayList<Integer> frontier = exploreQueue.removeLast();
            int lastCharIndex = frontier.get(frontier.size() - 1);
            if (lastCharIndex < 0) lastCharIndex = frontier.get(frontier.size() - 2);
            if (lastCharIndex == numLength - 1) {
                StringBuilder possibleEquation = new StringBuilder();
                for (int i : frontier) {
                    switch (i) {
                        case -1:
                            possibleEquation.append('+');
                            break;
                        case -2:
                            possibleEquation.append('-');
                            break;
                        case -3:
                            possibleEquation.append('*');
                            break;
                        default:
                            possibleEquation.append(numArray[i]);
                    }
                }
                String equation = possibleEquation.toString();
                if (exploredEquations.contains(equation)) continue;
                exploredEquations.add(equation);
                try {
                    if (calculateEquation(equation) == target)
                        possibleEquations.add(equation);
                } catch (Exception ignored) {
                }
            } else {
                int[] nextOperator = (lastCharIndex + 2 == numLength) ? new int[]{lastCharIndex + 1} :
                        new int[]{lastCharIndex + 1, -1, -2, -3};
                for (int i : nextOperator) {
                    ArrayList<Integer> nextFrontier = new ArrayList<>(frontier);
                    if (i != lastCharIndex + 1) nextFrontier.add(lastCharIndex + 1);
                    nextFrontier.add(i);
                    exploreQueue.add(nextFrontier);
                }
            }
        }
        return possibleEquations;
    }

    private int calculateEquation(String equation) throws Exception {
        ArrayDeque<Integer> parameters = new ArrayDeque<>();
        ArrayDeque<Character> operators = new ArrayDeque<>();
        boolean isMultiple = false;
        int tempInteger;
        StringBuilder tempInt = new StringBuilder();
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
            if (Character.isDigit(c)) {
                tempInt.append(c);
            } else {
                if (tempInt.length() > 1 && tempInt.toString().charAt(0) == '0')
                    throw new Exception("Wrong Value type");
                if (isMultiple) {
                    int lastInteger = parameters.pop();
                    tempInteger = lastInteger * Integer.parseInt(tempInt.toString());
                    isMultiple = false;
                } else tempInteger = Integer.parseInt(tempInt.toString());
                switch (c) {
                    case '+':
                    case '-':
                        operators.addLast(c);
                        break;
                    case '*':
                        isMultiple = true;
                }
                parameters.addLast(tempInteger);
                tempInt = new StringBuilder();
            }
        }
        if (tempInt.length() > 1 && tempInt.toString().charAt(0) == '0')
            throw new Exception("Wrong Value type");
        if (isMultiple) {
            int lastInteger = parameters.removeLast();
            tempInteger = lastInteger * Integer.parseInt(tempInt.toString());
        } else tempInteger = Integer.parseInt(tempInt.toString());
        parameters.addLast(tempInteger);
        while (!operators.isEmpty()) {
            int first = parameters.remove();
            int second = parameters.remove();
            char operator = operators.remove();
            if (operator == '+') first += second;
            else first -= second;
            parameters.addFirst(first);
        }
        return parameters.remove();
    }

    public List<String> addOperatorsFromDiscuss(String num, int target) {
        List<String> possibleEquations = new ArrayList<>();
        if (num == null || num.length() == 0) return possibleEquations;
        char[] digits = num.toCharArray();
        char[] path = new char[num.length() * 2 - 1];
        long n = 0;
        for (int i = 0; i < digits.length; i++) {
            path[i] = digits[i];
            n = 10 * n + digits[i] - '0';
            addOperatorsDFS(digits, path, i + 1, i + 1, 0, n, target, possibleEquations);
            if (n == 0) break;
        }
        return possibleEquations;
    }

    private void addOperatorsDFS(char[] digits, char[] path, int pos, int len, long left, long middle, int target,
                                 List<String> possibleEquations) {
        if (pos == digits.length) {
            if (left + middle == target) possibleEquations.add(new String(path, 0, len));
        }
        int j = len + 1;
        long n = 0;
        for (int i = pos; i < digits.length; i++) {
            n = 10 * n + digits[i] - '0';
            path[j++] = digits[i];
            path[len] = '+';
            addOperatorsDFS(digits, path, i + 1, j, left + middle, n, target, possibleEquations);
            path[len] = '-';
            addOperatorsDFS(digits, path, i + 1, j, left + middle, -n, target, possibleEquations);
            path[len] = '*';
            addOperatorsDFS(digits, path, i + 1, j, left, middle * n, target, possibleEquations);
            if (digits[pos] == '0') break;
        }
    }

    /**
     * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
     * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] *
     * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then
     * becomes adjacent.
     * <p>
     * Find the maximum coins you can collect by bursting the balloons wisely.
     * <p>
     * Note:
     * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
     * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
     * <p>
     * Example:
     * <p>
     * Given [3, 1, 5, 8]
     * <p>
     * Return 167
     * <p>
     * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
     *
     * @param nums a coin of balloons
     * @return maximum coins you can collect
     */
    public int maxCoins(int[] nums) {
        boolean[] burstedIndex = new boolean[nums.length];
        int maxCoin = 0;
        for (int i = 0; i < nums.length; i++) {
            burstedIndex[i] = true;
            maxCoin = Integer.max(maxCoin, getCoins(nums, burstedIndex, i) + maxCoins(nums, burstedIndex));
            burstedIndex[i] = false;
        }
        return maxCoin;
    }

    private int maxCoins(int[] nums, boolean[] burstedIndex){
        int maxCoin = 0;
        for (int i = 0; i < nums.length; i++) {
            if (burstedIndex[i]) continue;
            burstedIndex[i] = true;
            maxCoin = Integer.max(maxCoin, getCoins(nums, burstedIndex, i) + maxCoins(nums, burstedIndex));
            burstedIndex[i] = false;
        }
        return maxCoin;
    }

    private int getCoins(int[] nums, boolean[] burstedIndex, int index){
        int i, j;
        for (i = index + 1; i < nums.length; i++) {
            if (!burstedIndex[i]) break;
        }
        for (j = index - 1; j >= 0; j--) {
            if (!burstedIndex[j]) break;
        }
        int a = (j >= 0) ? nums[j] : 1;
        int b = (i < nums.length) ? nums[i]: 1;
        return nums[index] * a * b;
    }
}
