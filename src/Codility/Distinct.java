package Codility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by warn on 23/1/2016.
 */
public class Distinct {
    public static void main(String[] args) {
        // put your codes here
        Distinct test = new Distinct();
        System.out.println(test.solution(new int[]{2, 1, 1, 2, 3, 1}));
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        Map<Integer, Integer> numberCount = new HashMap<>();
        for (int i : A) {
            if (numberCount.containsKey(i)) numberCount.put(i, 1 + numberCount.get(i));
            else numberCount.put(i, 1);
        }
        return numberCount.size();
    }
}