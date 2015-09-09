package ch6;

public class CH6_4 {

	public static void main(String[] args) {
		int[][] matrix = {{1, 2, 3,}, {4, 5, 6}, {7, 8, 9}};
		showMatrix(matrix);
		transpose(matrix);
		showMatrix(matrix);
	}
	
	public static void transpose(int[][] matrix) {
		int[][] newMartrix = new int[matrix[0].length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				newMartrix[j][i] = matrix[i][j];
			}
		}
		showMatrix(newMartrix);
		matrix = newMartrix;
		showMatrix(matrix);
	}
	
	public static void showMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is) {
				System.out.print(i + " ");
			}
			System.out.print('\n');
		}
		System.out.print('\n');
	}

}
