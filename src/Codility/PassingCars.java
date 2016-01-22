package Codility;

/**
 * Created by warn on 21/1/2016.
 */
public class PassingCars {
    public static void main(String[] args) {
        // put your codes here
        PassingCars test = new PassingCars();
        System.out.print(test.solution(new int[]{0, 1, 0, 0, 1, 1, 0}));
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int n = A.length;
        int result = 0;
        int maxResult = 1000000000;
        int nOfTravelingWest = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (A[i] == 1) {
                nOfTravelingWest++;
            } else {
                result += nOfTravelingWest;
            }

            if (result > maxResult) {
                result = -1;
                break;
            }
        }
        return result;
    }
}