import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;


public class ReadInExcel {

	public static void main(String[] args) {
		// Auto-generated method stub

		File oSampleFile = new File("C:\\Selenium Test Directory\\SampleExcel.xls");
		FileInputStream inputstream = new FileInputStream(oSampleFile);
		Workbook workbook = Workbook.getWorkbook(new File("C:\\Selenium Test Directory\\SampleExcel.xls"));
		
		//System.out.println('pip3 freeze');
		 
		
	}

}
