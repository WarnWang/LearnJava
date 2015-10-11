package javabasic;

public class Matrix {
	public static void printMarix(int[][] m) {
		for (int[] is : m) {
			for (int i : is) {
				System.out.print(i + " ");
			}
			System.out.print("%n");
		}
	}
	
	public static void printMatrx(double[][] m) {
		for (double[] ds : m) {
			for (double d : ds) {
				System.out.print(d + " ");
			}
			System.out.print("%n");
		}
	}
	
	public static boolean haveSameDimension(int[][] m1, int[][] m2) {
		boolean result = false;
		if (m1.length == 0 && m2.length == 0) {
			result = true;
		}else if (m1.length == m2.length && m1[0].length == m2[0].length) {
			result = true;
		}
		return result;
	}

	public static boolean haveSameDimension(double[][] m1, double[][] m2) {
		boolean result = false;
		if (m1.length == 0 && m2.length == 0) {
			result = true;
		}else if (m1.length == m2.length && m1[0].length == m2[0].length) {
			result = true;
		}
		return result;
	}
	
	public static int[][] add(int[][] m1, int[][] m2) {
		int[][] result = new int[(m1.length > m2.length) ? m1.length : m2.length]
						 [(m1[0].length > m2[0].length) ? m1[0].length : m2[0].length];
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				if (i > m1.length || j > m1[i].length) {
					result[i][j] = m2[i][j];
				} else if (i > m2.length || j > m2[i].length) {
					result[i][j] = m1[i][j];
				} else {
					result[i][j] = m1[i][j] + m2[i][j];
				}
			}
		}
		
		return result;
	}
	
	public static double[][] add(double[][] m1, double[][] m2) {
		double[][] result = new double[(m1.length > m2.length) ? m1.length : m2.length]
						 [(m1[0].length > m2[0].length) ? m1[0].length : m2[0].length];
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				if (i > m1.length || j > m1[i].length) {
					result[i][j] = m2[i][j];
				} else if (i > m2.length || j > m2[i].length) {
					result[i][j] = m1[i][j];
				} else {
					result[i][j] = m1[i][j] + m2[i][j];
				}
			}
		}
		
		return result;
	}
	
	public static int[][] subtraction(int[][] m1, int[][] m2) {
		int[][] result = new int[(m1.length > m2.length) ? m1.length : m2.length]
				[(m1[0].length > m2[0].length) ? m1[0].length : m2[0].length];
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				if (i > m1.length || j > m1[i].length) {
					result[i][j] = -m2[i][j];
				} else if (i > m2.length || j > m2[i].length) {
					result[i][j] = m1[i][j];
				} else {
					result[i][j] = m1[i][j] - m2[i][j];
				}
			}
		}
		
		return result;
	}
	
	public static double[][] subtraction(double[][] m1, double[][] m2) {
		double[][] result = new double[(m1.length > m2.length) ? m1.length : m2.length]
						 [(m1[0].length > m2[0].length) ? m1[0].length : m2[0].length];
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				if (i > m1.length || j > m1[i].length) {
					result[i][j] = -m2[i][j];
				} else if (i > m2.length || j > m2[i].length) {
					result[i][j] = m1[i][j];
				} else {
					result[i][j] = m1[i][j] - m2[i][j];
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[][] a = new int[4][5];
		int[][] b = new int[4][5];

		System.out.println(haveSameDimension(a, b));
	}

}
