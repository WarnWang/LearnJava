package ch10;

public class CH10_2 {
	public int no1 = 10;
	public int no2 = 10;
	public int calculta(CH10_2 t) {
		return t.no1 * t.no2;
	}
	
	public void setNo1(int s){
		this.no1 = s;
	}
	
	public void setNo2(int s) {
		this.no2 = s;
	}
	
	public String toString(){
		return "no1: " + this.no1 + "\nno2: " + this.no2;
	}
	
	public static void main(String[] args) {
		CH10_2 test = new ChildClass();
		System.out.println(test.calculta(test));
		System.out.println(test);
	}

}

class ChildClass extends CH10_2{
	public int calculta(CH10_2 t) {
		return t.no1 + t.no2;
	}
}
