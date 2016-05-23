package LeetCode;

/**
 * Created by warn on 23/5/2016.
 * Use to solve those question with tag Bit Manipulation
 */
public class TagBitManipulation {
    /**
     * Reverse bits of a given 32 bits unsigned integer.
     * <p>
     * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192
     * (represented in binary as 00111001011110000010100101000000).
     *
     * @param n an 32-bit unsigned integer
     * @return the value of its reverse integer
     */
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 0) result <<= 1 ;
            else result = (result << 1) | 1;
            n >>= 1;
        }
        return result;
    }
}
