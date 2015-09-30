package javabasic;

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

	public static void main(String[] args) {
		Loop test = new Loop();
		test.harmonicSum(50000);

	}

}
