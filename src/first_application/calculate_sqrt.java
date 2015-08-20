package first_application;

public class calculate_sqrt {
	/** 
	 * ((sqrt(20) + sqrt(10)) / (sqrt(20) - sqrt(10))
	 */
	public static void main(String[] args) {
		double sqrt20 = Math.sqrt(20);
		double sqrt10 = Math.sqrt(10);
		double result = (sqrt20 + sqrt10) / (sqrt20 - sqrt10);
		result = Math.round(result * 10) / 10.0;
		System.out.println("((sqrt(20) + sqrt(10)) / (sqrt(20) - sqrt(10))=" + 
				result);
	}
}
