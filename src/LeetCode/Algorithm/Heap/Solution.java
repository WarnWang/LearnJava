package LeetCode.Algorithm.Heap;

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
}
