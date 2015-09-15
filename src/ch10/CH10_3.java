package ch10;

class Father {
	int s = 10;
	public Father(){
		System.out.println(s);
		System.out.println("I'm father!");
	}
}

class Child extends Father {
	int s = 20;
	public Child() {
		super();
		System.out.println(s);
		System.out.println("I'm child");
	}
}

public class CH10_3 {

	public static void main(String[] args) {
		new Child();
	}

}
