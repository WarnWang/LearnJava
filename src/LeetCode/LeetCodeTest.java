package LeetCode;

import java.util.Arrays;

/**
 * Created by warn on 4/4/16.
 * <p>
 * Use to test code
 */
public class LeetCodeTest {
    private static void testDFS() {
        TagDFS test;
        test = new TagDFS();
//        System.out.println(test.partition("baabc").toString());
        int nCourse = 2;
        int[][] prerequisites = {{1, 0}};
        int[] result = test.findOrder(nCourse, prerequisites);
        System.out.println(Arrays.toString(result));
    }

    private static void testMath() {
        TagMath test = new TagMath();
//        System.out.print(test.calculate("(((((8 - 12))))) +3"));
        System.out.println(test.isAdditiveNumber("12345"));
    }

    private static void testSort() {
//        int[] num = new int[100];
//        Random random = new Random();
//        for (int i = 0; i < 100; i++) {
//            num[i] = random.nextInt(100);
//        }
        int[] num = {4, 5, 5, 6};
        TagSort test = new TagSort();
//        test.wiggleSort(num);
//        System.out.println(Arrays.toString(num));
    }

    private static void testArray() {
        TagArray test = new TagArray();
        int[] nums = {1, 3, 2, 2, 3, 1};
//        System.out.println(test.findKthLargestArrayOnly(nums, 3));
        test.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void testDivideAndConquer() {
        TagDivideAndConquer test = new TagDivideAndConquer();
        System.out.println(test.maxCoins(new int[]{1, 6, 8, 5, 1, 7, 7, 4, 1, 8, 5, 4, 3, 5, 8, 8, 9, 7, 4, 4, 5, 2, 2,
                1, 5, 3, 4, 7, 8, 4, 3, 9, 2, 1, 5, 4, 9, 8, 5, 4, 7, 8, 6, 4, 1, 8, 8, 7, 2, 9, 8, 8, 9, 4, 2, 1, 3,
                4, 7, 9, 5, 7, 5, 5, 2, 5, 3, 6, 6, 3, 3, 4, 1, 3, 7, 1, 3, 5, 1, 5, 5, 7, 2, 4, 8, 2, 1, 8, 6, 8, 8,
                1, 5, 1, 9, 9, 1, 4, 6, 2}));
    }

    private static void testDynamicProgramming() {
        TagDynamicProgramming test = new TagDynamicProgramming();
        System.out.println(test.maxCoins(new int[]{1, 6, 8, 5, 1, 7, 7, 4, 1, 8, 5, 4, 3, 5, 8, 8, 9, 7, 4, 4, 5, 2, 2,
                1, 5, 3, 4, 7, 8, 4, 3, 9, 2, 1, 5, 4, 9, 8, 5, 4, 7, 8, 6, 4, 1, 8, 8, 7, 2, 9, 8, 8, 9, 4, 2, 1, 3,
                4, 7, 9, 5, 7, 5, 5, 2, 5, 3, 6, 6, 3, 3, 4, 1, 3, 7, 1, 3, 5, 1, 5, 5, 7, 2, 4, 8, 2, 1, 8, 6, 8, 8,
                1, 5, 1, 9, 9, 1, 4, 6, 2}));
    }

    private static void testGraph() {
        TagGraph test = new TagGraph();
        UndirectedGraphNode node = new UndirectedGraphNode(0);
        node.neighbors.add(node);
        node.neighbors.add(node);
        for (UndirectedGraphNode node1: node.neighbors){
            System.out.println("old: " + node1.label);
        }
        UndirectedGraphNode clonedNode = test.cloneGraph(node);

        for (UndirectedGraphNode node1: clonedNode.neighbors){
            System.out.println("new: " + node1.label);
        }
    }

    private static void testRandomListNode() {
        TagLinkedList test = new TagLinkedList();
        RandomListNode root = new RandomListNode(-1);
        root.next = new RandomListNode(-1);
        root.next.random = root;
        RandomListNode randomListNode = test.copyRandomList(root);
        for (RandomListNode i = randomListNode; i != null; i = i.next){
            if (i.random == null)
                System.out.println(i.label);
            else
                System.out.println(i.label + "" + i.random.label);
        }
    }

    public static void main(String[] args) {
        int runTime = 1;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runTime; i++) testRandomListNode();
        long runningTime = System.currentTimeMillis() - startTime;
        System.out.println("Running time is " + runningTime / runTime);
    }
}
