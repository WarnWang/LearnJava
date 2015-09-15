package ch11;

abstract class AnyClass{
	int t = 11;
	abstract int getValue();
	abstract void setValue(int s);
}

public class CH11_1 {
	public AnyClass test() {
		return new AnyClass(){
			private int t = 10;
			public int getValue() {
				return t;
			}
			public void setValue(int s) {
				t = s;
			}
		};
	}

	public static void main(String[] args) {
		CH11_1 haha = new CH11_1();
		AnyClass test = haha.test();
		System.out.println(test.getValue());
		test.setValue(22);
		System.out.println(test.getValue());
	}

}
