package ch11;

public class CH11_2 {
	static int x = 100;
	static class Inner{
		void doitInner(){
			System.out.println("外部类" + x);
			double character = 1.1;
			System.out.println(character);
		}
		
//		public static void main(String[] args) {
//			System.out.println("test");
//			Inner a = new Inner();
//			a.doitInner();
//		}
	}
	
	static class Inner2 extends Inner {
		void doitInner() {
			System.out.println("外部类2" + x);
		}
	}
	public static void main(String[] args) {
		Inner test = new Inner();
		test.doitInner();
		Inner2 test2 = new Inner2();
		test2.doitInner();
	}

}
