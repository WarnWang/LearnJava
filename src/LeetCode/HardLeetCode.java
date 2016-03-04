package LeetCode;

import java.util.*;

/**
 * Created by warn on 14/2/2016.
 */
public class HardLeetCode {
    public static void main(String[] args) {
        // put your codes here
        HardLeetCode test = new HardLeetCode();
//        System.out.println(test.candy(new int[]{1}));
//        System.out.println(test.maximalRectangle(new String[] {"000", "000", "000"}));
        test.countSmallerBST(new int[]{5, 2, 6, 1});
    }

    public int candy(int[] ratings) {
        int[] childCandy = new int[ratings.length];
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) childCandy[i] = childCandy[i - 1] + 1;
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && childCandy[i] <= childCandy[i + 1]) childCandy[i] =
                    childCandy[i + 1] + 1;
        }
        int total = 0;
        for (int i : childCandy) total += i;
        return total + ratings.length;
    }


    public int expandValue(char[][] matrix, int x, int y) {
        int offsetX = 0;
        int area;
        for (int i = x + 1; i < matrix.length; i++) {
            if (matrix[i][y] == '0') break;
            offsetX++;
        }

        area = offsetX + 1;
        for (int i = y + 1; i < matrix[0].length; i++) {
            if (matrix[x][i] == '0') break;
            int areaCount = 1;
            for (int j = 1; j <= offsetX; j++) {
                if (matrix[x + j][i] == '0') {
                    offsetX = j - 1;
                    break;
                }
                areaCount++;
            }
            int tempArea = areaCount * (i - y + 1);
            if (tempArea > area) area = tempArea;
        }
        return area;
    }


    public int maximalRectangle(char[][] matrix) {
        int height = matrix.length;
        if (height == 0) return 0;
        int width = matrix[0].length;
        int result = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char temp = matrix[i][j];
                if (temp == '1') {
                    int tempResult = expandValue(matrix, i, j);
                    if (tempResult > result) result = tempResult;
                }
            }
        }
        return result;
    }

    public int maxProfit(int k, int[] prices) {
        if (prices.length <= 1) return 0;
        if (k > prices.length) k = prices.length;
        k = k / 2 * 2;
        List<int[]> profitList = new ArrayList<>();
        int temp = -1;
        for (int i = 0, n = prices.length; i < n - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                if (temp == -1) temp = prices[i];
            } else if (prices[i] > prices[i + 1]) {
                if (temp != -1) profitList.add(new int[]{temp, prices[i]});
                temp = -1;
            }
        }
        if (temp != -1 && prices[prices.length - 1] > prices[prices.length - 2]) profitList.add(new int[]
                {temp, prices[prices.length - 1]});
        return 1;
    }

    public int largestRectangleArea1(int[] heights) {
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            int width = 0;
            int height = heights[i];
            if (i > 0 && height <= heights[i - 1]) continue;
            for (int j = i; j < heights.length; j++) {
                if (heights[j] < height) height = heights[j];
                width++;
                int newArea = width * height;
                if (newArea > area) area = newArea;
            }
        }
        return area;
    }

    public int largestRectangleArea(int[] heights) {
        return largestRectangleArea(heights, 0, heights.length - 1);
    }

    public int largestRectangleArea(int[] heights, int startIndex, int endIndex) {
        if (endIndex == startIndex) return heights[startIndex];
        else if (endIndex < startIndex) return 0;

        int minimalValueIndex = -1;
        int min = -1;
        for (int i = startIndex; i <= endIndex; i++) {
            if (min < 0 || heights[i] < min) {
                min = heights[i];
                minimalValueIndex = i;
            }
        }
        int area = min * (endIndex - startIndex + 1);
        area = Math.max(largestRectangleArea(heights, startIndex, minimalValueIndex - 1), area);
        area = Math.max(largestRectangleArea(heights, minimalValueIndex + 1, endIndex), area);
        return area;
    }

    public int largestRectangleAreaNonrecursive(int[] heights) {
        if (heights.length == 0) return 0;
        else if (heights.length == 1) return heights[0];
        Stack<Integer> tempHeight = new Stack<>();
        int area = 0;
        int i = 0;
        while (i < heights.length) {
            if (tempHeight.isEmpty() || heights[i] >= heights[tempHeight.peek()]) {
                tempHeight.push(i);
                i++;
            } else {
                int temp = tempHeight.pop();
                int height = heights[temp];
                int w = tempHeight.isEmpty() ? i : i - tempHeight.peek() - 1;
                area = Math.max(height * w, area);
            }
        }

        while (!tempHeight.isEmpty()) {
            int p = tempHeight.pop();
            int h = heights[p];
            int w = tempHeight.isEmpty() ? i : i - tempHeight.peek() - 1;
            area = Math.max(h * w, area);
        }
        return area;
    }

    public int largestRectangleDynamicProgramming(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        else if (heights.length == 1) return heights[0];
        int area = 0;
        int[] maxLeft = new int[heights.length];
        int[] maxRight = new int[heights.length];
        maxLeft[0] = -1;
        maxRight[heights.length - 1] = heights.length;
        for (int i = 1; i < heights.length; i++) {
            int leftH = i - 1;
            while (leftH >= 0) {
                if (heights[leftH] < heights[i]) break;
                else leftH = maxLeft[leftH];
            }
            maxLeft[i] = leftH;
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            int rightH = i + 1;
            while (rightH < heights.length) {
                if (heights[rightH] < heights[i]) break;
                else rightH = maxRight[rightH];
            }
            maxRight[i] = rightH;
        }

        for (int i = 0; i < heights.length; i++) {
            int tempArea = heights[i] * (maxRight[i] - maxLeft[i] - 1);
            area = Math.max(tempArea, area);
        }
        return area;
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        if (maxWidth <= 1) return Arrays.asList(words);
        List<String> result = new ArrayList<>();
        for (int i = 0, tempLength = maxWidth, temp = 0; i < words.length; i++) {
            if (tempLength > (words[i].length()) && i < words.length - 1) {
                tempLength -= words[i].length();
                tempLength--;
            } else if (tempLength == words[i].length()) {
                if (temp < i) result.add(concatenateString(words, temp, i, i - temp, 0));
                else result.add(words[i]);
                temp = i + 1;
                tempLength = maxWidth;
            } else if (tempLength < words[i].length()) {
                tempLength += i - temp;
                if (i - temp == 1) result.add(concatenateString(words, temp, temp, 0, tempLength));
                else result.add(concatenateString(words, temp, i - 1, tempLength, 0));
                if (i == words.length - 1) {
                    result.add(concatenateString(words, i, i, 0, maxWidth - words[i].length()));
                } else {
                    if (words[i].length() == maxWidth) {
                        result.add(words[i]);
                        temp = i + 1;
                        tempLength = maxWidth;
                    } else {
                        temp = i;
                        tempLength = maxWidth - words[i].length() - 1;
                    }
                }
            } else {
                if (temp == i) result.add(concatenateString(words, i, i, 0, maxWidth - words[i].length()));
                else result.add(concatenateString(words, temp, i, i - temp, tempLength - words[i].length()));
            }
        }
        return result;
    }

    private String concatenateString(String[] words, int startIndex, int endIndex, int spaceLength, int padLength) {
        int n = (endIndex == startIndex) ? 1 : endIndex - startIndex;
        StringBuilder result = new StringBuilder();
        int leftAdd = spaceLength % n;
        int everySpace = spaceLength / n;
        for (int i = startIndex; i < endIndex; i++) {
            result.append(words[i]);
            for (int j = 0; j < everySpace; j++) {
                result.append(' ');
            }
            if (i < leftAdd + startIndex) result.append(' ');
        }
        result.append(words[endIndex]);
        for (int i = 0; i < padLength; i++) {
            result.append(' ');
        }
        return result.toString();
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return Arrays.asList(new Integer[]{});
        int n = nums.length;
        List<Integer> sortedList = new LinkedList<>();
        Integer[] countSmall = new Integer[n];
        for (int i = n - 1; i >= 0; i--) {
            int index = 0;
            if (sortedList.size() == index) sortedList.add(nums[i]);
            else {
                index = findIndex(sortedList, 0, sortedList.size() - 1, nums[i]);
                sortedList.add(index, nums[i]);
            }
            countSmall[i] = index;
        }
        return Arrays.asList(countSmall);
    }

    public List<Integer> countSmallerBST(int[] nums) {
        if (nums.length == 0) return Arrays.asList(new Integer[]{});
        int n = nums.length;
        BinarySearchTree numsTree = new BinarySearchTree(nums[n - 1]);
        Integer[] countSmall = new Integer[n];
        countSmall[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            numsTree.put(nums[i]);
            countSmall[i] = numsTree.rank(nums[i]);
        }
        return Arrays.asList(countSmall);
    }

    public int findIndex(List<Integer> list, int low, int high, int target) {
        if (list.get(low) >= target) return low;
        if (list.get(high) < target) return high + 1;
        int mid = (high + low) / 2;
        if (list.get(mid) < target) return findIndex(list, mid + 1, high, target);
        else return findIndex(list, low, mid - 1, target);
    }

    class BinarySearchTree {
        Node root;

        BinarySearchTree(int val) {
            root = new Node(val);
        }

        public Node put(Node root, int val) {
            if (root == null) {
                root = new Node(val);
            } else if (root.val == val) {
                root.count++;
            } else if (root.val > val) {
                root.leftNode = put(root.leftNode, val);
            } else {
                root.rightNode = put(root.rightNode, val);
            }
            root.size = root.count + countSubNodes(root.leftNode) + countSubNodes(root.rightNode);
            return root;
        }

        public Node put(int val) {
            root = put(root, val);
            return root;
        }

        public int rank(int val) {
            return rank(root, val);
        }

        public int countSubNodes(Node root) {
            if (root == null) return 0;
            else return root.size;
        }

        public int rank(Node rootNode, int val) {
            if (rootNode == null) return 0;
            if (val > rootNode.val)
                return rootNode.count + countSubNodes(rootNode.leftNode) + rank(rootNode.rightNode,
                        val);
            else if (val == rootNode.val) return countSubNodes(rootNode.leftNode);
            else {
                return rank(rootNode.leftNode, val);
            }
        }

        class Node {
            int val;
            int count;
            int size;
            Node leftNode;
            Node rightNode;

            Node(int val) {
                this.val = val;
                count = 1;
                size = 1;
            }
        }
    }
}