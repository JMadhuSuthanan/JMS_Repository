package pkgExcelToXMLConversion;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class tryExcelToXMLConversion 
{

	String ExcelPath = "C:/Selenium Test Directory/SampleExcel.xls";
	public static int getColumns;
	static String[] returnGetColumnHeader = new String[getColumns];
	//public static String returnGetColumnHeader[];
	//@SuppressWarnings("null")
	@SuppressWarnings("null")
	public static void main(String[] args) throws Exception 
	{
		tryExcelToXMLConversion objectOfClass = new tryExcelToXMLConversion();
		
		Sheet sheet = getExcelSheetObject(objectOfClass.ExcelPath);
		
		
		getColumnHeader(sheet);
		
		loopToGetCell(sheet);
		
		System.out.println("Completed fetching the values from the excel");
		
	}
	//-Utility-
	public static Sheet getExcelSheetObject(String ExcelPath) throws JXLException, IOException
	{		
		File ExcelFile = new File(ExcelPath);
        Workbook workbook = Workbook.getWorkbook(ExcelFile);
        Sheet sheet = workbook.getSheet(0);        
        return sheet;
	}
	
	public static String loopToGetCell(Sheet sheet) throws Exception
	{
		String  CellText = null;
		int getRows= sheet.getRows();
		int getColumns= sheet.getColumns();
		
		for (int j=0;j<getColumns;j++)//Columns wise increase, 
		{			
			//for (int i =1; i<getRows;i++)		
			{				
			    CellText =  "<"+readCell(sheet, j, 1)+">";//to take the each column as tag and the next column as value  
				System.out.println("#Cell Text value of the cell j"+j +",1-:"+CellText);				
			}
		}
		
		return CellText;
	}
	@SuppressWarnings("null")
	public static String getColumnHeader(Sheet sheet) throws Exception
	{		
		int getRows= sheet.getRows();
		int getColumns = sheet.getColumns();
	/*	String[] returnGetColumnHeader = new String[getColumns];*/
		
		for (int j=0;j<getColumns;j++)//Columns wise increase, 
		{
			returnGetColumnHeader[j] =  "<"+readCell(sheet, j, 0)+">";//to take the each column as tag and the next column as value  				
			System.out.println("ColumnHeader[j] of j"+j+",0 :" +returnGetColumnHeader[j]);
			if(j+1 == getColumns)
			{
				break;
			}
		}
		
		return returnGetColumnHeader[getColumns];
	}
	public static String readCell(Sheet sheet, int j, int i) throws Exception
	{
		Thread.sleep(500);
		String CellText = sheet.getCell(j, i).getContents();

		Thread.sleep(500);
		return CellText;
	}

}
