package LeetCode.DesignPuzzles;

import LeetCode.DataTypes.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by warn on 6/9/2016.
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list
 * of disjoint intervals.
 * <p>
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 * <p>
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 */
public class SummaryRanges {
    /**
     * Initialize your data structure here.
     */

    private ArrayList<Integer> numberRecord;

    public SummaryRanges() {
        numberRecord = new ArrayList<>();
    }

    public void addNum(int val) {
        addNum(val, 0, numberRecord.size());
    }

    private void addNum(int val, int startIndex, int endIndex) {
        if (numberRecord.isEmpty()) numberRecord.add(val);
        else if (startIndex == endIndex - 1) {
            if (numberRecord.get(startIndex) > val) numberRecord.add(startIndex, val);
            else if (numberRecord.get(startIndex) < val) numberRecord.add(startIndex + 1, val);
        } else {
            int start = numberRecord.get(startIndex);
            int end = numberRecord.get(endIndex - 1);
            if (start > val) {
                numberRecord.add(startIndex, val);
                return;
            } else if (end < val) {
                numberRecord.add(endIndex, val);
                return;
            }
            int middleIndex = (startIndex + endIndex) / 2;
            int middle = numberRecord.get(middleIndex);
            if (start == val || end == val || middle == val) return;
            if (middle > val) addNum(val, startIndex, middleIndex);
            else addNum(val, middleIndex, endIndex);
        }
    }

    public List<Interval> getIntervals() {
        ArrayList<Interval> result = new ArrayList<>();
        if (numberRecord.isEmpty()) return result;
        Interval temp = new Interval();
        temp.start = numberRecord.get(0);
        for (int i = 1, n = numberRecord.size(); i < n; i++) {
            if (numberRecord.get(i) - numberRecord.get(i - 1) == 1) {
                temp.end = numberRecord.get(i);
            } else {
                if (temp.end == 0 && temp.start != 0) temp.end = temp.start;
                result.add(temp);
                temp = new Interval();
                temp.start = numberRecord.get(i);
            }
        }
        System.out.println(numberRecord.toString());
        if (temp.end == 0 && temp.start != 0) temp.end = temp.start;
        result.add(temp);
        return result;
    }
}
