package ch6;

import java.util.Arrays;

public class CH6_1 {

	public static void main(String[] args) {
		char[] arr1 = {'h', 'f', 'c', 'd', 'e', 'h'};
		char[] arr2 = Arrays.copyOfRange(arr1, 0, 3);
		System.out.println(arr1);
		System.out.println(arr2);

	}

}
