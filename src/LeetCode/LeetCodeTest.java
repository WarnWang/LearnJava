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

    public static void main(String[] args) {
        int runTime = 1;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runTime; i++) testMath();
        long runningTime = System.currentTimeMillis() - startTime;
        System.out.println("Running time is " + runningTime / runTime);
    }
}
