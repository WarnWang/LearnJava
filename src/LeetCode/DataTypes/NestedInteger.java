package LeetCode.DataTypes;

import java.util.List;

/**
 * Created by warn on 28/5/2016.
 */
public interface NestedInteger {
    //Returns true if this NestedInteger holds a single integer, rather than a nested list
    public boolean isInteger();

    //Returns the single integer that the NestedInteger holds, if it holds a single integer
    //Returns null if this NestedInteger holds a nested list
    public Integer getInteger();

    //Returns the nested list that this NestedInteger holds, if it holds a nested list
    //Returns null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
