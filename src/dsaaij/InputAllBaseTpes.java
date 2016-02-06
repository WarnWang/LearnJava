package dsaaij;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by warn on 6/2/2016.
 */
public class InputAllBaseTpes {
    public static void main(String[] args) {
        // put your codes here
//        Scanner test = new Scanner(System.in);
//        while (test.hasNext()) {
//            if (test.hasNextInt()) {
//                System.out.println("Integer" +test.nextInt());
//            } else if (test.hasNextBoolean()) {
//                System.out.println("boolean" + test.nextBoolean());
//            } else {
//                System.out.println(test.next());
//            }
//        }

        StringBuffer newTest = new StringBuffer("Let's try, Mike!");
        Pattern p = Pattern.compile("[,'!]*");
        Matcher m = p.matcher(newTest);
        System.out.println(m.replaceAll(""));
    }
}