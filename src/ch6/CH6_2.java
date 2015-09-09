package ch6;

public class CH6_2 {

	public static void main(String[] args) {
		int[] test = {5, 3, 4, -11, 6};
		System.out.println(findMin(test));
	}
	
	public static int findMin(int[] arr1) {
		int min = arr1[0];
		for (int i : arr1) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	}

}
