package javabasic;

import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayExercise {
	
	public void gradesAverage() {
		int[] gradesRecord;
		int numOfStud;
		int gradeSum = 0;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number of students: ");
		while (true) {
			
			try {
				numOfStud = in.nextInt();
				break;
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid num, try again...");
			}
		}
		
		gradesRecord = new int[numOfStud];
		
		for (int i = 0; i < numOfStud; i++) {
			while (true) {
				System.out.printf("Enter the grade for student %d: ", i+1);
				try {
					gradesRecord[i] = in.nextInt();
					if (gradesRecord[i] < 0 || gradesRecord[i] > 100) {
						System.out.println("Invalid grade, try again...");
					} else {
						gradeSum += gradesRecord[i];
						break;
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid grade, try again...");
				}
			}
		}
		
		System.out.printf("The average is %.1f", gradeSum / (double) numOfStud);
		in.close();
	}
	
	public void hex2Bin() throws NumberFormatException{
		Scanner in = new Scanner(System.in);
		String hexNum;
		String[] hexBits;
		Formatter f = new Formatter();
		
		System.out.print("Enter a Hexadecimal string: ");
		hexNum = in.next();
		hexBits = new String[hexNum.length()];
		
		System.out.printf("The equivalent binary for hexadecimal \"%s\" is ", hexNum);
		
		for (int i = 0; i < hexNum.length(); i++) {
			int temp = Integer.parseInt(Character.toString(hexNum.charAt(i)), 16);
			hexBits[i] = Integer.toBinaryString(temp);
			System.out.printf("%04d ", temp);
		}
		
		f.close();
		in.close();
	}

	public static void main(String[] args) {
		ArrayExercise test = new ArrayExercise();
		test.hex2Bin();

	}

}
