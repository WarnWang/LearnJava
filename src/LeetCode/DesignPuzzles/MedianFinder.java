package LeetCode.DesignPuzzles;

import java.util.PriorityQueue;

/**
 * Created by warn on 9/9/2016.
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 * <p>
 * Examples:
 * [2,3,4] , the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * For example:
 * <p>
 * add(1)
 * add(2)
 * findMedian() -> 1.5
 * add(3)
 * findMedian() -> 2
 */
public class MedianFinder {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);

    public static void main(String args[]) {
        MedianFinder test = new MedianFinder();
        test.addNum(-1);
        test.addNum(-2);
        System.out.println(test.findMedian());
        test.addNum(-3);
        System.out.println(test.findMedian());
        test.addNum(-4);
        System.out.println(test.findMedian());
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        double median = findMedian();
        if (num > median) maxHeap.add(num);
        else minHeap.add(num);

        int n1 = minHeap.size();
        int n2 = maxHeap.size();
        if (n1 < n2) minHeap.offer(maxHeap.poll());
        else if (n1 - n2 == 2) maxHeap.offer(minHeap.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        int n1 = minHeap.size();
        int n2 = maxHeap.size();
        if (n1 == 0 && n2 == 0) return 0;
        if (n1 > n2) return minHeap.peek();
        else return (double) (minHeap.peek() + maxHeap.peek()) / 2;
    }
}
