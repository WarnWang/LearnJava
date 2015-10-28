package javabasic;

import java.util.Formatter;

public class Loop {
	public void sumAndAverage() {
		int sum = 0;
		double average;
		int upperBound = 8899;
		int lowerBound = 111;
		int count = 0;
		// for iteration
//		for (int i = lowerBound; i <= upperBound; i++) {
//			sum += i;
//			++count;
//		}
		
		// while iteration
		int i = lowerBound;
//		while (i <= upperBound) {
//			sum += i;
//			i++;
//			++count;
//		}
		
		//do-while iternation
		do {
			sum += i;
			i++;
			++count;
		} while (i <= upperBound);
		
		average = (double)sum / (upperBound - lowerBound + 1);
		System.out.println("sum: " + sum);
		System.out.println("average: " + average);
		System.out.println("Total number is: " + count);
	}
	
	public void harmonicSum(int maxDenominator) {
		double sumL2R = 0.0;
		double sumR2L = 0.0;
		
		for (int i = 1; i <= maxDenominator; i++) {
			sumL2R += 1.0 / i;
			sumR2L += 1.0 / (maxDenominator + 1 - i);
		}
		System.out.println(sumL2R - sumR2L);
	}
	
	public double computePI(long maxDenom) {
		double sum = 0;
		for (long i = 0; i < maxDenom; i++) {
			if (i % 4 == 1) {
				sum += 1.0 / i;
			} else if (i % 4 == 3) {
				sum -= 1.0 / i;
			} else {}
		}
		double pi = 4 * sum;
		System.out.println("PI is " + pi);
		return pi;
	}
	
	public void cozaLozaWoza(int number) {
		String temp = "";
		for (int i = 1; i <= number; i++) {
			boolean flag = false;
			if(i % 3 == 0){
				temp += "Coza";
				flag = true;
			}
			if (i % 5 == 0) {
				temp += "Loza";
				flag = true;
			}
			if (i % 7 == 0) {
				temp += "Woza";
				flag = true;
			}
			if (!flag) {
				temp += i;
			}
			if (i % 11 == 0) {
				temp += '\n';
			} else {
				temp += " ";
			}
		}
		System.out.println(temp);
	}
	
	public void timeTable(int times) {
		String temp = " * |";
		Formatter f = new Formatter();
		
		for (int i = 1; i <= times; i++) {
			f.format("%4d", i);
		}
		System.out.println(temp += f.toString());
		f.close();
		
		temp = "----";
		for (int i = 1; i <= times; i++) {
			temp += "----";
		}
		
		System.out.println(temp);
		for (int i = 1; i <= times; i++) {
			f = new Formatter();
			f.format("%2d |", i);
			for (int j = 1; j <= times; j++) {
				f.format("%4d", i * j);
			}
			System.out.println(f.toString());
			f.close();
		}
	}

	public static void main(String[] args) {
		Loop test = new Loop();
		test.timeTable(4);
//		Formatter f = new Formatter();
//		System.out.println(f.format("%6.3f", 3.1415926));
//		String s = f.toString();
//		System.out.println(s);
	}

}
