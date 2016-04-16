package LeetCode;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

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
                try {
                    if (calculateEquation(possibleEquation.toString()) == target)
                        possibleEquations.add(possibleEquation.toString());
                } catch (Exception ignored){}
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

    private int calculateEquation(String equation) throws Exception {
        ArrayDeque<Integer> parameters = new ArrayDeque<>();
        ArrayDeque<Character> operators = new ArrayDeque<>();
        boolean isMultiple = false;
        int tempInteger;
        StringBuilder tempInt = new StringBuilder();
        for (int i = 0; i < equation.length(); i++){
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
                switch (c){
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
        while (!operators.isEmpty()){
            int first = parameters.remove();
            int second = parameters.remove();
            char operator = operators.remove();
            if (operator == '+') first += second;
            else first -= second;
            parameters.addFirst(first);
        }
        return parameters.remove();
    }
}
