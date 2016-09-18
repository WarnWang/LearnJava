package LeetCode.WeeklyContest.Contest5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by warn on 18/9/2016.A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on
 * the bottom represent the minutes (0-59).
 * <p>
 * Each LED represents a zero or one, with the least significant bit on the right.
 * <p>
 * <p>
 * For example, the above binary watch reads "3:25".
 * <p>
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times
 * the watch could represent.
 * <p>
 * Example:
 * <p>
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * Note:
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should
 * be "10:02".
 */
public class BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    String hour = Integer.toString(i);
                    String minute = String.format("%2s", Integer.toString(j)).replace(' ', '0');
                    result.add(hour + ":" + minute);
                }
            }
        }
        return result;
    }
}
