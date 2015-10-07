package javabasic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Method {
	public static int[] grades;
	
	public static int[] bins = new int[10];
	
	public static void readGrades() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number of students: ");
		grades = new int[in.nextInt()];
		
		for (int i = 0; i < grades.length; i++) {
			System.out.printf("Enter the grade for student %d: ", i + 1);
			grades[i] = in.nextInt();
		}
		
		in.close();
	}
	
	public static void readGradesFromFile(String filename) throws FileNotFoundException{
		Scanner in = new Scanner(new File(filename));
		int stdNum = in.nextInt();
		grades = new int[stdNum];
		
		for (int i = 0; i < grades.length; i++) {
			grades[i] = in.nextInt();
//			if (grades[i] < 100) {
//				bins[grades[i] / 10] += 1;
//			} else {
//				bins[9] += 1;
//			}
		}
		
		in.close();
	}
	
	public static void computeHistogram() {
		for (int i = 0; i < grades.length; i++) {
			if (grades[i] < 100) {
				bins[grades[i] / 10] += 1;
			} else {
				bins[9] += 1;
			}
		}
	}
	
	public static void printHistogramHorizontal() {
		for (int i = 0; i < bins.length; i++) {
			int min = i * 10;
			int max = i < 9 ? (i * 10 + 9) : (i * 10 + 10);
			String star = "";
			for (int j = 0; j < bins[i]; j++) {
				star += "*";
			}
			System.out.printf("%2d -%3d: %s%n", min, max, star);
		}
	}
	
	public static void printHistogramVertical() {
		int maxRows = IntStream.of(bins).max().getAsInt();
		for (int i = 0; i < maxRows; i++) {
			String temp = "";
			for (int j = 0; j < bins.length; j++) {
				if (bins[j] - maxRows + i >= 0) {
					temp += "  *   ";
				} else {
					temp += "      ";
				}
			}
			System.out.println(temp);
		}
		System.out.println(" 0-9  10-19 20-29 30-39 40-49 50-59 60-69 70-79 80-89 90-100");
	}
	
	public static double average() {
		return IntStream.of(grades).sum() / (double) grades.length;
	}
	
	public static int min() {
		return IntStream.of(grades).min().getAsInt();
	}
	
	public static int max() {
		return IntStream.of(grades).max().getAsInt();
	}
	
	public static double stdDev() {
		double average = average();
		double norDev;
		int sqSum = 0;
		
		for (int i = 0; i < grades.length; i++) {
			sqSum += grades[i] * grades[i];
		}
		norDev = (double) sqSum / grades.length - average * average;
		return Math.sqrt(norDev);
	}
	
	public void gradesStatistics() {
		readGrades();
		System.out.println("The average is " + average());
		System.out.println("The minimum is " + min());
		System.out.println("The maximum is " + max());
		System.out.println("The standard deviation is " + stdDev());
	}
	
	public void gradesHistogram () throws FileNotFoundException {
		readGradesFromFile("./src/javabasic/grades.in");
		computeHistogram();
		printHistogramHorizontal();
		printHistogramVertical();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Method test = new Method();
//		test.gradesStatistics();
		test.gradesHistogram();
	}

}
