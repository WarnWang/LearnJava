package LeetCode;

import net.datastructures.Tree;

import java.util.*;

/**
 * Created by warn on 4/5/2016.
 * Use to store puzzles with tag Dynamic programming
 */
public class TagDynamicProgramming {
    /**
     * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
     * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] *
     * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then
     * becomes adjacent.
     * <p>
     * Find the maximum coins you can collect by bursting the balloons wisely.
     * <p>
     * Note:
     * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
     * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
     * <p>
     * Example:
     * <p>
     * Given [3, 1, 5, 8]
     * <p>
     * Return 167
     * <p>
     * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
     *
     * @param nums a coin of balloons
     * @return maximum coins you can collect
     */
    public int maxCoins(int[] nums) {
        int[][] maxCoin = new int[nums.length + 2][nums.length + 2];
        return getMaxCoin(maxCoin, 0, nums.length + 1, nums);
    }

    private int getMaxCoin(int[][] maxCoin, int left, int right, int[] nums) {
        if (maxCoin[left][right] != 0) return maxCoin[left][right];
        else {
            int maxCoinValue = 0;
            for (int i = left + 1; i < right; i++) {
                maxCoinValue = Math.max(maxCoinValue, ((right == nums.length + 1) ? 1 : nums[right - 1]) *
                        ((left == 0) ? 1 : nums[left - 1]) * nums[i - 1] + getMaxCoin(maxCoin, left, i, nums) +
                        getMaxCoin(maxCoin, i, right, nums));
            }
            maxCoin[left][right] = maxCoinValue;
            return maxCoinValue;
        }
    }

    /**
     * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length
     * k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved.
     * Return an array of the k digits. You should try to optimize your time and space complexity.
     * <p>
     * Example 1:
     * nums1 = [3, 4, 6, 5]
     * nums2 = [9, 1, 2, 5, 8, 3]
     * k = 5
     * return [9, 8, 6, 5, 3]
     * <p>
     * Example 2:
     * nums1 = [6, 7]
     * nums2 = [6, 0, 4]
     * k = 5
     * return [6, 7, 6, 0, 4]
     * <p>
     * Example 3:
     * nums1 = [3, 9]
     * nums2 = [8, 9]
     * k = 3
     * return [9, 8, 9]
     *
     * @param nums1 array 1
     * @param nums2 array 2
     * @param k     maximum number length
     * @return the maximum number
     */
    public int[] maxNumberTLE(int[] nums1, int[] nums2, int k) {
        if (nums1 == null && nums2 == null) return null;
        if (nums1 == null) nums1 = new int[0];
        else if (nums2 == null) nums2 = new int[0];
        int n1 = nums1.length, n2 = nums2.length;
        int[] maximum = new int[k];
        if (k == (n1 + n2)) maxNumber(maximum, 0, nums1, 0, nums2, 0);
        else findMaxNumber(maximum, 0, nums1, 0, nums2, 0);
        return maximum;
    }

    private void findMaxNumber(int[] maximum, int maxIndex, int[] nums1, int nums1Index, int[] nums2, int nums2Index) {
        if (maxIndex == maximum.length) return;
        int remainLength = maximum.length - maxIndex;
        if (nums2.length - nums2Index + nums1.length - nums1Index == remainLength) {
            maxNumber(maximum, maxIndex, nums1, nums1Index, nums2, nums2Index);
            return;
        }
        int tmpMax = 0;
        int tmpMaxIndex1 = -1, tmpMaxIndex2 = -1;
        for (int i = nums1Index; i < nums1.length; i++) {
            if (nums2.length - nums2Index + nums1.length - i < remainLength) break;
            if (nums1[i] > tmpMax) {
                tmpMaxIndex1 = i;
                tmpMax = nums1[i];
                if (tmpMax == 9) break;
            }
        }
        for (int i = nums2Index; i < nums2.length; i++) {
            if (nums2.length - i + nums1.length - nums1Index < remainLength) break;
            if (nums2[i] > tmpMax) {
                tmpMax = nums2[i];
                tmpMaxIndex1 = -1;
                tmpMaxIndex2 = i;
                if (tmpMax == 9) break;
            } else if (tmpMaxIndex2 == -1 && nums2[i] == tmpMax) {
                tmpMaxIndex2 = i;
                if (tmpMax == 9) break;
            }
        }

        if (tmpMaxIndex1 == -1 || tmpMaxIndex2 == -1) {
            maximum[maxIndex] = tmpMax;
            if (tmpMaxIndex1 == -1) findMaxNumber(maximum, maxIndex + 1, nums1, nums1Index, nums2, tmpMaxIndex2 + 1);
            else findMaxNumber(maximum, maxIndex + 1, nums1, tmpMaxIndex1 + 1, nums2, nums2Index);
        }
//        else if (nums1.length - tmpMaxIndex1 + nums2.length - tmpMaxIndex2 >= maximum.length - maxIndex) {
//            maximum[maxIndex] = tmpMax;
//            if (maxIndex + 1 == maximum.length) return;
//            maximum[maxIndex + 1] = tmpMax;
//            findMaxNumber(maximum, maxIndex + 2, nums1, tmpMaxIndex1 + 1, nums2, tmpMaxIndex2 + 1);
//        }
        else {
            maximum[maxIndex] = tmpMax;
            int[] temp1 = new int[remainLength - 1];
            int[] temp2 = new int[remainLength - 1];
            findMaxNumber(temp1, 0, nums1, tmpMaxIndex1 + 1, nums2, nums2Index);
            findMaxNumber(temp2, 0, nums1, nums1Index, nums2, tmpMaxIndex2 + 1);
            if (isBigger(temp1, temp2, 0))
                System.arraycopy(temp1, 0, maximum, maxIndex + 1, remainLength - 1);
            else
                System.arraycopy(temp2, 0, maximum, maxIndex + 1, remainLength - 1);
        }
    }

    private boolean isBigger(int[] nums1, int[] nums2, int nums2Index) {
        for (int i = 0, n = nums1.length; i < n; i++) {
            if (nums1[i] < nums2[i + nums2Index]) return false;
            else if (nums1[i] > nums2[i]) return true;
        }
        return false;
    }

    private void maxNumber(int[] maximum, int maxIndex, int[] nums1, int nums1Index, int[] nums2, int nums2Index) {
        for (int n = maximum.length; maxIndex < n; maxIndex++) {
            if (nums1Index >= nums1.length || nums2Index >= nums2.length) break;
            if (nums1[nums1Index] > nums2[nums2Index]) {
                maximum[maxIndex] = nums1[nums1Index++];
            } else {
                maximum[maxIndex] = nums2[nums2Index++];
            }
        }
        if (maxIndex != maximum.length) {
            if (nums1Index < nums1.length) {
                nums2 = nums1;
                nums2Index = nums1Index;
            }
            for (int n = nums2.length; nums2Index < n; nums2Index++) {
                maximum[maxIndex++] = nums2[nums2Index];
            }
        }
    }

    // Reference: https://leetcode.com/discuss/93176/12ms-java-code-beat-javasubmissions-little-long-messy-fast
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int k1 = Integer.min(k, nums1.length);
        int k2 = Integer.min(k, nums2.length);
        int[][] v1 = new int[k + 1][];
        int[][] v2 = new int[k + 1][];
        v1[k1] = bestChoice(nums1, k1);
        v2[k2] = bestChoice(nums2, k2);
        if (nums1.length + nums2.length > k) {
            for (int i = k1; i > 0; i--) {
                v1[i - 1] = trimList(v1[i], i);
            }
            for (int i = k2; i > 0; i--) {
                v2[i - 1] = trimList(v2[i], i);
            }
        }

        int[] merged = null;
        for (int i = 0; i <= k; i++) {
            if (v1[i] == null || v2[k - i] == null) continue;
            merged = mergeTwoList(v1[i], i, v2[k - i], k - i, merged);
        }
        return merged;
    }

    private int[] trimList(int[] lastInput, int lastLength) {
        if (lastLength == 1) return new int[0];
        int[] output = new int[lastLength - 1];
        int j = 0;
        for (int i = 0; i < lastLength; i++) {
            if (j == i && (i == lastLength -1 || lastInput[i] < lastInput[i + 1])) continue;
            output[j++] = lastInput[i];
        }
        return output;
    }

    private int[] bestChoice(int[] inputNum, int k){
        if (k == 0) return new int[0];
        int[] output = new int[Math.max(k, inputNum.length)];
        int cur = 1;
        output[0] = inputNum[0];
        for (int i = 1; i < inputNum.length; i++) {
            while (cur > 0 && inputNum[i] > output[cur - 1] && k - cur < inputNum.length - i) cur--;
            output[cur++] = inputNum[i];
        }
        return output;
    }

    private int[] mergeTwoList(int[] nums1, int n1, int[] nums2, int n2, int[] previous){
        int i1 = 0, i2 = 0;
        int[] output = new int[n1 + n2];
        int i = 0;
        while (i1 < n1 && i2 < n2) {
            if (nums1[i1] > nums2[i2]) output[i++] = nums1[i1++];
            else if (nums2[i2] > nums1[i1]) output[i++] = nums2[i2++];
            else {
                int ii1 = i1;
                int ii2 = i2;
                while (nums1[ii1] == nums2[ii2]){
                    if (ii1 < n1 - 1) ii1++;
                    if (ii2 < n2 - 1) ii2++;
                    if (ii1 == n1 - 1 && ii2 == n2 - 1) break;
                }
                if (nums1[ii1] > nums2[ii2]) output[i++] = nums1[i1++];
                else output[i++] = nums2[i2++];
            }
            if (previous != null) {
                if (output[i - 1] < previous[i - 1]) return previous;
                if (output[i - 1] > previous[i - 1]) previous = null;
            }
        }
        if (i1 != n1) {
            while (i1 < n1) {
                output[i++] = nums1[i1++];
                if (previous != null) {
                    if (output[i - 1] < previous[i - 1]) return previous;
                    if (output[i - 1] > previous[i - 1]) previous = null;
                }
            }
        } else {
            while (i2 < n2) {
                output[i++] = nums2[i2++];
                if (previous != null) {
                    if (output[i - 1] < previous[i - 1]) return previous;
                    if (output[i - 1] > previous[i - 1]) previous = null;
                }
            }
        }
        return output;
    }

    /**
     * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
     * <p>
     * For example,
     * Given n = 3, your program should return all 5 unique BST's shown below.
     * <p>
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
     * <p>
     *
     * @param n a tree with different tree node
     * @return all possible of different binarysearch tree
     */
    public List<TreeNode> generateTrees(int n) {
        return generateTreeNodes(1, n);
    }

    ArrayList<TreeNode> generateTreeNodes(int start, int max){
        ArrayList<TreeNode> treeNodeList = new ArrayList<>();
        ArrayList<TreeNode> leftTreeNodeList;
        ArrayList<TreeNode> rightTreeNodeList;
        for (int i = start; i <= max; i++) {
            leftTreeNodeList = generateTreeNodes(start, i - 1);
            rightTreeNodeList = generateTreeNodes(i + 1, max);
            if (leftTreeNodeList.isEmpty()) leftTreeNodeList.add(null);
            if (rightTreeNodeList.isEmpty()) rightTreeNodeList.add(null);
            for (TreeNode left: leftTreeNodeList) {
                for (TreeNode right: rightTreeNodeList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    treeNodeList.add(root);
                }
            }
        }
        return treeNodeList;
    }
}
