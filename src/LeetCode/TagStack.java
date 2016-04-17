package LeetCode;

/**
 * Created by warn on 17/4/2016.
 * Use to store those with tag stack
 */
public class TagStack {
    /**
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * <p>
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
     * <p>
     * Some examples:
     * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     *
     * @param tokens a list of arithmetic expression
     * @return the value of the expression
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null) return 0;
        int tokensLength = tokens.length;
        if (tokensLength == 0) return 0;
        int[] numberStack = new int[4];
        int numberIndex = -1;
        for (String token: tokens) {
            int a;
            switch (token) {
                case "+":
                    a = numberStack[numberIndex--];
                    numberStack[numberIndex] += a;
                    break;
                case "-":
                    a = numberStack[numberIndex--];
                    numberStack[numberIndex] -= a;
                    break;
                case "*":
                    a = numberStack[numberIndex--];
                    numberStack[numberIndex] *= a;
                    break;
                case "/":
                    a = numberStack[numberIndex--];
                    numberStack[numberIndex] /= a;
                    break;
                default:
                    numberStack[++numberIndex] = Integer.parseInt(token);
            }
        }
        return numberStack[0];
    }
}
