package javabasic;

import java.util.Random;

/**
 * Created by warn on 22/1/2016.
 */
public class TestRandom {
    public static void main(String[] args) {
        // put your codes here
        int[] container = new int[10];
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            container[random.nextInt(9)]++;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(container[i]);
        }
    }
}