package ch12;

class MyException extends Exception{
	/**
	 * my exception
	 */
	private static final long serialVersionUID = 1L;

	public MyException(String ErrorMessager) {
		super(ErrorMessager);
		System.out.println("Error:" + ErrorMessager);
	}
}

class Student{
	void speak(int m) throws MyException{
		if (m > 1000) {
			throw new MyException("m greater than 1000");
		}
	}
}

class Computer{
	int commonDivier(int m, int n) throws MyException{
		if (n <= 0 || m <= 0){
			throw new MyException("N must be positive");
		}
		int i = 0;
		int ans = 1;
		for(; i < Math.min(n, m); i++){
			int temp = i + 1;
			if (m % temp == 0 && n % temp == 0) {
				ans = temp;
			}
		}
		return ans;
	}
}

public class CH12_1 {

	public static void main(String[] args) {
//		Student test = new Student();
//		try {
//			test.speak(10000);
//		} catch (Exception e) {
////			System.out.println(e);
//			e.printStackTrace();
//		}
//		System.out.println(5%2);
		Computer test1 = new Computer();
		try{
			System.out.println(test1.commonDivier(432, -19));
		} catch (MyException e) {
			e.printStackTrace();
		}
		
	}

}
