package LeetCode.DataTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by warn on 9/5/2016.
 *
 * This class will be used in the coming tag graph
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
