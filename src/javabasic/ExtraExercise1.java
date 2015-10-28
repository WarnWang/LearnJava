package javabasic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ExtraExercise1 {
	public int dGenerater(int d) {
		int sum = d;
		while (d > 0) {
			int temp = d % 10;
			sum += temp;
			d /= 10;
		}
		return sum;
	}
	
	public ArrayList<Integer> getSelfNumber(int n) {
		ArrayList<Integer> selfNumber = new ArrayList<Integer>();
		JSONObject allNumber = new JSONObject();
		for (int i = 1; i < n + 1; i++) {
			int dG = this.dGenerater(i);
			String key = Integer.toString(dG);
			if (allNumber.has(key)) {
				allNumber.getJSONArray(key).put(i);
			} else {
				JSONArray values = new JSONArray();
				values.put(i);
				allNumber.put(key, values);
			}
		}
		for (int i = 1; i < n + 1; i++) {
			String key = Integer.toString(i);
			if (!allNumber.has(key)) {
				selfNumber.add(i);
			}
		}
		return selfNumber; 
	}
	
	public int checkHKIDDigit(String HKID) {
		int checkValue;
		int idLength = HKID.length();
		int charA = 'A';
		int char1 = '1';
		
		checkValue = (HKID.charAt(0) - charA + 1) * 8;
		
		for (int i = 1; i < idLength; i++) {
			checkValue += (HKID.charAt(i) - char1 + 1) * (idLength - i + 1);
		}
		
		checkValue = 11 - checkValue % 11;
		return checkValue;
	}

	public static void main(String[] args) {
		ExtraExercise1 test = new ExtraExercise1();
//		System.out.println(test.getSelfNumber(100));

		System.out.println(test.checkHKIDDigit("M150618"));
	}

}
