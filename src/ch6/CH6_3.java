package ch6;

import java.util.Arrays;

public class CH6_3 {

	public static void main(String[] args) {
		String arr1[] = {"a", "cc", "ef", "wyf"};
		showArray(arr1);
		replaceString(arr1.clone());
		showArray(arr1);

	}
	
	public static void replaceString(String[] arr) {
		Arrays.fill(arr, 2, 3, "bb");
		showArray(arr);
	}
	
	public static void showArray(String[] arr) {
		for (String i : arr) {
			System.out.print(i + " ");
		}
		System.out.print('\n');
	}

}
