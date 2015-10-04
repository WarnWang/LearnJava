package javabasic;

import java.util.Scanner;
import java.util.function.BinaryOperator;

public class StringOperation {
	public void ReserveString() {
		Scanner in = new Scanner(System.in);
		String temp, reseTemp = "";
		
		System.out.print("Enter a String: ");
		temp = in.next();
		for (int i = 0; i < temp.length(); i++) {
			reseTemp += temp.charAt(temp.length() - i - 1);
		}
		System.out.printf("The reverse String \"%s\" is \"%s\"", temp, reseTemp);
		in.close();
	}
	
	public void phoneKeyPad() {
		Scanner in = new Scanner(System.in);
		String temp;
		String result = "";
		
		System.out.print("Enter a Sting: ");
		temp = in.next().toLowerCase();
		for (int i = 0; i < temp.length(); i++) {
			switch (temp.charAt(i)) {
			case 'a':
			case 'b':
			case 'c':
				result += 2;
				break;
				
			case 'e':
			case 'd':
			case 'f':
				result += 3;
				break;
				
			case 'g':
			case 'h':
			case 'i':
				result += 4;
				break;
				
			case 'j':
			case 'k':
			case 'l':
				result += 5;
				break;
				
			case 'm':
			case 'n':
			case 'o':
				result += 6;
				break;
				
			case 'p':
			case 'q':
			case 'r':
			case 's':
				result += 7;
				break;
				
			case 't':
			case 'u':
			case 'v':
				result += 8;
				break;
				
			case 'w':
			case 'x':
			case 'y':
			case 'z':
				result += 9;
				break;

			default:
				result += temp.charAt(i);
				break;
			}
		}
		System.out.printf("The phone key pad of \"%s\" is \"%s\".", temp, result);
		
		in.close();
	}
	
	public void testPalindromicWord(String worldToTest) {
		Boolean result = true;
		int frontIndex = 0;
		int backIndex = worldToTest.length() - 1;
		String test = worldToTest.toLowerCase();
		
		while (backIndex - frontIndex > 1) {
			while (!Character.isAlphabetic(test.charAt(backIndex))) {
				backIndex--;
			}
			while (!Character.isAlphabetic(test.charAt(frontIndex))) {
				frontIndex++;
			}
			if (backIndex - frontIndex <= 1) {
				break;
			}
			if (test.charAt(backIndex) != test.charAt(frontIndex)){
				result = false;
				break;
			}
			backIndex--;
			frontIndex++;
		}
		if (result) {
			System.out.printf("\"%s\" is palindromic", worldToTest);
		} else {
			System.out.printf("\"%s\" is not palindromic", worldToTest);
		}
	}
	
	public void bin2Dec(String binNum) {
		int Dec;
		try {
			Dec = Integer.parseInt(binNum, 2);
		} catch (NumberFormatException e) {
			System.err.printf("Error: Invalid Hexadecimal String \"%s\"", binNum);
			return;
		} 
		System.out.printf("The equivalent decimal number for hexadecimal \"%s\" is %d", binNum, Dec);
	}
	
	public void hex2Dec(String hexNum) {
		int Dec;
		try {
			Dec = Integer.parseInt(hexNum, 16);
		} catch (NumberFormatException e) {
			System.err.printf("Error: Invalid Binary String \"%s\"", hexNum);
			return;
		} 
		System.out.printf("The equivalent decimal number for binary \"%s\" is %d", hexNum, Dec);
	}

	public static void main(String[] args) {
		StringOperation test = new StringOperation();
//		test.ReserveString();
//		test.phoneKeyPad();
//		test.testPalindromicWord("A man, a plan, a canaa1l - Panama!");
		test.hex2Dec("1abcd2w1");
//		test.bin2Dec("10121");

	}

}
