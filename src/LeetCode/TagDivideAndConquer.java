package LeetCode;

import java.util.*;

/**
 * Created by warn on 16/4/2016.
 *
 * Use to store puzzles with tag Divide and Conquer
 */
public class TagDivideAndConquer {
    /**
     * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
     * (not unary) +, -, or * between the digits so they evaluate to the target value.
     * -1 means +, -2 means -, -3 means bfs version
     *
     * @param num a string that contains only digits 0-9
     * @param target target value
     * @return all possibilities to add binary operators between the digits so they evaluate to the target value
     */
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) return null;
        List<String> possibleEquations = new ArrayList<>();
        ArrayDeque<ArrayList<Integer>> exploreQueue = new ArrayDeque<>();
        char[] numArray = num.toCharArray();
        int numLength = numArray.length;
        exploreQueue.add(new ArrayList<>(Collections.singletonList(0)));
        exploreQueue.add(new ArrayList<>(Arrays.asList(new Integer[] {0, -1})));
        exploreQueue.add(new ArrayList<>(Arrays.asList(new Integer[] {0, -2})));
        exploreQueue.add(new ArrayList<>(Arrays.asList(new Integer[] {0, -3})));
        while (!exploreQueue.isEmpty()) {
            ArrayList<Integer> frontier = exploreQueue.removeLast();
            int lastCharIndex = frontier.get(frontier.size() - 1);
            if (lastCharIndex < 0) lastCharIndex = frontier.get(frontier.size() - 2);
            if (lastCharIndex == numLength - 1){
                StringBuilder possibleEquation = new StringBuilder();
                for (int i: frontier) {
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
                if (calculateEquation(possibleEquation.toString()) == target)
                    possibleEquations.add(possibleEquation.toString());
            } else {
                int[] nextOperator = (lastCharIndex + 2 == numLength) ? new int[] {lastCharIndex + 1} :
                        new int[] {lastCharIndex + 1, -1, -2, -3};
                for (int i: nextOperator){
                    ArrayList<Integer> nextFrontier = new ArrayList<>(frontier);
                    if (i != lastCharIndex + 1) nextFrontier.add(lastCharIndex + 1);
                    nextFrontier.add(i);
                    exploreQueue.add(nextFrontier);
                }
            }
        }
        return possibleEquations;
    }

    private int calculateEquation(String equation){
        Stack<Integer> parameters = new Stack<>();
        Stack<Character> operators = new Stack<>();
        boolean isMultiple = false;
        int tempInteger = 0;
        for (int i = 0; i < equation.length(); i++){
            char c = equation.charAt(i);
            if (Character.isDigit(c)) {
                tempInteger = 10 * tempInteger + c - '0';
            } else {
                if (isMultiple) {
                    int lastInteger = parameters.pop();
                    tempInteger *= lastInteger;
                    isMultiple = false;
                }
                switch (c){
                    case '+':
                    case '-':
                        operators.push(c);
                        parameters.push(tempInteger);
                        tempInteger = 0;
                        break;
                    case '*':
                        isMultiple = true;
                        parameters.push(tempInteger);
                }
            }
            parameters.push(tempInteger);
        }
        System.out.println(parameters.toString());
        System.out.println(operators.toString());
        if (isMultiple) {
            int lastInteger = parameters.pop();
            tempInteger *= lastInteger;
        }
        parameters.push(tempInteger);
        while (!operators.isEmpty()){
            int second = parameters.pop();
            int first = parameters.pop();
            char operator = operators.pop();
            if (operator == '+') first += second;
            else first -= second;
            parameters.push(first);
        }
        int result = parameters.pop();
        System.out.println(equation + '=' + result);
        return result;
    }
}
