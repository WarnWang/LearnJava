package ch12;

public class CH12_2 {

	public double count(String x, String y) {
		double ans = 0;
		double m, n;
		try {
			m = Double.parseDouble(x);
			n = Double.parseDouble(y);
			ans = m / n;
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	public static void main(String[] args) {
		CH12_2 test = new CH12_2();
		System.out.println(test.count("0", "0"));

	}

}
