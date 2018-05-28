
	package pkgExcelToXMLConversion;

	 

	import java.io.File;

	import java.io.IOException;

	import java.io.UnsupportedEncodingException;

	import javax.swing.text.BadLocationException;

	import jxl.Cell;

	import jxl.CellType;

	import jxl.Sheet;

	import jxl.Workbook;

	import jxl.format.Font;

	import jxl.read.biff.BiffException;

	 

public class clsExcelToXMLConversion {

	public String toXml(File excelFile) throws IOException, BiffException {

	    try {

	        String xmlLine = "";

	        String rowText = "";

	        String colText = "";

	        String isBold = "";

	        Font font = null;

	        String cellCol = "";

	        String cellAddress = "";

	        Cell cell = null;

	        Workbook workbook = Workbook.getWorkbook(excelFile);

	        xmlLine += "<workbook>" + "\n";

	        for (int sheet = 0; sheet <workbook.getNumberOfSheets(); sheet++) {

	            Sheet s = workbook.getSheet(sheet);

	            xmlLine += "  <sheets>" + "\n";

	            Cell[] row = null;

	            for (int i = 0; i < s.getRows(); i++) {

	                row = s.getRow(i);

	                for (int j = 0; j < row.length; j++) {

	                    if (row[j].getType() != CellType.EMPTY) {

	                        cell = row[j];

	                        cellCol=columnName(cell.getColumn());

	                        cellCol=" colLetter=\""+cellCol+"\"";

	                        cellAddress=" address=\""+cellAddress(cell.getRow()+1,cell.getColumn())+"\"";

	                        isBold =cell.getCellFormat().getFont().getBoldWeight() == 700 ? "true" : "false";

	                        isBold = (isBold == "false" ? "" : "isBold=\"true\"");

	                        colText += "      <col number=\"" + (j + 1) + "\"" + isBold +cellAddress+ ">";

	                        colText += "<![CDATA[" + cell.getContents() + "]]>";

	                        colText += "</col>" + "\n";

	                        rowText += cell.getContents();

	                    }

	                                                                                                                                                                                   /*System.out.println( "1: At Row Level -colText is :"+colText);

	                                                                                                                                                                                   System.out.println( "1: At Row Level -rowText is :"+rowText);

	                                                                                                                                                                                   System.out.println( "1: At Row Level -xmlLine is :"+xmlLine);*/

	                }//for the j no of columns, here ends the columns for the row

	                if (rowText != "") {

	                    xmlLine += "    <row number=\"" + (i + 1) + "\">" + "\n";

	                    xmlLine += colText;

	                    xmlLine += "    </row>" + "\n";

	                }

	                                                                                                                                                                                                                                               System.out.println( "2: At Sheet Level -colText is :"+colText);

	                                                                                                                                                                                                                                               System.out.println( "2: At Sheet Level -rowText is :"+rowText);

	                                                                                                                                                                                                                                               System.out.println( "2: At Sheet Level -xmlLine is :"+xmlLine);               

	                colText = "";//we are emptying the value for each row

	                rowText = "";//we are emptying the value for each row

	            }//for the i no of rows, here ends the rows for the sheet

	                                                                                                                                                                                                                                               System.out.println( "3: At workbook Level -colText is :"+colText);

	                                                                                                                                                                                                                                               System.out.println( "3: At workbook Level -rowText is :"+rowText);

	                                                                                                                                                                                                                                               System.out.println( "3: At workbook Level - xmlLine is :"+xmlLine);

	            xmlLine += "  </sheet>" + "\n";

	        }//workbook for loop, here ends the sheets for the workbook

	        xmlLine += "</workbook>";

	        return xmlLine;

	    } catch (UnsupportedEncodingException e) {

	        System.err.println(e.toString());

	    }

	    return null;

	}

	private String cellAddress(Integer rowNumber, Integer colNumber){

	    //return "$"+columnName(colNumber)+"$"+rowNumber;

	    return columnName(colNumber)+rowNumber;

	}

	private String columnName(Integer colNumber) {

	    Base columns = new Base(colNumber,26);

	    columns.transform();

	    return columns.getResult();

	}

	 

	class Base {

	    String[] colNames = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");

	    String equalTo;

	    int position;

	    int number;

	    int base;

	    int[] digits;

	    int[] auxiliar;

	 

	    public Base(int n, int b) {

	        position = 0;

	        equalTo = "";

	        base = b;

	        number = n;

	        digits = new int[1];

	    }

	 

	    public void transform() {

	        if (number < base) {

	            digits[position] = number;

	            size();

	        } else {

	            digits[position] = number % base;

	            size();

	            position++;

	            number = number / base;

	            transform();

	        }

	    }

	 

	    public String getResult() {

	        for (int j = digits.length - 2; j >= 0; j--) {

	            equalTo += colNames[j>0?digits[j]-1:digits[j]];

	        }

	        return equalTo;

	    }

	 

	    private void size() {

	        auxiliar = digits;

	        digits = new int[auxiliar.length + 1];

	        System.arraycopy(auxiliar, 0, digits, 0,auxiliar.length);

	    }

	}

	



}
