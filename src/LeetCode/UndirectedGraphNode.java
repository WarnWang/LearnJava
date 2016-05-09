package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by warn on 9/5/2016.
 *
 * This class will be used in the coming tag graph
 */
public class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
