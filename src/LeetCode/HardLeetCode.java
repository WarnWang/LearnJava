package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by warn on 14/2/2016.
 */
public class HardLeetCode {
    public static void main(String[] args) {
        // put your codes here
        HardLeetCode test = new HardLeetCode();
//        System.out.println(test.candy(new int[]{1}));
//        System.out.println(test.maximalRectangle(new String[] {"000", "000", "000"}));
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
}