package LeetCode.DesignPuzzles;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Created by warn on 24/5/2016.
 * Implement the following operations of a stack using queues.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * Notes:
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is
 * empty operations are valid.
 * Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque
 * (double-ended queue), as long as you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top operations will be called on an empty
 * stack).
 */
public class MyStack {

    private int[] stack = new int[2];
    private int index = 0;
    private int stackSize = 2;

    private void enlargeStack(){
        int newSize = stackSize * 2;
        int[] newStack = new int[newSize];
        System.arraycopy(stack, 0, newStack, 0, stackSize);
        stackSize = newSize;
        stack = newStack;
    }

    // Push element x onto stack.
    public void push(int x) {
        if (index == stackSize) enlargeStack();
        stack[index++] = x;
    }

    // Removes the element on top of the stack.
    public void pop() {
        index--;
    }

    // Get the top element.
    public int top() {
        return stack[index - 1];
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return index == 0;
    }
}

class MyLinkedListStack {

    private class Node {
        Node parent, child;
        int val;
        Node(int x) {
            val = x;
        }
    }

    private Node stack;

    // Push element x onto stack.
    public void push(int x) {
        if (stack == null) stack = new Node(x);
        else {
            Node child = new Node(x);
            stack.child = child;
            child.parent = stack;
            stack = child;
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        stack = stack.parent;
    }

    // Get the top element.
    public int top() {
        return stack.val;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return stack == null;
    }
}


class MyArraylistStack {

    private ArrayList<Integer> stack = new ArrayList<>();
    private int index = 0;
    private int size = 0;

    // Push element x onto stack.
    public void push(int x) {
        if (index < size) {
            stack.set(index, x);
        } else {
            stack.add(x);
            size++;
        }
        index++;
    }

    // Removes the element on top of the stack.
    public void pop() {
        index--;
    }

    // Get the top element.
    public int top() {
        return stack.get(index - 1);
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return index == 0;
    }
}