package first_application;

public class test_variable {
	public static void main(String[] args) {
		String mobile_brand = "小米";
		String os = "Android4.3";
		String out_info;
		int cpu_no = 2;
		float price = 1999.0f;
		out_info = String.format("手机品牌：%s\n操作系统：%s\nCPU个数：%d\n价格：%.2f", 
				mobile_brand, os, cpu_no, price);
		System.out.println(out_info);
		
	}

}
