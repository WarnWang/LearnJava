package LeetCode.DesignPuzzles;

import java.util.Stack;

/**
 * Created by warn on 6/7/2016.
 * Implement the following operations of a s1 using stacks.
 * <p>
 * push(x) -- Push element x to the back of s1.
 * pop() -- Removes the element from in front of s1.
 * peek() -- Get the front element.
 * empty() -- Return whether the s1 is empty.
 * Notes:
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is
 * empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque
 * (double-ended s1), as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty
 * s1).
 */
public class MyQueue {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    int front;

    // Push element x to the back of s1.
    public void push(int x) {
        if (s1.isEmpty())
            front = x;
        s1.push(x);
    }

    // Removes the element from in front of s1.
    public void pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) s2.push(s1.pop());
        }
        s2.pop();
    }

    // Get the front element.
    public int peek() {
        if (s2.isEmpty()) return front;
        else return s2.peek();
    }

    // Return whether the s1 is empty.
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
