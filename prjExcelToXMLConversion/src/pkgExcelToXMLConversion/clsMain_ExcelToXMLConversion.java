package pkgExcelToXMLConversion;

import java.io.File;

public class clsMain_ExcelToXMLConversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        String path ="Z:/ExcelToXMLConversion/ExcelForXML_Conversion ExactFromSampleXML.xls";

        File excelFile = new File(path);

        System.out.println("The path of the excel which needs conversion is "+path);

        clsExcelToXMLConversion oClass= new clsExcelToXMLConversion();

        oClass.toXml(excelFile);
	}

}
