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
//        System.out.println(test.maxCoins(new int[]{1, 6, 8, 5, 1, 7, 7, 4, 1, 8, 5, 4, 3, 5, 8, 8, 9, 7, 4, 4, 5, 2, 2,
//                1, 5, 3, 4, 7, 8, 4, 3, 9, 2, 1, 5, 4, 9, 8, 5, 4, 7, 8, 6, 4, 1, 8, 8, 7, 2, 9, 8, 8, 9, 4, 2, 1, 3,
//                4, 7, 9, 5, 7, 5, 5, 2, 5, 3, 6, 6, 3, 3, 4, 1, 3, 7, 1, 3, 5, 1, 5, 5, 7, 2, 4, 8, 2, 1, 8, 6, 8, 8,
//                1, 5, 1, 9, 9, 1, 4, 6, 2}));
        System.out.println(test.diffWaysToComputeImprove("3+2*2").toString());
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
        for (UndirectedGraphNode node1 : node.neighbors) {
            System.out.println("old: " + node1.label);
        }
        UndirectedGraphNode clonedNode = test.cloneGraph(node);

        for (UndirectedGraphNode node1 : clonedNode.neighbors) {
            System.out.println("new: " + node1.label);
        }
    }

    private static void testMaxNumber() {
        int[] nums1 = {8, 9, 7, 3, 5, 9, 1, 0, 8, 5, 3, 0, 9, 2, 7, 4, 8, 9, 8, 1, 0, 2, 0, 2, 7, 2, 3, 5, 4, 7, 4, 1, 4, 0, 1, 4, 2, 1, 3, 1, 5, 3, 9, 3, 9, 0, 1, 7, 0, 6, 1, 8, 5, 6, 6, 5, 0, 4, 7, 2, 9, 2, 2, 7, 6, 2, 9, 2, 3, 5, 7, 4, 7, 0, 1, 8, 3, 6, 6, 3, 0, 8, 5, 3, 0, 3, 7, 3, 0, 9, 8, 5, 1, 9, 5, 0, 7, 9, 6, 8, 5, 1, 9, 6, 5, 8, 2, 3, 7, 1, 0, 1, 4, 3, 4, 4, 2, 4, 0, 8, 4, 6, 5, 5, 7, 6, 9, 0, 8, 4, 6, 1, 6, 7, 2, 0, 1, 1, 8, 2, 6, 4, 0, 5, 5, 2, 6, 1, 6, 4, 7, 1, 7, 2, 2, 9, 8, 9, 1, 0, 5, 5, 9, 7, 7, 8, 8, 3, 3, 8, 9, 3, 7, 5, 3, 6, 1, 0, 1, 0, 9, 3, 7, 8, 4, 0, 3, 5, 8, 1, 0, 5, 7, 2, 8, 4, 9, 5, 6, 8, 1, 1, 8, 7, 3, 2, 3, 4, 8, 7, 9, 9, 7, 8, 5, 2, 2, 7, 1, 9, 1, 5, 5, 1, 3, 5, 9, 0, 5, 2, 9, 4, 2, 8, 7, 3, 9, 4, 7, 4, 8, 7, 5, 0, 9, 9, 7, 9, 3, 8, 0, 9, 5, 3, 0, 0, 3, 0, 4, 9, 0, 9, 1, 6, 0, 2, 0, 5, 2, 2, 6, 0, 0, 9, 6, 3, 4, 1, 2, 0, 8, 3, 6, 6, 9, 0, 2, 1, 6, 9, 2, 4, 9, 0, 8, 3, 9, 0, 5, 4, 5, 4, 6, 1, 2, 5, 2, 2, 1, 7, 3, 8, 1, 1, 6, 8, 8, 1, 8, 5, 6, 1, 3, 0, 1, 3, 5, 6, 5, 0, 6, 4, 2, 8, 6, 0, 3, 7, 9, 5, 5, 9, 8, 0, 4, 8, 6, 0, 8, 6, 6, 1, 6, 2, 7, 1, 0, 2, 2, 4, 0, 0, 0, 4, 6, 5, 5, 4, 0, 1, 5, 8, 3, 2, 0, 9, 7, 6, 2, 6, 9, 9, 9, 7, 1, 4, 6, 2, 8, 2, 5, 3, 4, 5, 2, 4, 4, 4, 7, 2, 2, 5, 3, 2, 8, 2, 2, 4, 9, 8, 0, 9, 8, 7, 6, 2, 6, 7, 5, 4, 7, 5, 1, 0, 5, 7, 8, 7, 7, 8, 9, 7, 0, 3, 7, 7, 4, 7, 2, 0, 4, 1, 1, 9, 1, 7, 5, 0, 5, 6, 6, 1, 0, 6, 9, 4, 2, 8, 0, 5, 1, 9, 8, 4, 0, 3, 1, 2, 4, 2, 1, 8, 9, 5, 9, 6, 5, 3, 1, 8, 9, 0, 9, 8, 3, 0, 9, 4, 1, 1, 6, 0, 5, 9, 0, 8, 3, 7, 8, 5};
        int[] nums2 = {7, 8, 4, 1, 9, 4, 2, 6, 5, 2, 1, 2, 8, 9, 3, 9, 9, 5, 4, 4, 2, 9, 2, 0, 5, 9, 4, 2, 1, 7, 2, 5, 1, 2, 0, 0, 5, 3, 1, 1, 7, 2, 3, 3, 2, 8, 2, 0, 1, 4, 5, 1, 0, 0, 7, 7, 9, 6, 3, 8, 0, 1, 5, 8, 3, 2, 3, 6, 4, 2, 6, 3, 6, 7, 6, 6, 9, 5, 4, 3, 2, 7, 6, 3, 1, 8, 7, 5, 7, 8, 1, 6, 0, 7, 3, 0, 4, 4, 4, 9, 6, 3, 1, 0, 3, 7, 3, 6, 1, 0, 0, 2, 5, 7, 2, 9, 6, 6, 2, 6, 8, 1, 9, 7, 8, 8, 9, 5, 1, 1, 4, 2, 0, 1, 3, 6, 7, 8, 7, 0, 5, 6, 0, 1, 7, 9, 6, 4, 8, 6, 7, 0, 2, 3, 2, 7, 6, 0, 5, 0, 9, 0, 3, 3, 8, 5, 0, 9, 3, 8, 0, 1, 3, 1, 8, 1, 8, 1, 1, 7, 5, 7, 4, 1, 0, 0, 0, 8, 9, 5, 7, 8, 9, 2, 8, 3, 0, 3, 4, 9, 8, 1, 7, 2, 3, 8, 3, 5, 3, 1, 4, 7, 7, 5, 4, 9, 2, 6, 2, 6, 4, 0, 0, 2, 8, 3, 3, 0, 9, 1, 6, 8, 3, 1, 7, 0, 7, 1, 5, 8, 3, 2, 5, 1, 1, 0, 3, 1, 4, 6, 3, 6, 2, 8, 6, 7, 2, 9, 5, 9, 1, 6, 0, 5, 4, 8, 6, 6, 9, 4, 0, 5, 8, 7, 0, 8, 9, 7, 3, 9, 0, 1, 0, 6, 2, 7, 3, 3, 2, 3, 3, 6, 3, 0, 8, 0, 0, 5, 2, 1, 0, 7, 5, 0, 3, 2, 6, 0, 5, 4, 9, 6, 7, 1, 0, 4, 0, 9, 6, 8, 3, 1, 2, 5, 0, 1, 0, 6, 8, 6, 6, 8, 8, 2, 4, 5, 0, 0, 8, 0, 5, 6, 2, 2, 5, 6, 3, 7, 7, 8, 4, 8, 4, 8, 9, 1, 6, 8, 9, 9, 0, 4, 0, 5, 5, 4, 9, 6, 7, 7, 9, 0, 5, 0, 9, 2, 5, 2, 9, 8, 9, 7, 6, 8, 6, 9, 2, 9, 1, 6, 0, 2, 7, 4, 4, 5, 3, 4, 5, 5, 5, 0, 8, 1, 3, 8, 3, 0, 8, 5, 7, 6, 8, 7, 8, 9, 7, 0, 8, 4, 0, 7, 0, 9, 5, 8, 2, 0, 8, 7, 0, 3, 1, 8, 1, 7, 1, 6, 9, 7, 9, 7, 2, 6, 3, 0, 5, 3, 6, 0, 5, 9, 3, 9, 1, 1, 0, 0, 8, 1, 4, 3, 0, 4, 3, 7, 7, 7, 4, 6, 4, 0, 0, 5, 7, 3, 2, 8, 5, 1, 4, 5, 8, 5, 6, 7, 5, 7, 3, 3, 9, 6, 8, 1, 5, 1, 1, 1, 0, 3};
//        int[] nums1 = {5, 1, 6, 0, 7, 0, 7, 1, 4, 4, 2, 7, 7, 1, 3, 7, 9, 9, 0, 4}, nums2={9, 1, 2, 5, 8, 3};
        TagDynamicProgramming test = new TagDynamicProgramming();
        int[] max = test.maxNumber(nums1, nums2, 500);
        System.out.println(Arrays.toString(max));
    }

    private static void testRandomListNode() {
        TagLinkedList test = new TagLinkedList();
        RandomListNode root = new RandomListNode(-1);
        root.next = new RandomListNode(-1);
        root.next.random = root;
        RandomListNode randomListNode = test.copyRandomList(root);
        for (RandomListNode i = randomListNode; i != null; i = i.next) {
            if (i.random == null)
                System.out.println(i.label);
            else
                System.out.println(i.label + "" + i.random.label);
        }
    }

    public static void main(String[] args) {
        int runTime = 1;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runTime; i++) testDivideAndConquer();
        long runningTime = System.currentTimeMillis() - startTime;
        System.out.println("Running time is " + runningTime / runTime);
    }
}
