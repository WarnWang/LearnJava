package ch11;

public class CH11_2 {
	static int x = 100;
	static class Inner{
		void doitInner(){
			System.out.println("外部类" + x);
		}
		
//		public static void main(String[] args) {
//			System.out.println("test");
//			Inner a = new Inner();
//			a.doitInner();
//		}
	}
	public static void main(String[] args) {
		Inner test = new Inner();
		test.doitInner();
	}

}
