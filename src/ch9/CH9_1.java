package ch9;

import java.util.Random;

public class CH9_1 {
	public static void main(String[] args){
		Random rand = new Random();
		int sum = 0;
		for (int i = 0; i < 6; i++) {
			int s = rand.nextInt(15);
			sum += (2 * s + 2);
			System.out.println(2 * s + 4);
		}
		System.out.println(sum);
	}

}
