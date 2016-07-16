package LeetCode.Algorithm.Search;

/**
 * Created by warn on 16/7/2016.
 * Store solutions to question with tag binary search
 */
public class BinarySearch {
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
}
