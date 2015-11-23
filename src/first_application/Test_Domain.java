package first_application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test_Domain {
	public static void main(String[] args) {
		BufferedReader bReader = null;
		StringBuffer stringBuffer = new StringBuffer();
		
		try {
			String sCurrentLine;
			
			bReader = new BufferedReader(new FileReader("/Users/warn/Documents/FacilityStatusDate.aspx"));
			
			while ((sCurrentLine = bReader.readLine()) != null) {
				stringBuffer.append(sCurrentLine);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (bReader != null) {
				try {
					bReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		Document document = Jsoup.parse(stringBuffer.toString());
		System.out.println(document.getElementById("__VIEWSTATE").val().toString());
	}

}
