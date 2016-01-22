package Codility;

/**
 * Created by warn on 21/1/2016.
 */
public class CountDiv {
    public static void main(String[] args) {
        // put your codes here
        CountDiv test = new CountDiv();
        System.out.print(test.solution(11, 345, 17));
    }

    public int solution(int A, int B, int K) {
        // write your code in Java SE 8
        int result;
        if (A < K && A > 0) {
            result = B / K;
        } else {
            result = (B - A) / K;
        }
        if (A % K == 0 || B % K == 0) {
            result++;
        }
//        result = 0;
//        for (int i = A; i<= B; i++) {
//            if (i % K == 0){
//                System.out.println(i);
//                result++;
//            }
//        }
        return result;
    }
}