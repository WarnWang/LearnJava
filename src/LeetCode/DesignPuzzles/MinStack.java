package LeetCode.DesignPuzzles;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by warn on 25/9/2016.
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */
public class MinStack {

    private LinkedList<Integer> minList;
    private Stack<Integer> numStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        minList = new LinkedList<>();
        numStack = new Stack<>();
    }

    public void push(int x) {
        numStack.push(x);
        if (minList.isEmpty()) minList.addLast(x);
        else minList.addLast(Math.min(minList.getLast(), x));
    }

    public void pop() {
        numStack.pop();
        minList.removeLast();
    }

    public int top() {
        return numStack.peek();
    }

    public int getMin() {
        return minList.getLast();
    }
}
