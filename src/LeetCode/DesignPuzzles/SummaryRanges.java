package LeetCode.DesignPuzzles;

import LeetCode.DataTypes.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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

    private ArrayList<Interval> intervalList;
    private HashSet<Integer> existNum;

    public SummaryRanges() {
        intervalList = new ArrayList<>();
        existNum = new HashSet<>();
    }

    public void addNum(int val) {
        if (!existNum.contains(val)) {
            intervalList.add(new Interval(val, val));
            existNum.add(val);
        }
    }

    public List<Interval> getIntervals() {
        ArrayList<Interval> result = new ArrayList<>();
        if (intervalList.isEmpty()) return result;

        Collections.sort(intervalList, (o1, o2) -> o1.start - o2.start);

        int currentStart = intervalList.get(0).start;
        int currentEnd = intervalList.get(0).end;

        for (int i = 1, n = intervalList.size(); i < n; i++) {
            if (currentEnd + 1 == intervalList.get(i).start) {
                currentEnd = intervalList.get(i).end;
            } else {
                result.add(new Interval(currentStart, currentEnd));
                currentEnd = intervalList.get(i).end;
                currentStart = intervalList.get(i).start;
            }
        }
        result.add(new Interval(currentStart, currentEnd));
        intervalList = result;
        return result;
    }
}
