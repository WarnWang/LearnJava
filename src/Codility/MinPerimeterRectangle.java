package Codility;

/**
 * Created by warn on 29/1/2016.
 */
public class MinPerimeterRectangle {
    public static void main(String[] args) {
        // put your codes here
        MinPerimeterRectangle test = new MinPerimeterRectangle();
        System.out.println(test.solution(1000000000));
    }

    public int solution(int N) {
        int i, sqrtN;
        sqrtN = (int) Math.sqrt(N);
        for (i = sqrtN; i > 0; i--) {
            if (N % i == 0) break;
        }
        return 2 * (N / i + i);
    }
}