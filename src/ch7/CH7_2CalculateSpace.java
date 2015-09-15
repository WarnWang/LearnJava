package ch7;

public class CH7_2CalculateSpace {
	int wide = 10;
	int length = 10;
	public CH7_2CalculateSpace(int wide, int length){
		this.wide = wide;
		this.length = length;
	}
	
	public int space(){
		return this.wide * this.length;
	}

	public static void main(String[] args) {
		CH7_2CalculateSpace test = new CH7_2CalculateSpace(12, 22);
		System.out.println(test.space());

	}

}
