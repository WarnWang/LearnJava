package LeetCode;

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
        int offsetX = 0, offsetY = 0;
        int area;
        for (int i = x + 1; i < matrix.length; i++) {
            if (matrix[i][y] == '0') break;
            offsetX++;
        }

        for (int i = y + 1; i < matrix[0].length; i++) {
            if (matrix[x][i] == '0') break;
            offsetY++;
        }

        area = offsetX + 1;
        for (int i = 1; i <= offsetY; i++) {
            int areaCount = 1;
            for (int j = 1; j <= offsetX; j++) {
                if (matrix[x + j][i + y] == '0') {
                    offsetX = j - 1;
                    break;
                }
                areaCount++;
            }
            int tempArea = areaCount * (i + 1);
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
}