package LeetCode.Algorithm.Greedy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by warn on 4/10/2016.
 * store solution about queue
 */
public class Solution {

    /**
     * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
     * where h is the height of the person and k is the number of people in front of this person who have a height greater
     * than or equal to h. Write an algorithm to reconstruct the queue.
     * <p>
     * Note:
     * The number of people is less than 1,100.
     * <p>
     * Example
     * <p>
     * Input:
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     * <p>
     * Output:
     * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     *
     * @param people a random list of people
     * @return the reconstructed queue
     */
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return new int[0][0];

        Arrays.sort(people, (o1, o2) -> {
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            else {
                if (o1[1] != 0) return o2[0] - o1[0];
                return o1[0] - o2[0];
            }
        });

        ArrayList<int[]> arrayList = new ArrayList<>();

        for (int[] i: people) {
            if (i[1] > 0) {
                int n = i[1], m = arrayList.size();
                for (int j = 0; j < m; j++) {
                    if (arrayList.get(j)[0] >= i[0]) n--;
                    if (n == 0) {
                        arrayList.add(j + 1, i);
                        break;
                    }
                }
                if (m == arrayList.size()) arrayList.add(i);
            } else arrayList.add(i);
        }
        for (int i = 0; i < people.length; i++) {
            people[i] = arrayList.get(i);
        }
        return people;
    }

    public int[][] reconstructQueueBetter(int[][] people) {
        if (people == null || people.length == 0) return new int[0][0];

        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0]) return o2[0] - o1[0];
            else {
                return o1[1] - o2[1];
            }
        });

        ArrayList<int[]> arrayList = new ArrayList<>();

        for (int[] i: people) {
            arrayList.add(i[0], i);
        }
        return arrayList.toArray(new int[people.length][]);
    }
}
