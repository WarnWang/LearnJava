package LeetCode.Algorithm.Search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by warn on 16/7/2016.
 * Store solutions to question with tag binary search
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch test = new BinarySearch();
        test.searchRange(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 6, 6, 6, 8, 10, 10}, 4);
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

    /**
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     * <p>
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * <p>
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     * <p>
     * You may assume no duplicate exists in the array.
     *
     * @param nums   a rotated sorted array
     * @param target target value
     * @return the index about the target
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int n = nums.length;
        if (nums[0] < nums[n - 1] || n == 1) {
            int index = Arrays.binarySearch(nums, target);
            return (index < 0)? -1 : index;
        } else {
            int end = n;
            int start = 0;
            if (target == nums[0]) return 0;
            else if (target == nums[n - 1]) return n - 1;
            while (start < end) {
                int mid = (start + end) / 2;
                if (mid == start) break;
                if (nums[mid] > nums[start]) start = mid;
                else end = mid;
            }
            int index;
            if (target <= nums[start] && target > nums[0])
                index = Arrays.binarySearch(nums, 0, start + 1, target);
            else if (target >= nums[start + 1] && target < nums[n - 1])
                index = Arrays.binarySearch(nums, start + 1, n, target);
            else
                index = -1;
            return (index < 0)? -1 : index;
        }
    }

    /**
     * Given a sorted array of integers, find the starting and ending position of a given target value.
     * <p>
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * <p>
     * If the target is not found in the array, return [-1, -1].
     * <p>
     * For example,
     * Given [5, 7, 7, 8, 8, 10] and target value 8,
     * return [3, 4].
     *
     * @param nums   a sorted array of integers
     * @param target a given target value
     * @return the position of target value
     */
    public int[] searchRange(int[] nums, int target) {
        int[] range = new int[2];
        range[0] = -1;
        range[1] = -1;
        if (nums != null) searchRange(nums, target, range, 0, nums.length);
        return range;
    }

    private void searchRange(int[] nums, int target, int[] range, int start, int end) {
        if (start >= end) return;
        if (nums[start] == target) {
            range[0] = start;
            range[1] = findSamePositionFromStart(nums, target, start + 1, end);
            if (range[1] == -1) range[1] = start;
        } else if (nums[end - 1] == target) {
            range[1] = end - 1;
            range[0] = findSamePositionFromEnd(nums, target, start, end - 1);
            if (range[0] == -1) range[0] = range[1];
        } else {
            int mid = (start + end) / 2;
            if (nums[mid] > target) searchRange(nums, target, range, start, mid);
            else if (nums[mid] < target) searchRange(nums, target, range, mid + 1, end);
            else {
                range[0] = findSamePositionFromEnd(nums, target, start, mid + 1);
                range[1] = findSamePositionFromStart(nums, target, mid, end);
            }
        }
    }

    private int findSamePositionFromStart(int[] nums, int target, int start, int end) {
        if (start >= end || nums[start] != target) return -1;

        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            int result = findSamePositionFromStart(nums, target, mid + 1, end);
            if (result == -1) return mid;
            else return result;
        } else {
            int result = findSamePositionFromStart(nums, target, start + 1, mid);
            if (result == -1) return start;
            else return result;
        }
    }

    private int findSamePositionFromEnd(int[] nums, int target, int start, int end) {
        if (start >= end || nums[end - 1] != target) return -1;
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            int result = findSamePositionFromEnd(nums, target, start, mid);
            if (result == -1) return mid;
            else return result;
        } else {
            int result = findSamePositionFromEnd(nums, target, mid + 1, end - 1);
            if (result == -1) return end - 1;
            else return result;
        }
    }

    /**
     * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest
     * version of your product fails the quality check. Since each version is developed based on the previous version,
     * all the versions after a bad version are also bad.
     * <p>
     * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the
     * following ones to be bad.
     * <p>
     * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function
     * to find the first bad version. You should minimize the number of calls to the API.
     *
     * @param n current total version number
     * @return the latest version
     */
    public int firstBadVersion(int n) {
        if (isBadVersion(1)) return 1;
        int low = 2;
        int high = n;
        int mid = low + (high - low) / 2;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (isBadVersion(mid)) high = mid;
            else low = mid + 1;
        }
        if (low == high) return low;
        return mid;
    }


    private boolean isBadVersion(int version) {
        return version % 2 == 0;
    }
}
