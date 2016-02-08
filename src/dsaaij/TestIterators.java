package dsaaij;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by warn on 8/2/2016.
 */
public class TestIterators {
    public static void main(String[] args) {
        // put your codes here
        ArrayList<Integer> test = new ArrayList<>();
        LinkedList<Integer> test2 = new LinkedList<>();
        test2.push(1);
        test2.push(2);
        test2.push(3);
        test2.push(4);
        test2.push(5);
        ListIterator<Integer> testIterators = test2.listIterator();
        while (testIterators.hasNext()) {
            int i = testIterators.next();
            if (i % 2 == 0) testIterators.add(4);
        }

        System.out.print(test2.toString());
    }
}