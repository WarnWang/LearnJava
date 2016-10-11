package CodeFestival2016.QualB;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by warn on 10/10/2016.
 * Problem Statement
 * There are N participants in the CODE FESTIVAL 2016 Qualification contests. The participants are either students in
 * Japan, students from overseas, or neither of these.
 * <p>
 * Only Japanese students or overseas students can pass the Qualification contests. The students pass when they satisfy
 * the conditions listed below, from the top rank down. Participants who are not students cannot pass the Qualification
 * contests.
 * <p>
 * A Japanese student passes the Qualification contests if the number of the participants who have already definitively
 * passed is currently fewer than A+B.
 * An overseas student passes the Qualification contests if the number of the participants who have already definitively
 * passed is currently fewer than A+B and the student ranks B-th or above among all overseas students.
 * A string S is assigned indicating attributes of all participants. If the i-th character of string S is a, this means
 * the participant ranked i-th in the Qualification contests is a Japanese student; b means the participant ranked i-th
 * is an overseas student; and c means the participant ranked i-th is neither of these.
 * <p>
 * Write a program that outputs for all the participants in descending rank either Yes if they passed the Qualification
 * contests or No if they did not pass.
 * <p>
 * Constraints
 * 1≦N,A,B≦100000
 * A+B≦N
 * S is N characters long.
 * S consists only of the letters a, b and c.
 * Input
 * Inputs are provided from Standard Input in the following form.
 * <p>
 * N A B
 * S
 * Output
 * Output N lines. On the i-th line, output Yes if the i-th participant passed the Qualification contests or No if that
 * participant did not pass.
 */
public class QualificationSimulator {
    public static void main(String[] args) throws IOException {
        Scanner dis = new Scanner(System.in);
        int n = dis.nextInt(), a = dis.nextInt(), b = dis.nextInt();
        String s = dis.next();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                a--;
                if (a + b >= 0) System.out.println("Yes");
                else System.out.println("No");
            } else if (c == 'b') {
                b--;
                if (a + b >= 0 && b >= 0) System.out.println("Yes");
                else System.out.println("No");
            } else System.out.println("No");
        }
    }
}
