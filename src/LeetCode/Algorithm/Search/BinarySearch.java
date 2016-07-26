package LeetCode.Algorithm.Search;

/**
 * Created by warn on 16/7/2016.
 * Store solutions to question with tag binary search
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch test = new BinarySearch();
        System.out.println(test.getMoneyAmount(11));
        System.out.println(test.getMoneyAmount(14));
        System.out.println(test.getMoneyAmount(8));
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
     * We are playing the Guess Game. The game is as follows:
     * <p>
     * I pick a number from 1 to n. You have to guess which number I picked.
     * <p>
     * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
     * <p>
     * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess
     * the number I picked.
     * <p>
     * Example:
     * <p>
     * n = 10, I pick 8.
     * <p>
     * First round:  You guess 5, I tell you that it's higher. You pay $5.
     * Second round: You guess 7, I tell you that it's higher. You pay $7.
     * Third round:  You guess 9, I tell you that it's lower. You pay $9.
     * <p>
     * Game over. 8 is the number I picked.
     * <p>
     * You end up paying $5 + $7 + $9 = $21.
     * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
     *
     * @param n the maximum of guess number
     * @return the money you should pay.
     */
    public int getMoneyAmount(int n) {
        if (n <= 2) return n - 1;
        moneyMatrix = new int[n + 1][n + 1];
        return getSubMoney(1, n);
    }

    private int getSubMoney(int i, int j) {
        if (i == j) return 0;
        if (j - i == 1) moneyMatrix[i][j] = i;
        if (moneyMatrix[i][j] > 0) return moneyMatrix[i][j];
        int minMoney = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            minMoney = Math.min(minMoney, k + Math.max(getSubMoney(i, k - 1), getSubMoney(k + 1, j)));
        }
        moneyMatrix[i][j] = minMoney;
        return minMoney;
    }

    private int[][] moneyMatrix;
}
