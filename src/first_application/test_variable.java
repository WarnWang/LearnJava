package first_application;

public class test_variable {
	public static void main(String[] args) {
		String mobile_brand = "С��";
		String os = "Android4.3";
		String out_info;
		int cpu_no = 2;
		float price = 1999.0f;
		out_info = String.format("�ֻ�Ʒ�ƣ�%s\n����ϵͳ��%s\nCPU������%d\n�۸�%.2f", 
				mobile_brand, os, cpu_no, price);
		System.out.println(out_info);
		
	}

}
