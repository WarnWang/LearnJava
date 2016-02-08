package dsaaij;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by warn on 8/2/2016.
 */
public class TestIterators {
    public static void main(String[] args) {
        // put your codes here
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        Iterator<Integer> testIterators = test.iterator();
        while (testIterators.hasNext()) {
            int i = testIterators.next();
            if (i % 2 == 0) testIterators.remove();
        }

        System.out.print(test.toString());
    }
}