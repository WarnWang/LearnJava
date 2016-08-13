package LeetCode.Algorithm.Search;

import java.util.ArrayList;

/**
 * Created by warn on 16/7/2016.
 * Store solutions to question with tag binary search
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch test = new BinarySearch();
    }

    int guess(int num) {
        return 0;
    }

    public int guessNumber(int n) {
        int upBound = n, lowBound = 1;
        while (upBound >= lowBound) {
            if (guess(upBound) == 0) return upBound;
            else if (guess(lowBound) == 0) return lowBound;
            int middle = upBound / 2 + lowBound / 2;
            int guessMiddle = guess(middle);
            if (guessMiddle == 0) return middle;
            else if (guessMiddle == -1) {
                upBound = middle - 1;
                lowBound++;
            } else {
                upBound--;
                lowBound = middle + 1;
            }
        }
        return 0;
    }

    /**
     * Given a positive integer num, write a function which returns True if num is a perfect square else False.
     * <p>
     * Note: Do not use any built-in library function such as sqrt.
     * <p>
     * Example 1:
     * <p>
     * Input: 16
     * Returns: True
     * Example 2:
     * <p>
     * Input: 14
     * Returns: False
     *
     * @param num a positive integer num
     * @return whether this number is perfect square or not
     */
    public boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        if (num <= 1) return true;
        int start = 1;
        int end = Math.min(num, 46340);
        while (start < end) {
            if (start * start == num || end * end == num) return true;
            else if (start * start > num || end * end < num) return false;
            int middle = start / 2 + end / 2;
            if (middle * middle == num) return true;
            else if (middle * middle > num) end = middle;
            else start = middle;
        }
        return false;
    }

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
        return findKthSmallest(matrix, matrix[width - 1][height - 1], matrix[0][0], k);
    }

    private int findKthSmallest(int[][] matrix, int high, int low, int k) {
        int middle = high / 2 + low / 2;
        ArrayList<Integer> smaller = new ArrayList<>();
        ArrayList<Integer> larger = new ArrayList<>();
        int nEqual = 0;
        for (int[] row : matrix) {
            for (int cell : row) {
                if (cell > middle) larger.add(cell);
                else if (cell == middle) nEqual++;
                else smaller.add(cell);
            }
        }
        if (smaller.size() >= k) return findKthSmallest(smaller, k);
        else if (smaller.size() + nEqual >= k) return middle;
        else return findKthSmallest(larger, k - smaller.size() - nEqual);
    }

    private int findKthSmallest(ArrayList<Integer> integerList, int k) {
        ArrayList<Integer> smaller = new ArrayList<>();
        ArrayList<Integer> larger = new ArrayList<>();
        int nEquals = 0;
        int n = integerList.get(integerList.size() / 2);
        for (int i : integerList) {
            if (i > n) larger.add(i);
            else if (i < n) smaller.add(i);
            else nEquals++;
        }
        if (smaller.size() >= k) return findKthSmallest(smaller, k);
        else if (smaller.size() + nEquals >= k) return n;
        else return findKthSmallest(larger, k - nEquals - smaller.size());
    }
}
