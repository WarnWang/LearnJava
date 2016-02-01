package Codility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by warn on 22/1/2016.
 */
public class GenomicRangeQuery {
    public static void main(String[] args) {
        // put your codes here
        GenomicRangeQuery test = new GenomicRangeQuery();
        test.solution("GT", new int[]{0, 0, 1}, new int[]{0, 1, 1});
    }

    public int[] solution(String S, int[] P, int[] Q) {
        // write your code in Java SE 8
        int[] result = new int[P.length];
        Map<Character, Integer> nucleotidesType = new HashMap<>();
        nucleotidesType.put('A', 0);
        nucleotidesType.put('C', 1);
        nucleotidesType.put('G', 2);
        nucleotidesType.put('T', 3);
        int[][] stringCount = new int[S.length()][4];
        for (int i = 0; i < S.length(); i++) {
            char temp = S.charAt(i);
            int[] count = new int[4];
            if (i != 0) {
                count = stringCount[i - 1].clone();
            }
            count[nucleotidesType.get(temp)]++;
            stringCount[i] = count.clone();
        }

        for (int i = 0; i < P.length; i++) {
            int p = P[i] == 0 ? -1 : P[i] - 1;
            int q = Q[i];
            int[] pCount = p >= 0 ? stringCount[p] : new int[4];
            int[] qCount = stringCount[q];
            for (int j = 0; j < pCount.length; j++) {
                if (qCount[j] - pCount[j] > 0) {
                    result[i] = j + 1;
                    break;
                }
            }
        }

        return result;
    }
}