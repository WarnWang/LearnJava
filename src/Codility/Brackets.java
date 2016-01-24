package Codility;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by warn on 24/1/2016.
 */

//A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
//
//        S is empty;
//        S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
//        S has the form "VW" where V and W are properly nested strings.
//        For example, the string "{[()()]}" is properly nested but "([)()]" is not.
//
//        Write a function:
//
//class Solution { public int solution(String S); }
//
//that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
//
//        For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.
//
//        Assume that:
//
//        N is an integer within the range [0..200,000];
//        string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
//        Complexity:
//
//        expected worst-case time complexity is O(N);
//        expected worst-case space complexity is O(N) (not counting the storage required for input arguments).

public class Brackets {
    public static void main(String[] args) {
        // put your codes here
        Brackets test = new Brackets();
        System.out.println(test.solution(")("));
        System.out.println(test.solution("()[()()]"));
        System.out.println(test.solution("(){[()()]}"));
        System.out.println(test.solution("(){[()()]}{{"));
    }

    public int solution(String S) {
        // write your code in Java SE 8
        int n = S.length();
        if (n % 2 != 0) return 0;
        int result = 1;
        Stack<Character> container = new Stack<>();
        for (int i = 0; i < n; i++) {
            char temp = S.charAt(i);
            if (temp == '{' || temp == '[' || temp == '(') {
                container.push(temp);
            } else {
                try {
                    char counter = container.pop();
                    if (temp - counter != 2 && temp - counter != 1) {
                        result = 0;
                        break;
                    }
                } catch (EmptyStackException e) {
                    result = 0;
                    break;
                }
            }
        }
        if (container.size() != 0) result = 0;
        return result;
    }
}