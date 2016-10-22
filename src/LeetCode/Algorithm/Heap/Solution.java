package LeetCode.Algorithm.Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by warn on 13/8/2016.
 * Save function with tag heap
 */
public class Solution {

    /**
     * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest
     * element in the matrix.
     * <p>
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     * <p>
     * Example:
     * <p>
     * matrix = [
     * [ 1,  5,  9],
     * [10, 11, 13],
     * [12, 13, 15]
     * ],
     * k = 8,
     * <p>
     * return 13.
     *
     * @param matrix a n x n matrix
     * @param k      the kth smallest element in the sorted order
     * @return the kth smallest element in the sorted order
     */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        if (k == 1) return matrix[0][0];
        int width = matrix.length;
        int height = matrix[0].length;
        if (k == width * height) return matrix[width - 1][height - 1];
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, (o1, o2) -> matrix[o1[0]][o1[1]] - matrix[o2[0]][o2[1]]);
        heap.offer(new int[]{0, 0});
        int c = 0;
        while (true) {
            int[] currentIndex = heap.poll();
            if (++c == k) return matrix[currentIndex[0]][currentIndex[1]];
            if (currentIndex[0] == 0 && currentIndex[1] < height - 1)
                heap.offer(new int[]{currentIndex[0], currentIndex[1] + 1});
            if (currentIndex[0] < width - 1) heap.offer(new int[]{currentIndex[0] + 1, currentIndex[1]});
        }
    }

    /**
     * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
     * <p>
     * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
     * <p>
     * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
     * <p>
     * Example 1:
     * Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
     * <p>
     * Return: [1,2],[1,4],[1,6]
     * <p>
     * The first 3 pairs are returned from the sequence:
     * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     * Example 2:
     * Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
     * <p>
     * Return: [1,1],[1,1]
     * <p>
     * The first 2 pairs are returned from the sequence:
     * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     * Example 3:
     * Given nums1 = [1,2], nums2 = [3],  k = 3
     * <p>
     * Return: [1,3],[2,3]
     * <p>
     * All possible pairs are returned from the sequence:
     * [1,3],[2,3]
     *
     * @param nums1 one array of number
     * @param nums2 another array of number
     * @param k     required number
     * @return result list
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> resultList = new ArrayList<>();
        if (k == 0) return resultList;

        PriorityQueue<int[]> combinationList = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] + o1[1] - o2[0] - o2[1];
            }
        });
        for (int num1 : nums1)
            for (int num2 : nums2) combinationList.add(new int[]{num1, num2});

        while (!combinationList.isEmpty() && k > 0) {
            resultList.add(combinationList.poll());
            k--;
        }

        return resultList;
    }
}
