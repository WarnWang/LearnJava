package ch9;

import java.math.BigDecimal;

public class BigDecimalDemo {
	public BigDecimal div(double value1, double value2, int scale) {
		if(scale < 0){
			System.err.println("b must larger than 0.");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.divide(b2, scale, BigDecimal.ROUND_DOWN);
	}
	
	public static void main(String[] args) {
		BigDecimalDemo test = new BigDecimalDemo();
		System.out.println(test.div(2.1, 2.2, 6));
	}

}