package javabasic;

public class MoreExercise {
	public static void printPatternA(int size) {
		for (int i = 0; i < size; i++) {
			String s = "";
			for (int j = 0; j < i + 1; j++) {
				s += "# ";
			}
			System.out.println(s);
		}
	}
	
	public static void printPatternB(int size) {
		for (int i = size; i > 0; i--) {
			String s = "";
			for (int j = 0; j < i; j++) {
				s += "# ";
			}
			System.out.println(s);
		}
	}
	
	public static void printPatternC(int size) {
		for (int i = 0; i < size; i++) {
			String s = "";
			for (int j = 0; j < size; j++) {
				if (j >= i) {
					s += "# ";
				} else {
					s += "  ";
				}
			}
			System.out.println(s);
		}
	}
	
	public static void printPatternD(int size) {
		for (int i = 0; i < size; i++) {
			String s = "";
			for (int j = 0; j < size; j++) {
				if (j <= i) {
					s += "# ";
				} else {
					s += "  ";
				}
			}
			System.out.println(s);
		}
	}
	
	public static void printPatternE(int size) {
		for (int i = 0; i < size; i++) {
			String s = "";
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == size - 1) {
					s += "# ";
				} else {
					if (j == 0 || j == size - 1) {
						s += "# ";
					} else {
						s += "  ";
					}
				} 
			}
			System.out.println(s);
		}
	}
	
	public static void printPatternF(int size) {
		for (int i = 0; i < size; i++) {
			String s = "";
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == size - 1) {
					s += "# ";
				} else {
					if (j == i) {
						s += "# ";
					} else {
						s += "  ";
					}
				} 
			}
			System.out.println(s);
		}
	}
	
	public static void printPatternG(int size) {
		for (int i = 0; i < size; i++) {
			String s = "";
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == size - 1) {
					s += "# ";
				} else {
					if (j == size - i - 1) {
						s += "# ";
					} else {
						s += "  ";
					}
				} 
			}
			System.out.println(s);
		}
	}
	
	public static void printPatternH(int size) {
		for (int i = 0; i < size; i++) {
			String s = "";
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == size - 1) {
					s += "# ";
				} else {
					if (j == size - i - 1 || j == i) {
						s += "# ";
					} else {
						s += "  ";
					}
				} 
			}
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		printPatternH(9);
	}

}
