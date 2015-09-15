package ch7;

public class CH7_1 {
	private int parameter = 10;
	public void setPara(int newPara) {
		this.parameter = newPara;
	}
	
	public int getPara() {
		return parameter;
	}

	public static void main(String[] args) {
		CH7_1 test1 = new CH7_1();
		CH7_1 test2 = new CH7_1();
		System.out.println(test1.getPara());
		test2.setPara(20);
		System.out.println(test2.getPara());
		System.out.println(test1.getPara());
	}

}

