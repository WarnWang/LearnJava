package LeetCode.DesignPuzzles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 * Given the list [1,[4,[6]]],
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 */
public class NestedIterator implements Iterator<Integer> {
    ArrayList<Integer> integers = new ArrayList<>();
    int index = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger: nestedList) {
            addIntegerFromNestedList(nestedInteger);
        }
    }

    private void addIntegerFromNestedList(NestedInteger integer) {
        if (integer.isInteger()) {
            integers.add(integer.getInteger());
        } else {
            integer.getList().forEach(this::addIntegerFromNestedList);
        }
    }

    @Override
    public Integer next() {
        if (index < integers.size()) return integers.get(index++);
        else return null;
    }

    @Override
    public boolean hasNext() {
        return index < integers.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
