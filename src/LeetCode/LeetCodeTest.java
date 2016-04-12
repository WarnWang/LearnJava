package LeetCode;

/**
 * Created by warn on 4/4/16.
 */
public class LeetCodeTest {
    private static void testDFS() {
        TagDFS test;
        test = new TagDFS();
        System.out.println(test.partition("baabc").toString());
    }

    private static void testMath(){
        TagMath test = new TagMath();
        System.out.print(test.calculate("(((((8 - 12))))) +3"));
    }

    public static void main(String[] args) {
        testMath();
    }
}
