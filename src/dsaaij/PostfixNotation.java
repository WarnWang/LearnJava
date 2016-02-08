package dsaaij;

import java.util.Stack;

/**
 * Created by warn on 7/2/2016.
 */
public class PostfixNotation {
    public static double postfixNotation(String postEquation) {
        Stack<Double> numbers = new Stack<>();
        int n = postEquation.length();
        for (int i = 0; i < n; i++) {
            char temp = postEquation.charAt(i);
            if (Character.isDigit(temp)) {
                numbers.push((double) (temp - '0'));
            } else {
                double number2 = numbers.pop();
                double number1 = numbers.pop();
                switch (temp) {
                    case '+':
                        numbers.push(number2 + number1);
                        break;
                    case '-':
                        numbers.push(number1 - number2);
                        break;
                    case '*':
                        numbers.push(number1 * number2);
                        break;
                    default:
                        numbers.push(number1 / number2);
                }
            }
        }
        return numbers.pop();
    }

    public static void main(String[] args) {
        // put your codes here
        System.out.print(postfixNotation("52+83-*4/"));
    }
}