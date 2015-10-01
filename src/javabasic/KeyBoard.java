package javabasic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KeyBoard {
	public void keyboardScanner() {
		int num1;
		double num2;
		String name;
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter an integer: ");
		num1 = in.nextInt();
		System.out.print("Enter a floating point number: ");
		num2 = in.nextDouble();
		System.out.print("Enter your name: ");
		name = in.next();
		System.out.println("Hi! " + name + ", the sum of " + num1 + " and " + num2 + " is " + (num1 + num2));
		in.close();
	}
	
	public void fileScanner(String file_name) throws FileNotFoundException {
		int num1;
		double num2;
		String name;
		
		Scanner in = new Scanner(new File(file_name));
		num1 = in.nextInt();
		num2 = in.nextDouble();
		name = in.next();
		System.out.println("Hi! " + name + ", the sum of " + num1 + " and " + num2 + " is " + (num1 + num2));
		in.close();
	}
	
	public void circleComputation() {
		double radius;
		double area;
		double perimeter;
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the radius: ");
		radius = in.nextDouble();
		area = Math.PI * radius * radius;
		perimeter = 2 * Math.PI * radius;
		System.out.printf("The area is %f\nThe perimeter is %f", area, perimeter);
		in.close();
	}

	public static void main(String[] args) throws FileNotFoundException {
		KeyBoard test = new KeyBoard();
//		test.keyboardScanner();
//		test.fileScanner("./src/javabasic/in");
//		System.out.println(System.getProperty("user.dir"));
		test.circleComputation();
	}

}
