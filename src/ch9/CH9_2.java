package ch9;

import java.text.DecimalFormat;

public class CH9_2 {
	public String returnAera(double r) {
		DecimalFormat myFormat = new DecimalFormat();
		myFormat.applyPattern("#.#####");
		return myFormat.format(Math.PI * r * r);
	}

	public static void main(String[] args) {
		CH9_2 test = new CH9_2();
		for(int i = 0; i < 100; i++){
			System.out.println(test.returnAera(Math.random()));
		}
	}

}
