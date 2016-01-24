package Codility;

import java.util.Stack;

/**
 * Created by warn on 24/1/2016.
 * <p>
 * You are going to build a stone wall. The wall should be straight and N meters long, and its thickness should be constant; however, it should have different heights in different places. The height of the wall is specified by a zero-indexed array H of N positive integers. H[I] is the height of the wall from I to I+1 meters to the right of its left end. In particular, H[0] is the height of the wall's left end and H[N−1] is the height of the wall's right end.
 * <p>
 * The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular). Your task is to compute the minimum number of blocks needed to build the wall.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] H); }
 * <p>
 * that, given a zero-indexed array H of N positive integers specifying the height of the wall, returns the minimum number of blocks needed to build it.
 * <p>
 * For example, given array H containing N = 9 integers:
 * <p>
 * H[0] = 8    H[1] = 8    H[2] = 5
 * H[3] = 7    H[4] = 9    H[5] = 8
 * H[6] = 7    H[7] = 4    H[8] = 8
 * the function should return 7. The figure shows one possible arrangement of seven blocks.
 * <p>
 * <p>
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..100,000];
 * each element of array H is an integer within the range [1..1,000,000,000].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class StoneWall {
    public static void main(String[] args) {
        // put your codes here
        StoneWall test = new StoneWall();
        System.out.println(test.solution(new int[]{8, 8, 5, 7, 9, 8, 7, 4, 8}));
    }

    public int solution(int[] H) {
        // write your code in Java SE 8
        int n = H.length;
        if (n == 0) return 0;
        int blockNumber = 1;
        Stack<Integer> higherBlock = new Stack<>();
        higherBlock.push(H[0]);
        for (int i = 1; i < n; i++) {
            if (H[i] < H[i - 1]) {
                while (higherBlock.size() > 0) {
                    int temp = higherBlock.pop();
                    if (temp > H[i]) {
                        blockNumber++;
                    } else if (temp == H[i]) {
                        higherBlock.push(temp);
                        break;
                    } else if (temp < H[i]) {
                        higherBlock.push(temp);
                        higherBlock.push(H[i]);
                        break;
                    }
                }
                if (higherBlock.size() == 0) {
                    higherBlock.push(H[i]);
                }
            } else if (H[i] > H[i - 1]) {
                higherBlock.push(H[i]);
            }
        }
        return blockNumber + higherBlock.size() - 1;
    }
}