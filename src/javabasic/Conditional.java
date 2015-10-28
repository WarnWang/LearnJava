package javabasic;

public class Conditional {
	public void checkPassFail(int mark) {
		System.out.println("The mark is " + mark);
		if (mark >= 50) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	public void checkOddEven(int number) {
		System.out.println("The number is " + number);
		if (number % 2 == 0) {
			System.out.println("Even number");
		} else {
			System.out.println("Odd number");
		}
	}
	
	public void printNumberInWord(int number) {
		System.out.println("The number is " + number);
		switch (number) {
		case 1: System.out.println("ONE"); break;
		case 2: System.out.println("TWO"); break;
		case 3: System.out.println("THREE"); break;
		case 4: System.out.println("FOUR"); break;
		case 5: System.out.println("FIVE"); break;
		case 6: System.out.println("SIX"); break;
		case 7: System.out.println("SEVEN"); break;
		case 8: System.out.println("EIGHT"); break;
		case 9: System.out.println("NINE"); break;
		
		default: System.out.println("OTHER");
			break;
		}
	}

	public static void main(String[] args) {
		Conditional test = new Conditional();
		test.printNumberInWord(18);

	}

}
