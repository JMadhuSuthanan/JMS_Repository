//BServe_All_Class.java


package BServe_All_Package;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sourceforge.htmlunit.cyberneko.HTMLElements.Element;



public class BServe_All_Class 
{
	public static String obj;
	public static WebDriver driver;
	public static WebDriverWait wait3;
	public static WebDriverWait wait4;
	public static WebElement Page_Logo;
	public static boolean Page_Logo_Displayed;
	public static boolean ExecutionStatus;
	public static WebDriverWait wait31;
	public static Robot Rob1;
	public static Alert Unexpected_Alert;
	public static FileWriter fw;
	public static BufferedWriter BW;
	public static SimpleDateFormat sdfDate;
	public static Date now;
	public static String strDate;
	public static String Download_Path;
	public static String Prent_WNDID;
	public static Sheet RTM_Sheet1;
	public static Sheet RTM_Sheet2;
	public static DateFormat dateFormat;
	public static String Result_Folder_Path;
	public static JavascriptExecutor js;
	public static String Plan_Design_Name;
	public static String Working_Dir;
	public static File Ab_File1;
	public static String Parent_Dir;
	public static String Input_File_Path;
	public static String JARS_File_Path;
	public static String IE_Driver_Path;
	public static String RTM_Folder_Path;
	public static String carrcode;
	public static String carrname;
	public static String PlanName;
	public static String Effective_Date;
	public static File f1;
	public static Workbook WB;
	public static Sheet WS;
	public static Sheet Input_WS;
	public static Sheet BusinessFlow_WS;
	public static Sheet CreatePlanDesign_WS;
	public static String BServe_URL;
	public static String User_Name;
	public static String Pawd;
	public static File RTM_FPath;
	public static File[] RTM_files;
	public static int iteration;
	public static String RTM_File_Name;
	public static String RTM_File_FullPath;	
	public static String Tier_Type;	
	public static String resval;
	public static ObjectMap objmap;
	public static String maxcov1;
	public static String maxcov2;
	public static int x;
	public static int y;
	public static String Property_Path;
	public static String FileName;
	
	public static void main(String[] args) throws Exception 
	{		
		//Create File Path
		
		Create_File_Path();
		
		//Create Log File
		Create_Log_File();		
																																								
		//Create IE Driver
		Create_IE_Driver();
		
		js = (JavascriptExecutor) driver;

//Get Current Date Time
		strDate = Get_Current_Date_Time();		
		FileName = "C:\\Users\\436586\\workspace\\Results\\"+sdfDate.format(now).replace(":", "_").replace(".", "_").replace(" ", "_")+"\\";
		System.out.println("FileName " + strDate);
		
//Write Execution Start Time
		System.out.println("Starts Execution at: " + strDate);
		WriteLog("Starts Execution at: " + strDate);
		wait4  = new WebDriverWait(driver, 60);
		
//Open Main Input Sheet
		//Open_Main_Input_Sheet();		
		
//Open RTM File
		//Open_RTM_Folder_Path();	
		
//Load Object Library		
/*		Property_Path = Parent_Dir+"\\Driver Script\\objectmap.properties";
		objmap = new ObjectMap(Property_Path);//For making as runnable Jar file
*/		String workingDir=System.getProperty("user.dir");
		objmap = new ObjectMap(workingDir+"\\src\\BServe_All_Package\\objectmap.properties");
		System.out.println("Property_Path: " + Property_Path);//for executing locally in eclipse IDE
		iteration=1;	
		
//Core#########Core#########Core#########Core######### Calling business methods Core#########Core#########Core#########Core#########
		
			f1=new File(Input_File_Path);
			WB=Workbook.getWorkbook(f1);	
		
			BServe_URL = GetValueFromExcel("Input", 0, 1);
			User_Name  = GetValueFromExcel("Input", 1, 1);
			Pawd       = GetValueFromExcel("Input", 2, 1);
							
			Launch_IE_Browser();			
				
			Login_Bserve();	
			
			//int x = 1;// Row starts with 0 
			int y = 1;
			int z = 1;
			//int rowsCountBusinessFlow_WS  = WB.getSheet("BusinessFlow").getRows();
			int rowsCountBusinessFlow_WS=Integer.parseInt(GetValueFromExcel("Input",8,1));
			//int rowsCountCreatePlanDesign = WB.getSheet("CreatePlanDesign").getRows();
			int rowsCountCreatePlanDesign=Integer.parseInt(GetValueFromExcel("Input",7,1));
			//int businessFlowcolsCount	  = WB.getSheet("BusinessFlow").getColumns();
			int businessFlowcolsCount=Integer.parseInt(GetValueFromExcel("Input",9,1));
			
			int businessFlowRowIdx 		  = 0;
			boolean foundClientinBusinessFlow;
			java.lang.reflect.Method method = null;				 							
			
			for (x=1; x<=rowsCountCreatePlanDesign; x++)//Loop - 1 - through CreatePlanDesign
			{				
				foundClientinBusinessFlow=false;
				String currentClient_Name 	= GetValueFromExcel("CreatePlanDesign",0,x);
				String currentPlanName 		= GetValueFromExcel("CreatePlanDesign",1,x);
				System.out.println("Started for currentPlanName: # " + currentPlanName);
				System.out.println("Started for currentClient_Name: # " + currentClient_Name);				
						
				String CurrentBusinessFlow;
				for (z =1 ; z<=rowsCountBusinessFlow_WS; z++)// Loop-2 - through Business Flow Sheet to Find current Client Name 
				{ 						
					CurrentBusinessFlow     = GetValueFromExcel("BusinessFlow",0,z);
					if (CurrentBusinessFlow ==currentClient_Name)  //change == to contains
					{
						System.out.println("Started for Current Client Name is # "+currentClient_Name);
						businessFlowRowIdx = z;
						foundClientinBusinessFlow=true;
					    break;
						
					}else if(z == rowsCountBusinessFlow_WS-1){
						
						System.out.println("current Client_Name is not present in Business Flow Work Sheet");
						foundClientinBusinessFlow=false;
						break;
					}
					
				}				
				
				if(foundClientinBusinessFlow==true)
				{
						//For_colsCountBusinessFlow_WS:
						for (y=1; y<=businessFlowcolsCount; y++)
						{ 			
							String BusinessFlowKeyword = GetValueFromExcel("BusinessFlow",y,businessFlowRowIdx);
							if (BusinessFlowKeyword.isEmpty())
							{
								System.out.println("methodName is empty");
								break;//to come out of columns loop in BusinessFlow_WS
								//break For_colsCountBusinessFlow_WS;//to come out of columns loop in BusinessFlow_WS								
							}
							else
							{
								String methodName = GetValueFromExcel("BusinessFlow",y,businessFlowRowIdx);							
								Method method1	  = BServe_All_Class.class.getDeclaredMethod(methodName, null);
								boolean 			ReturnValue;
								ReturnValue 	  = false;
								do 
								{																	   //method
									Object value  = method1.invoke(null, null);									
									ReturnValue   = (boolean) value;
									System.out.println("Return Value: "+ methodName +" "+ ReturnValue  );	
									if (ReturnValue==false)
									{											
										Close_Unexpected_Alert();
										Refresh_Page();
										Close_Unexpected_Alert();
									}
								}
								while (ReturnValue == false);	
							}
							//Refresh_Page();
						}			//For Loop for y=1; y<=colsCount; y++	
							//break For_rowsCountBusinessFlow_WS;		//to come out of rows loop in BusinessFlow_WS
						iteration=iteration+1;
				}					//for (z =1 ; z<=rowsCountBusinessFlow_WS; z++
				else
				{
					System.out.println("Current Client Name is "+currentClient_Name + " not present in Business Flow");
				}
				System.out.println("Started for currentPlanName: # " + currentPlanName);
				System.out.println("End for currentClient_Name: # " + currentClient_Name);
			}
			EndQuit();
					
			
						/*//		
						
						//Change to Benefit Analyst
						iteration=iteration+1;			
						ChangeDesignation();
											
						String resval;
						
						//------------------------------------------------------------- Navigating To Copy Plan Design		
						NavigatingToCopyPlan_Design();
						//NavigatingToCopyfromTemplate();
							
							
						//------------------------------------------------------------- Search Plan Design & Select 
						SearchPlanDesignAndSelect();			
						
						//------------------------------------------------------------- Page 1 - Create Plan Design
						CreatePlanDesign();
									
						//------------------------------------------------------------- Page 2 - Delivery System
						DeliverySystem();
						
						//------------------------------------------------------------- Page 3- CAG & Contract Details
						CAGAndContractDetails();			
						
						//------------------------------------------------------------- Page 4 - DAW & Excluded
						DAWAndExcluded();
									
						//------------------------------------------------------------- Page 5 - Stepped Copay
						SteppedCopay();
			 
						//------------------------------------------------------------- Page 6 - Stepped Copay VA
						SteppedCopay_VA();
						
						//------------------------------------------------------------- Page 7 - Accumulations
						Accumulation();
						
						//------------------------------------------------------------- Page 8: Unique Copay
						UniqueCopay();
						
						//------------------------------------------------------------- Formulary Mapping
						FormularyMapping();
			
						//------------------------------------------------------------- Finalize
						Finalize();
						
						//BServe_All_Class.OnlyForQuickExecution();
						
						//------------------------------------------------------------- SelectPlanDesginName
						SelectPlanDesginName();
			
						//------------------------------------------------------------- GenerateScenario
						GenerateScenario();
						
						//------------------------------------------------------------- InitiateMemberRequest
						InitiateMemberRequest();
						
						//------------------------------------------------------------- Download_Excel_Report
						Download_Excel_Report();	*/		
			}//For Loop for x=1; x<=rowsCountCreatePlanDesign; x++
					
						//EndQuit();
						
	/*}//RTM_files
*/		//newly added by madhu
	
	//-----------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------- End of Main Function ------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------


	//Sub Function - 'Generic (Tier 1) - Default Values
	public static boolean EndQuit()
	{
		try{
			driver.close();
			System.out.println("Done...");
			WriteLog("*************************************All Done");
			now = new Date();
			strDate = sdfDate.format(now);
			WriteLog("Ends whole processes at : " + strDate);
			BW.close();
			fw.close();	
			//driver.close();
			driver.quit();	
			/*ExecutionStatus =true;*/
		}
		catch(Exception EndQuit_Error)
		{
			System.out.println(" Error:"+ EndQuit_Error);		
			/*ExecutionStatus =false;*/	
		}
		/*return ExecutionStatus;*/
		return ExecutionStatus;
	}
	public static void Select_DropDown_TextBox_Values() throws Exception
	{		
		SendKeys_TO_Object("Edt_30" ,"30");
		
		SendKeys_TO_Object("Edt_60" ,"60");
		
		SendKeys_TO_Object("Edt_90" ,"90");
						
		SendKeys_TO_Object("Edt_30_2" ,"30");
					
		SendKeys_TO_Object("Edt_60_2" ,"60");
				
		SendKeys_TO_Object("Edt_90_2" ,"90");
				 		
		SendKeys_TO_Object("Edt_30_3" ,"30");
				 		
		SendKeys_TO_Object("Edt_60_3" ,"60");
		 
		SendKeys_TO_Object("Edt_90_3" ,"90");
				 		
		SendKeys_TO_Object("Edt_30_3" ,"30");
				 
		SendKeys_TO_Object("Edt_60_3" ,"60");
				 
		SendKeys_TO_Object("Edt_90_3" ,"90");
				
		SendKeys_TO_Object("Edt_Retail_IN_MergedSteppedCopay_0" , "Retail(IN)");
		SendKeys_TO_Object("Edt_Retail_IN_MergedSteppedCopay_1" , "Retail(IN)");
		SendKeys_TO_Object("Edt_Retail_IN_MergedSteppedCopay_2" , "Retail(IN)");		
		
		SendKeys_TO_Object("Edt_Retail_IN_MergedSteppedCopay_3" , "Retail(IN)");
		SendKeys_TO_Object("Edt_Retail_IN_MergedSteppedCopay_4" , "Retail(IN)");
		SendKeys_TO_Object("Edt_Retail_IN_MergedSteppedCopay_5" , "Retail(IN)");
		
		SendKeys_TO_Object("Edt_Retail_IN_MergedSteppedCopay_6" , "Retail(IN)");
		SendKeys_TO_Object("Edt_Retail_IN_MergedSteppedCopay_7" , "Retail(IN)");
		SendKeys_TO_Object("Edt_Retail_IN_MergedSteppedCopay_8" , "Retail(IN)");
		
		SendKeys_TO_Object("Edt_Retail_Out_MergedSteppedCopay_9" , "Retail(OUT)");
		SendKeys_TO_Object("Edt_Retail_Out_MergedSteppedCopay_10" , "Retail(OUT)");
		SendKeys_TO_Object("Edt_Retail_Out_MergedSteppedCopay_11" , "Retail(OUT)");
		
	}
	public static void retail30_For_PacSource(String resv) throws Exception//madhu
	{
		System.out.println("retail30_For_PacSource Started");
		
		 Thread.sleep(3000);
		if (resv.contains("Min"))
		{
			System.out.println("retail30 Method with resv value:"+ resv);
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_0"); //Retial 
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay102")));
			//Thread.sleep(1000);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee102",pctvalue); // Retail
			Thread.sleep(100);
			if (minvalue >0)
			{
				//retail
				SendKeys_TO_Object_SC("CoPayment1txtmincopay102",String.valueOf(minvalue));
				Thread.sleep(100);
			}
			Clear_Object("CoPayment1txtmaxcopay102"); //retail
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay102",maxvalue);
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			Click_ObjectWo("imgCancelOf_0");//Click Clear Btn
			Wait_For_Element("CoPayment1txtcoinsurancee102");
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
				//Retail
			SendKeys_TO_Object_SC("CoPayment1txtmincopay102",temp2);
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{			
			Click_ObjectWo("imgCancelOf_0");//Click Clear Btn
			Wait_For_Element("CoPayment1txtcoinsurancee102");
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11 ;
			//Retail
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee102",temp2);			
			Thread.sleep(100);			
		}
	}

	//Retial - 30 - ######### validated
	public static void retail30(String resv) throws Exception
	{
		System.out.println("retail30 Started");
		 Thread.sleep(3000);
		if (resv.contains("Min"))
		{
			System.out.println("retail30 Method with resv value:"+ resv);
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_0"); //Retial 
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay102")));
			Thread.sleep(100);
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_6"); //Paper IN
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay108"))); //CoPayment1txtmaxcopay108
			Thread.sleep(100);
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_9"); // Paper Out					
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay111"))); //CoPayment1txtmaxcopay108
			
			Thread.sleep(1000);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee102",pctvalue); // Retail
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee108",pctvalue); // Paper IN
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee111",pctvalue); // Paper Out
			Thread.sleep(100);

			if (minvalue >0)
			{
			//retail
				SendKeys_TO_Object_SC("CoPayment1txtmincopay102",String.valueOf(minvalue));
				Thread.sleep(100);
			//paper in
				SendKeys_TO_Object_SC("CoPayment1txtmincopay108",String.valueOf(minvalue));
				Thread.sleep(100);
			//paper out
				SendKeys_TO_Object_SC("CoPayment1txtmincopay111",String.valueOf(minvalue));
				Thread.sleep(100);
			}
			//retail
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay102",maxvalue);
			Thread.sleep(100);
			//paper IN
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay108",maxvalue);
			Thread.sleep(100);
			//Paper OOUT
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay111",maxvalue);
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
			//Retail
			SendKeys_TO_Object_SC("CoPayment1txtmincopay102",temp2);
			Thread.sleep(100);
			// Paper IN
			SendKeys_TO_Object_SC("CoPayment1txtmincopay108",temp2);
			Thread.sleep(100);
			// Paper Out
			SendKeys_TO_Object_SC("CoPayment1txtmincopay111",temp2);
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11;					
			Clear_Object("CoPayment1txtmincopay102");//Retail
			//Retail
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee102",temp2);			
			Thread.sleep(100);			
			Clear_Object("CoPayment1txtmincopay108");//Paper IN
			//Paper IN
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee108",temp2);			
			Thread.sleep(100);			
			Clear_Object("CoPayment1txtmincopay111");//Paper Out
			//Paper Out
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee111",temp2);						
			Thread.sleep(100);
		}
	}
	public static void retail60_For_PacSource(String resv) throws Exception//madhu
	{
		System.out.println("retail60_For_PacSource Started");
		 Thread.sleep(3000);
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			JavascriptExecutorClick("RbtnRetial_1_4_1"); //Retial
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee103",pctvalue); // Retail
			if (minvalue >0)
			{
			//retail
				SendKeys_TO_Object_SC("CoPayment1txtmincopay111",String.valueOf(minvalue));								
				Thread.sleep(100);
			}
			//retail
			SendKeys_TO_Object_SC("CoPayment1txtmincopay103",maxvalue);
						
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";			
			JavascriptExecutorClick("imgCancelOf_1");			
			Wait_For_Element("CoPayment1txtmincopay103");			
			//Retail
			SendKeys_TO_Object_SC("CoPayment1txtmincopay103",temp2);						
			Thread.sleep(100);			
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11 ;					
			JavascriptExecutorClick("imgCancelOf_1");			
			Wait_For_Element("CoPayment1txtcoinsurancee103");	
			Clear_Object("CoPayment1txtmincopay103");//Retail
			//Retail
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee103",temp2);
			Thread.sleep(100);
		}
	}
	//Retila - 60
	public static void Retail60(String resv) throws Exception
	{
		System.out.println("Retail60 Started");
		 Thread.sleep(500);
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			JavascriptExecutorClick("RbtnRetial_1_4_1"); //Retial
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay103")));
			Thread.sleep(100);
			JavascriptExecutorClick("RbtnPaperIN7_4_7");	 //Paper IN
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay109")));
			Thread.sleep(100);
			JavascriptExecutorClick("RbtnPaperOut_10_4_10"); // Paper Out
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay112")));
			Thread.sleep(1000); // PCT Value
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee103",pctvalue); // Retail
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee109",pctvalue); // Paper IN
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee112",pctvalue); // Paper Out
			Thread.sleep(100);
			//
			if (minvalue >0)
			{
				//retail
				SendKeys_TO_Object_SC("CoPayment1txtmincopay103",String.valueOf(minvalue));				
				Thread.sleep(100);
				//paper in
				SendKeys_TO_Object_SC("CoPayment1txtmincopay109",String.valueOf(minvalue));						
				Thread.sleep(100);
				//paper in
				SendKeys_TO_Object_SC("CoPayment1txtmincopay112",String.valueOf(minvalue));				
				Thread.sleep(100);
			}
			//retail
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay103",maxvalue);					
			Thread.sleep(100);
			//paper IN
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay109",maxvalue);			
			Thread.sleep(100);
			//paper OOUT
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay112",maxvalue);					
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
			//retail
			SendKeys_TO_Object_SC("CoPayment1txtmincopay103",temp2);					
			Thread.sleep(100);
			//paper in
			SendKeys_TO_Object_SC("CoPayment1txtmincopay109",temp2);						
			Thread.sleep(100);
			//Paper Out
			SendKeys_TO_Object_SC("CoPayment1txtmincopay112",temp2);			
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11 ;			
			Clear_Object("CoPayment1txtmincopay103");//Retail
			//Retail
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee103",temp2);			
			Thread.sleep(100);
			Clear_Object("CoPayment1txtmincopay109");//Paper IN
			//Paper IN
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee109",temp2);
			Thread.sleep(100);
			Clear_Object("CoPayment1txtmincopay112");//Paper Out			
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee112",temp2);			
			Thread.sleep(100);
		}
	}
	public static void retail90_For_PacSource(String resv) throws Exception
	{
		System.out.println("retail90_For_PacSource Started");
		 Thread.sleep(3000);
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_2"); //Retial
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee104", pctvalue); // Retail
			Thread.sleep(100);
			//
			if (minvalue >0)
			{										
				SendKeys_TO_Object_SC("CoPayment1txtmincopay104",String.valueOf(minvalue));								
				Thread.sleep(100);
			}
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay104",maxvalue);			
			Thread.sleep(100);											
			
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
			
			if (driver.findElement(ObjectMap.getLocator("CoPayment1txtmincopay104")).isDisplayed())
			{}else{
				JavascriptExecutorClick("imgCancelOf_2");
				Wait_For_Element("CoPayment1txtmincopay104");
			} 			
			//JavascriptExecutorClick("imgCancelOf_2");
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmincopay104")));//doubt need to ask sankal
			SendKeys_TO_Object_SC("CoPayment1txtmincopay104",temp2);
			Thread.sleep(100);			 
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11;			
			JavascriptExecutorClick("imgCancelOf_2");
			Wait_For_Element("CoPayment1txtmincopay104");
			Clear_Object("CoPayment1txtmincopay104");//Retail
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee104",temp2);			
			Thread.sleep(100);
		}
	}
	//Retila - 90
	public static void Retail90(String resv) throws Exception
	{
		System.out.println("Retail90 Started");
		 Thread.sleep(3000);
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_2"); //Retial
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay104")));
			Thread.sleep(100);
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_8");  //Paper IN
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay110")));
			Thread.sleep(100);
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_11");  // Paper Out
			// PCT Value	
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee104", pctvalue); // Retail
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee110", pctvalue); // Paper IN
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee113", pctvalue); // Paper Out
			Thread.sleep(100);
			if (minvalue >0)
			{						
				SendKeys_TO_Object_SC("CoPayment1txtmincopay104",String.valueOf(minvalue));				
				Thread.sleep(100);
				SendKeys_TO_Object_SC("CoPayment1txtmincopay110",String.valueOf(minvalue));				
				Thread.sleep(100);
				SendKeys_TO_Object_SC("CoPayment1txtmincopay113",String.valueOf(minvalue));			
				Thread.sleep(100);
			}
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay104",maxvalue);			
			Thread.sleep(100);								
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay110",maxvalue);			
			Thread.sleep(100);			
			//Paper Out
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay113",maxvalue);	
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
			SendKeys_TO_Object_SC("CoPayment1txtmincopay104",temp2);
			//Retail			
			Thread.sleep(100);
			//Paper IN
			SendKeys_TO_Object_SC("CoPayment1txtmincopay110",temp2);
			Thread.sleep(100);
			//Paper Out
			SendKeys_TO_Object_SC("CoPayment1txtmincopay113",temp2);			
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11 ;//need to remove
			Clear_Object("CoPayment1txtmincopay104");//Retail
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee104",temp2);			
			Thread.sleep(100);
			Clear_Object("CoPayment1txtmincopay110");//Paper IN
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee110",temp2);
			Thread.sleep(100);			
			Clear_Object("CoPayment1txtmincopay113");//Paper Out
			//Paper Out
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee113",temp2);
			Thread.sleep(100);
		}
	}

	//Mail - 30
	public static void mail30(String resv) throws Exception
	{
		System.out.println("mail30 Started");
		 Thread.sleep(3000);
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_3");
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay105")));
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee105",pctvalue);
			Thread.sleep(100);
			if (minvalue >0)
			{
				SendKeys_TO_Object_SC("CoPayment1txtmincopay105",String.valueOf(minvalue));
				Thread.sleep(100);
			}
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay105",maxvalue);
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
			JavascriptExecutorClick("imgCancelOf_3");
			Wait_For_Element("CoPayment1txtmincopay105");
			SendKeys_TO_Object_SC("CoPayment1txtmincopay105",temp2);
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11 ;
			JavascriptExecutorClick("imgCancelOf_3");
			Wait_For_Element("CoPayment1txtcoinsurancee105");
			Clear_Object              ("CoPayment1txtmincopay105");
			SendKeys_TO_Object_SC		  ("CoPayment1txtcoinsurancee105",temp2);
			Thread.sleep(100);
		}
	}

	//Mail - 60
	public static void mail60(String resv) throws Exception
	{
		System.out.println("mail60 Started");
		 Thread.sleep(3000);
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_4");
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee106",pctvalue);
			Thread.sleep(100);
			if (minvalue >0)
			{
				SendKeys_TO_Object_SC("CoPayment1txtmincopay106",String.valueOf(minvalue));
				Thread.sleep(100);
			}
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay106",maxvalue);
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
			JavascriptExecutorClick("imgCancelOf_4");
			Wait_For_Element("CoPayment1txtmincopay106");
			SendKeys_TO_Object_SC("CoPayment1txtmincopay106",temp2);
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11 ;
			JavascriptExecutorClick("imgCancelOf_4");
			Wait_For_Element("CoPayment1txtcoinsurancee106");
			Clear_Object              ("CoPayment1txtmincopay106");
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee106",temp2);
			Thread.sleep(100);
		}
	}

	//Mail - 90
	public static void mail90(String resv) throws Exception
	{
		System.out.println("mail90 Started");
		 Thread.sleep(3000);
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_5");
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee107",pctvalue);
			Thread.sleep(100);
			if (minvalue >0)
			{
				SendKeys_TO_Object_SC("CoPayment1txtmincopay107",String.valueOf(minvalue));
				Thread.sleep(100);
			}
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay107",maxvalue);
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
			JavascriptExecutorClick("imgCancelOf_5");
			Wait_For_Element("CoPayment1txtmincopay107");
			SendKeys_TO_Object_SC("CoPayment1txtmincopay107",temp2);
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11;
			JavascriptExecutorClick("imgCancelOf_5");
			Wait_For_Element("CoPayment1txtmincopay107");
			Clear_Object              ("CoPayment1txtmincopay107");
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee107",temp2);
			Thread.sleep(100);
		}
	}


	//stepped_Copay_retail30
	public static void stepped_Copay_retail30(String resv) throws Exception
	{
		System.out.println("SteppedCopay_VA _retail30 - Start");
		 Thread.sleep(500);
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			//Retial
			Wait_For_Element("Rbtn_Retail_CoPayment1_0");
			RadioButton_IsSelected ("Rbtn_Retail_CoPayment1_0");
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay102")));
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee102",pctvalue); // Retail
			Thread.sleep(100);
			if (minvalue >0)
			{//retail
				SendKeys_TO_Object_SC	  ("CoPayment1txtmincopay102",String.valueOf(minvalue));
				Thread.sleep(100);
			}//retail
			SendKeys_TO_Object_SC		  ("CoPayment1txtmaxcopay102",maxvalue);
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
		//Retail
			SendKeys_TO_Object_SC		  ("CoPayment1txtmincopay102",temp2);
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11 ;
			Clear_Object              ("CoPayment1txtmincopay102");
			//Retail//Retail - CoPayment1txtcoinsurancee102
			SendKeys_TO_Object_SC		  ("CoPayment1txtcoinsurancee102",temp2);
			Thread.sleep(100);
		}
	}

	//stepped_Copay_retail60
	public static void stepped_Copay_retail60(String resv) throws Exception
	{
		System.out.println("SteppedCopay_VA _retail60 - Start");
		 Thread.sleep(500);
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			Wait_For_Element("Rbtn_Retail_CoPayment1_1");
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_1"); //Retial
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay103")));
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee103",pctvalue); // Retail
			Thread.sleep(100);			
			if (minvalue >0)
			{//retail
				SendKeys_TO_Object_SC("CoPayment1txtmincopay103",String.valueOf(minvalue));
				Thread.sleep(100);
			} //retail
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay103",maxvalue);
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";//Retail
			SendKeys_TO_Object_SC("CoPayment1txtmincopay103",temp2);
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11 ;
			Clear_Object              ("CoPayment1txtmincopay103"); //Retail//Retail
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee103",temp2);
			Thread.sleep(100);
		}
	}
		
	//stepped_Copay_retail90
	public static void stepped_Copay_retail90(String resv) throws Exception
	{
		System.out.println("SteppedCopay_VA _retail90 - Start");
		 Thread.sleep(500);
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();			
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_2");
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay104")));
			Thread.sleep(100);
			// PCT Value
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee104",pctvalue);
			// Retail
			if (minvalue >0)
			{
				SendKeys_TO_Object_SC("CoPayment1txtmincopay104",String.valueOf(minvalue));				
			}
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay104",maxvalue);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmincopay104")));
			SendKeys_TO_Object_SC("CoPayment1txtmincopay104",temp2);			
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11;
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtcoinsurancee104")));			
			Clear_Object              ("CoPayment1txtmincopay104");			
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee104",temp2);
		}
		Thread.sleep(100);
	}

	public static void UpdateUniqueCopayDetails(int RowNo, String CopayAmount) throws Exception
	{
		try
		{
			System.out.println("UpdateUniqueCopayDetails $ Method Started  for "+RowNo);
			WebDriverWait wait2 = new WebDriverWait(driver, 120);
			WebElement we1= wait2.until(ExpectedConditions.visibilityOfElementLocated(ObjectMap.getLocator("ViewDrugCopay"))); //ContentPlaceHolder1_lgnDrugcopay
			boolean status = we1.isDisplayed();
			if (status)
			{
				WebElement element = driver.findElement(By.id("ContentPlaceHolder1_gvViewDrugCopay_btnEditDrugcopay_" + RowNo));
				wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("ContentPlaceHolder1_gvViewDrugCopay_btnEditDrugcopay_" + RowNo)));
				if (element.isDisplayed())
				{
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].click();", element);
					Thread.sleep(1000);//for clicking the edit icon
				}
				//Navigating to UpdateUniqueCopayDetails page
				wait2.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("btnSave")));
				if (CopayAmount.contains("Min"))
				{
					String[] temp21 = CopayAmount.split(",");
					String pctvalue = temp21[0].replace("%", "").trim();
					int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
					String temp_minvalue =minvalue + ".00"; 
					String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
					maxvalue = maxvalue + ".00";
					Thread.sleep(500);				wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("EdtCopayOption")));
					SelectByVisibleText("EdtCopayOption","Coinsurance Range");// we are selecting cost share
					wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("Edt_MaxCopay")));
					Thread.sleep(100);
					SendKeys_TO_Object_SC		  ("EdtCoinsurance",pctvalue);
					Thread.sleep(100);
					if (minvalue >0)
					{
						SendKeys_TO_Object_SC	  ("Edt_MinCopay",temp_minvalue);
						Thread.sleep(100);
					}
					SendKeys_TO_Object_SC        ("Edt_MaxCopay",maxvalue);//doubt need to ask sankar
					Thread.sleep(100);
				}
				else if(CopayAmount.contains("$"))
				{				
					SelectByVisibleText("EdtCopayOption","Flat Amount");
					Thread.sleep(100);
					String temp11 = CopayAmount.replace("$", "").trim();
					String temp2=temp11 + ".00";
					SendKeys_TO_Object_SC("Edt_Copay",temp2);
					Thread.sleep(100);
				}
				else if (CopayAmount.contains("%"))
				{
					SelectByVisibleText("EdtCopayOption","Flat Amount");
					Thread.sleep(100);
					String temp11 = CopayAmount.replace("%", "").trim();
					String temp2=temp11;
					Clear_Object              ("Edt_Copay");
					SendKeys_TO_Object_SC("EdtCoinsurance",temp2);
					Thread.sleep(100);
				}
				//Thread.sleep(500);
				//wait2.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("btnSave"))); //Save Button// Commented by Madhu
				//wait2.until(ExpectedConditions.elementToBeClickable(ObjectMap.getLocator("btnSave")));     //Save Button// Commented by Madhu
				Thread.sleep(500);
				boolean Save_done = false;
				while (Save_done == false)
				{
					JavascriptExecutorClick("btnSave");
					WebDriverWait wait11 = new WebDriverWait(driver, 120);
					try
					{
						wait11.until(ExpectedConditions.alertIsPresent());
						Alert a2=driver.switchTo().alert();
						a2.accept();
						WebElement WEE1 =  wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("TabDrugCopay"))); //Wait for "Drug Copay" Button which is available in Unique copay page
						Save_done       =  WEE1.isDisplayed();
						System.out.println("Unique Copay Save - Button Save: Clicked: " + Save_done);
					}
					catch(Exception alex)
					{
						System.out.println("Unique Copay Save - Error: " + alex);
					}
				}			
				/*try 
				{		        
			        Alert alert = driver.switchTo().alert();
			        alert.accept();
			    } 
				catch (Exception e) 
				{
			        System.out.println("Exception Not Present");
			    } */
				wait2.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("TabDrugCopay")));
		    	//Thread.sleep(1000);
		    	System.out.println("UpdateUniqueCopayDetails  ###   Method Ended for "+RowNo);
			} 
			ExecutionStatus=true;		
		}
		catch(Exception eUpdateUniqueCopayDetails)
		{
			try 
			{		        
		        Alert alert = driver.switchTo().alert();
		        alert.accept();
		    } 
			catch (Exception e) 
			{
		        System.out.println("Exception Not Present");
		    } 
			
			System.out.println("Error in UpdateUniqueCopayDetails: " + eUpdateUniqueCopayDetails);
			ExecutionStatus=false;
		}

	} 

	public static void Refresh_Page() throws Exception
	{
		/*WebElement Page_Logo;
		boolean Page_Logo_Displayed=true;*/
		
		
		//Page_Logo = wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("BServeLogo")));
		//Page_Logo = wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='/Images/benefitServLogo.jpg']")));
		//img[@src='/Images/benefitServLogo.jpg']
		//WebElement Logo = driver.findElement(By.xpath("//img[@src='/Images/benefitServLogo.jpg']"));		
		//Page_Logo_Displayed=driver.findElement(By.xpath("//img[@src='/Images/benefitServLogo.jpg']")).isDisplayed();
		//Page_Logo_Displayed =Page_Logo.isDisplayed();
		//Page_Logo = driver.findElement(By.xpath("//img[@src='/Images/benefitServLogo.jpg']"));
		//Page_Logo = driver.findElement(By.xpath("//img[@src='/Images/benefitServLogo.jpg']"));
		
		/*String PageSource = driver.getPageSource();
		if (PageSource.contains("System.Web.UI."))
		{
			System.out.println("Benefit Serv Logo is not present need to refresh");
			driver.navigate().refresh();
			Thread.sleep(2000);
		}
		else
		{
			System.out.println("Benefit Serv Logo is present");
		}*/
		
		try
		{
			Page_Logo_Displayed = driver.findElement(By.xpath("//img[@src='/Images/benefitServLogo.jpg']")).isDisplayed();
		}
		catch(Exception BalnkExcp)
		{		
			//Page_Logo_Displayed =Page_Logo.isDisplayed();		
			driver.navigate().refresh();
			Thread.sleep(5000);
			//driver.executeScript("location.reload()");
			/*driver.navigate().refresh();
			driver.get(driver.getCurrentUrl());
			driver.navigate().to(driver.getCurrentUrl());
			driver.findElement(By.id("Contact-us")).sendKeys(Keys.F5); 
			driver.executeScript("history.go(0)");*/
			Close_Unexpected_Alert();
			//wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("ImgForm1")));
			wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='/Images/benefitServLogo.jpg']")));
			Close_Unexpected_Alert();
		}
		/*catch (Exception Ref_Exp)
		{
			System.out.println("Page Refresh Error:" + Ref_Exp);
		}	*/	
	}

	public static void Close_Unexpected_Alert()
	{
		try
		{
			Unexpected_Alert=driver.switchTo().alert();			
			Unexpected_Alert.accept();
			Thread.sleep(1000);
		}
		catch(Exception UN_Ex_A)
		{ 
			System.out.println(UN_Ex_A);
		}
	}


	public static void WriteLog(String Msg) throws IOException
	{
		BW.write(Msg);
		BW.newLine();
	}

	public static boolean Check_File_Download(long Before_Download_Time) throws IOException
	{
		//Download_Path
		File dir = new File(Download_Path);
		File[] files = dir.listFiles();
		String LastModifiedFileName = null;
		if (files == null || files.length == 0) 
		{
			return false;
		}
		File lastModifiedFile = null;
		for (int i = 1; i < files.length; i++) 
		{
			if (Before_Download_Time < files[i].lastModified()) 
			{
				lastModifiedFile = files[i];
				LastModifiedFileName = files[i].getName();
			}
		}
		if (lastModifiedFile!=null)
		{
			WriteLog(LastModifiedFileName);
			return true;
		}
		else
		{
			return false;
		}

	}
	public static void retail30_Tier4(String resv) throws Exception
	{
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_0"); //Retial													 
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay102")));
			Thread.sleep(100);
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_2"); //Paper IN													
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay104"))); //CoPayment1txtcoinsurancee104
			Thread.sleep(100);
			JavascriptExecutorClick("Rbtn_Retail_CoPayment1_3"); // Paper Out		
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay105")));
			//Thread.sleep(1000);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee102",pctvalue); // Retail
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee104",pctvalue); // Paper IN
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee105",pctvalue); // Paper Out
			Thread.sleep(100);

			if (minvalue >0)
			{
				driver.findElement(ObjectMap.getLocator("CoPayment1txtmincopay102")); //retail
				SendKeys_TO_Object_SC("CoPayment1txtmincopay102",String.valueOf(minvalue));
				Thread.sleep(100);
				driver.findElement(ObjectMap.getLocator("CoPayment1txtmincopay104")); //paper in
				SendKeys_TO_Object_SC   ("CoPayment1txtmincopay104",String.valueOf(minvalue));
				Thread.sleep(100);
				driver.findElement   (ObjectMap.getLocator("CoPayment1txtmincopay105")); //paper out
				SendKeys_TO_Object_SC	 ("CoPayment1txtmincopay105",String.valueOf(minvalue));
				Thread.sleep(100);
			}
			//retail
			SendKeys_TO_Object_SC		  ("CoPayment1txtmaxcopay102",maxvalue);
			Thread.sleep(100);//paper IN
			SendKeys_TO_Object_SC		  ("CoPayment1txtmaxcopay104",maxvalue);
			Thread.sleep(100); //Paper OOUT
			SendKeys_TO_Object_SC		  ("CoPayment1txtmaxcopay105",maxvalue);
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";//Retail
			SendKeys_TO_Object_SC		  ("CoPayment1txtmincopay102",temp2);
			Thread.sleep(100);// Paper IN
			SendKeys_TO_Object_SC		  ("CoPayment1txtmincopay104",temp2);
			Thread.sleep(100);
			//Paper Out
			SendKeys_TO_Object_SC		  ("CoPayment1txtmincopay105",temp2);
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			//String temp2=temp11 + ".00";
			String temp2=temp11;
			Clear_Object              ("CoPayment1txtmincopay102"); //Retail
			//Retail
			SendKeys_TO_Object_SC		  ("CoPayment1txtcoinsurancee102",temp2);
			Thread.sleep(100);			
			Clear_Object              ("CoPayment1txtmincopay104"); // Paper IN
			//Paper IN
			SendKeys_TO_Object_SC		  ("CoPayment1txtcoinsurancee104",temp2);
			Thread.sleep(100);			
			Clear_Object              ("CoPayment1txtmincopay105"); // Paper Out
			//Paper OUT
			SendKeys_TO_Object_SC		  ("CoPayment1txtcoinsurancee105",temp2);
			Thread.sleep(100);
		}
	}
	public static void mail30_Tier4(String resv) throws Exception
	{
		if (resv.contains("Min"))
		{
			String[] temp21 = resv.split(",");
			String pctvalue = temp21[0].replace("%", "").trim();
			int minvalue = Integer.parseInt(temp21[1].replace("Min", "").replace("$", "").replace(":", "").trim());
			String maxvalue = temp21[2].replace("Max", "").replace("$", "").replace(":", "").trim();
			Wait_For_Element("CoPayment1txtmincopay104");
			JavascriptExecutorClick("RbtnRetial_1_4_1");
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("CoPayment1txtmaxcopay103")));
			Thread.sleep(100);
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee103",pctvalue);
			Thread.sleep(100);
			if (minvalue >0)
			{
				SendKeys_TO_Object_SC("CoPayment1txtmincopay103",String.valueOf(minvalue));
				Thread.sleep(100);
			}
			SendKeys_TO_Object_SC("CoPayment1txtmaxcopay103",maxvalue);
			Thread.sleep(100);
		}
		else if(resv.contains("$"))
		{
			String temp11 = resv.replace("$", "").trim();
			String temp2=temp11 + ".00";
			SendKeys_TO_Object_SC("CoPayment1txtmincopay103",temp2);
			Thread.sleep(100);
		}
		else if (resv.contains("%"))
		{
			String temp11 = resv.replace("%", "").trim();
			String temp2=temp11 ;
			Clear_Object              ("CoPayment1txtmincopay103");
			SendKeys_TO_Object_SC("CoPayment1txtcoinsurancee103",temp2);
			Thread.sleep(100);
		}
	}
	public static String GetValueFromExcel(String SheetName, int col, int row) throws InterruptedException
	{
		String Ret_Value =null; 
		int Wait_Limit=0;		
		//Sheet sheet = WB.getSheet(SheetName);				
		while ((Ret_Value == "" ||  Ret_Value == null) && Wait_Limit<4)
		{
			Thread.sleep(1000);
			//Ret_Value 	 = WB.getSheet(SheetName).getCell(col,row).getContents().trim();
			Cell cell = WB.getSheet(SheetName).getCell(col,row);
			if (cell != null)
			{
				Ret_Value = WB.getSheet(SheetName).getCell(col,row).getContents().trim();
			}
			Wait_Limit   = Wait_Limit+1;
		}			
		return Ret_Value;			
	}
	
	public static String Read_Excel(int Sheet_no, int col, int row) throws InterruptedException
	{
		String Ret_Value =null; 
		int Wait_Limit=0;
		if (Sheet_no==1)
		{
			while (Ret_Value == null && Wait_Limit<4)
			{
				Thread.sleep(1000);
				Ret_Value=RTM_Sheet1.getCell(col,row).getContents().trim();
				Wait_Limit=Wait_Limit+1;
			}
		}
		else if(Sheet_no==2)
		{
			while (Ret_Value==null && Wait_Limit<4)
			{
				Thread.sleep(1000);
				Ret_Value=RTM_Sheet2.getCell(col,row).getContents().trim();
				Wait_Limit=Wait_Limit+1;
			}
		}
		return Ret_Value;	
	}
	public static void Wait_For_Element(String ObjectMapName) throws Exception
	{			
				wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator(ObjectMapName)));
				System.out.println(" Waiting For Element: "+ ObjectMapName);
	}
	public static void IsDisplayed(String ObjectMapName) throws Exception
	{		
				Thread.sleep(1000);
				wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator(ObjectMapName)));
				System.out.println(" Waiting For Element: "+ ObjectMapName);
	}
	
	public static void Create_Log_File() throws IOException
	{
		dateFormat=new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		Date date=new Date();
		String date21=dateFormat.format(date);
		date21=date21.replace(":", "_");
		String Log_File_Path = Result_Folder_Path + "\\Logs_" + date21 + ".txt";
		File f3 = new File(Log_File_Path);
		f3.createNewFile();
		fw = new FileWriter(Log_File_Path);
		BW = new BufferedWriter(fw);
		sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}
	
	public static boolean Finalize() throws Exception
	{
		try{
			Thread.sleep(1000);
			System.out.println("Finalize  ###   Method Started");
			//Click Finalize
			Wait_For_Element("btnFinalize");
			JavascriptExecutorClick("btnFinalize");
			Thread.sleep(500);
			Wait_For_Element("btnProceed");
			JavascriptExecutorClick("btnProceed");
			Wait_For_Element("ContainerHeader");
			Thread.sleep(6000);
			WriteLog("Finalize Clicked");	
			System.out.println("Finalize  ###   Method Ended");
			ExecutionStatus =true;
		}
		catch(Exception Finalize_Error)
		{
			System.out.println(" Error:"+ Finalize_Error);		
			ExecutionStatus =false;	
		}
		return ExecutionStatus;
	}	
	
	public static boolean ClickPlanDesginName() throws Exception
	{
		try
		{	
			System.out.println("ClickPlanDesginName  ###   Method Started ");
			//Select Plan Desgin Name
			
			Plan_Design_Name= GetValueFromExcel("CreatePlanDesign", 1, x);
			Wait_For_Element("Edt_BPDName");
			List <WebElement> PlanDesginNameItems = driver.findElements(By.linkText(Plan_Design_Name));
			driver.switchTo().window(Prent_WNDID);
			
			new Actions(driver).moveToElement(PlanDesginNameItems.get(0)).click().build().perform();			
			
			System.out.println("ClickPlanDesginName  ###   Method Ended");
			ExecutionStatus =true;
		}
		catch(Exception PlanDesign_Exp)
		{
			System.out.println(PlanDesign_Exp);
			ExecutionStatus =false;
		}
		return ExecutionStatus;
	}
	
	//Click Settings Icon - Old Code
	public static boolean ClickSettingsIcon_OldCode() throws Exception
	       {
		try{
	              Prent_WNDID=driver.getWindowHandle();
	              //Click Settings Icon - Do this After clicking the Settings Icon                  
	              Thread.sleep(3000);
	              wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("ContentPlaceHolder1_scenarioattributes")));  // Wait for Settings Icon                
	              driver.findElement(By.id("ContentPlaceHolder1_scenarioattributes")).click();                    //Click Settings Icon
	              Thread.sleep(4000);
	              String Child_WNDID = Prent_WNDID;
	              while (Child_WNDID.equalsIgnoreCase(Prent_WNDID)==true)
	              {
	                     for (String WndHandle: driver.getWindowHandles())
	                     {
	                           driver.switchTo().window(WndHandle);     
	                           Child_WNDID=driver.getWindowHandle();                                              
	                           if (Child_WNDID.equalsIgnoreCase(Prent_WNDID)==false)
	                                  break;
	                     }
	                     Thread.sleep(1000);
	                     Child_WNDID = driver.getWindowHandle();
	                     System.out.println("Prent_WNDID: " + Prent_WNDID);
	                     System.out.println("Child_WNDID: " + Child_WNDID);
	              }
	              
	              String Base_Plan = GetValueFromExcel("CreatePlanDesign", 5, x);
	              Plan_Design_Name = GetValueFromExcel("CreatePlanDesign", 1, x);
	              SendKeys_TO_Object("EdtClientSpecific",Base_Plan);
	              SendKeys_TO_Object("EdtTuftsSpecific",Plan_Design_Name);
	              Thread.sleep(100);
	              JavascriptExecutorClick("BtnSaveAttributes");
	              wait3.until(ExpectedConditions.alertIsPresent());                    
	              Alert Pop_Alert = driver.switchTo().alert();
	              Pop_Alert.accept();
	              Wait_For_Element("btnClose");
	              JavascriptExecutorClick    ("btnClose");
	              driver.close();            
	              driver.switchTo().window(Prent_WNDID);
	              System.out.println("ClickSettingsIcon  ###   Method Ended");
	              ExecutionStatus =true;
	    }
					catch(Exception ClickSettingsIcon_Error)
			      		{
			      			System.out.println(" Error:"+ ClickSettingsIcon_Error);		
			      			ExecutionStatus =false;	
			      		}
			return ExecutionStatus;
	}

	
	public static boolean ClickSettingsIcon() throws Exception
	{
		try{	
				System.out.println("ClickSettingsIcon  ###   Method Started ");
				Set<String> oldWindows = driver.getWindowHandles(); // get windows handles before clicking link
				JavascriptExecutorClickWo("Scenarioattributes");
				Set<String> allWindows = driver.getWindowHandles();
				allWindows.removeAll(oldWindows);
				String newWindow = allWindows.iterator().next();
				driver.switchTo().window(newWindow);/*		
  			Prent_WNDID=driver.getWindowHandle();		
  			JavascriptExecutorClickWo("Scenarioattributes");		
				for (String WndHandle: driver.getWindowHandles())
				{
				       if(!WndHandle.equals(Prent_WNDID))
				       {
				    	    driver.switchTo().window(WndHandle);
				    	    System.out.println("Current Window Handle: " + WndHandle);	*/			    	   
							String Base_Plan = GetValueFromExcel("CreatePlanDesign", 5, x);
							Plan_Design_Name = GetValueFromExcel("CreatePlanDesign", 1, x);
										
							SendKeys_TO_Object_SC("EdtClientSpecific",Base_Plan);
							SendKeys_TO_Object_SC("EdtTuftsSpecific",Plan_Design_Name);
							
							JavascriptExecutorClick("BtnSaveAttributes");
							
							wait3.until(ExpectedConditions.alertIsPresent());			
							Alert Pop_Alert = driver.switchTo().alert();
							Pop_Alert.accept();
							
							Wait_For_Element("btnClose");
							JavascriptExecutorClick    ("btnClose");
							driver.close();		
							driver.switchTo().window(Prent_WNDID);
							System.out.println("ClickSettingsIcon  ###   Method Ended");
							ExecutionStatus =true;
				      /* }				      
				}	*/					
		}
		catch(Exception ClickSettingsIcon_Error)
		{
			System.out.println(" Error:"+ ClickSettingsIcon_Error);		
			ExecutionStatus =false;	
		}
		return ExecutionStatus;
	}
		
	private static WebElement findElement(By id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void OnlyForQuickExecution() throws Exception{
		//only for Quick Execution
		SendKeys_TO_Object("Edt_PlanDesignName",Plan_Design_Name);
		Thread.sleep(1000);
		JavascriptExecutorClick("BtnSearch");
		Thread.sleep(3000); 
		}

	public static boolean InitiateMemberRequest() throws Exception
	{
		System.out.println("InitiateMemberRequest  ###   Method Started");
		try{
			//Click Initiate Member Request for Mapping the Members
			boolean Member_Mapped=false;
			boolean Member_Mapped_temp=false;
			//Thread.sleep(3000);					
			while (Member_Mapped==false)
			{																					
				JavascriptExecutorClick("BtnMemberRequest");								
				wait4.until(ExpectedConditions.visibilityOfElementLocated(ObjectMap.getLocator("ListState")));
				SelectByVisibleText("ListState","TX");
				SendKeys_TO_Object("EdtZipcode","12345");
				JavascriptExecutorClick("BtoK");
				String Alt_Msg="";
				WebElement MemberRequest;
				while (Alt_Msg =="")
				{
					MemberRequest = wait4.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("BtMemberMapping")));
					driver.switchTo().window(Prent_WNDID);
					JavascriptExecutorClick("BtMemberMapping");
					Alert MemAlert = wait4.until(ExpectedConditions.alertIsPresent());
					Alt_Msg = MemAlert.getText();
				}
				Alert Accept_Member_Sce = driver.switchTo().alert();
				Accept_Member_Sce.accept();
				MemberRequest = wait4.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("BtMemberMapping")));
				Member_Mapped_temp = MemberRequest.isEnabled();
				WebElement Regenerate_Sce_Btn;
				if (Member_Mapped_temp==true)
				{									
					JavascriptExecutorClick("TabScenario");
					Regenerate_Sce_Btn = wait4.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("btnFirstRun")));
					Member_Mapped =Regenerate_Sce_Btn.isDisplayed();
					if (Member_Mapped==false)
					{
						Refresh_Page();
						Regenerate_Sce_Btn = wait4.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("btnFirstRun")));
						Member_Mapped =Regenerate_Sce_Btn.isDisplayed();
					}
				}				 
			}          
			
			//Refresh Page			
			Refresh_Page();
			ExecutionStatus =true;
		}
		catch(Exception InitiateMemberRequest_Error)
		{
			System.out.println(" Error:"+ InitiateMemberRequest_Error);		
			ExecutionStatus =false;	
		}
		return ExecutionStatus;
	}

	public static boolean GenerateScenario() throws Exception
	{
		System.out.println("GenerateScenario  ###   Method Started");
		try{
			//Click Generate Scenario
			Wait_For_Element("btnFirstRun");
			JavascriptExecutorClick( "btnFirstRun");
			Thread.sleep(6000);
			
			//Thread.sleep(6000);
			boolean Click_MemberMapping = false;
			while (Click_MemberMapping==false)
			{
				try
				{
					WebElement we7= wait4.until(ExpectedConditions.visibilityOfElementLocated(ObjectMap.getLocator("TabMember")));					
					Click_MemberMapping= we7.isEnabled();					
					if (Click_MemberMapping==true)
					{
						//Thread.sleep(3000);
						JavascriptExecutorClick("TabMember");						
						WebElement Mem_Reg = wait4.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("BtnMemberRequest")));
						Click_MemberMapping =Mem_Reg.isDisplayed();
						Take_A_Screenshot("GenerateScenario");
						System.out.println("Click_MemberMapping= Mem_Reg.isDisplayed" );
					}
				}
				catch(Exception e12)
				{
					System.out.println("Click Member Map Tab: " + e12);
				}
			}
			WriteLog("Generate Scenario - Done");
			System.out.println("GenerateScenario  ###   Method Ended");
			ExecutionStatus =true;
		}
		catch(Exception GenerateScenario_Error)
		{
			System.out.println(" Error:"+ GenerateScenario_Error);		
			ExecutionStatus =false;	
		}
		return ExecutionStatus;
	}

	public static boolean Download_Excel_Report() throws Exception
	{
		System.out.println("Download_Excel_Report  ###   Method Started");
		boolean File_Downloaded=false;
		try
		{
			//Get Current Time before Clicking the Download Icon
			now = new Date();
			long Time_BeforeDownload = now.getTime();
			//String DateTime_Before_Download = sdfDate.format(now);
			//Click Download Icon			
			wait4.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("BtnExcelExportIcon")));
			boolean excel_IconDownloaded=false;
			int downloadTry=1;
			driver.switchTo().window(Prent_WNDID);			
			while(excel_IconDownloaded==false && downloadTry<5)
			{
				JavascriptExecutorClick("BtnExcelExportIcon");
				Thread.sleep(6000);
				File_Downloaded=false;
				Rob1=new Robot();
				WebElement element;			
				int Try_Count=0;
				while (File_Downloaded==false && Try_Count<5)
				{
			    	driver.switchTo().parentFrame();
			    	Thread.sleep(5000);		    	
			    	//JavascriptExecutorClick("LblPageFooter");		    // replaced since it keeps on clicking and no events happened
			    	element=driver.findElement(By.xpath("//table/tbody/tr[@class = 'PageFooter']/td/img"));
			    	Actions act2=new Actions(driver);
			    	act2.moveToElement(element).click().build().perform();
			    	//ActionsDriverClick("LblPageFooter");//replaced by original code
			    	Thread.sleep(3000);	
			    	Rob1.keyPress(KeyEvent.VK_TAB);
			    	//Rob1.keyRelease(KeyEvent.VK_TAB);
			    	Thread.sleep(500);
			    	Rob1.keyPress(KeyEvent.VK_TAB);
			    	//Rob1.keyRelease(KeyEvent.VK_TAB);
			    	Thread.sleep(500);
			    	Rob1.keyPress(KeyEvent.VK_ENTER);
			    	//Rob1.keyRelease(KeyEvent.VK_ENTER);
			    	Thread.sleep(6000);		    	
			    	//Check whether file has downloaded or not...
			    	File_Downloaded=Check_File_Download(Time_BeforeDownload);
			    	excel_IconDownloaded=File_Downloaded;
			    	Try_Count=Try_Count+1;		    	
				}
				downloadTry=downloadTry+1;
			}
			
			//close download notification
			WebElement element=driver.findElement(By.xpath("//table/tbody/tr[@class = 'PageFooter']/td/img"));
	    	Actions act2=new Actions(driver);
	    	act2.moveToElement(element).click().build().perform();
	    	Thread.sleep(1000);	
	    	Rob1.keyPress(KeyEvent.VK_TAB);
	    	Thread.sleep(500);
	    	Rob1.keyPress(KeyEvent.VK_TAB);
	    	Thread.sleep(500);
	    	Rob1.keyPress(KeyEvent.VK_TAB);
	    	Thread.sleep(500);
	    	Rob1.keyPress(KeyEvent.VK_TAB);
	    	Thread.sleep(500);
	    	Rob1.keyPress(KeyEvent.VK_ENTER);
	    	Thread.sleep(3000);	
			
			WriteLog("--------------------------------- Done");
			now = new Date();
			strDate = sdfDate.format(now);
			WriteLog("Ends RTM at : " + strDate);
			System.out.println("Download_Excel_Report  ###   Method Ended");
			ExecutionStatus =true;
		}
		catch(Exception Download_Excel_Report_Error)
		{
			System.out.println(" Error:"+ Download_Excel_Report_Error);		
			ExecutionStatus =false;	
		}
		if (File_Downloaded==false)
		{
			ExecutionStatus =false;
		}
		return ExecutionStatus;
	}
	public static boolean Download_Excel_Report_OldCode() throws Exception
	{
		System.out.println("Download_Excel_Report  ###   Method Started");
		boolean File_Downloaded=false;
		try
		{
			//Get Current Time before Clicking the Download Icon
			now = new Date();
			long Time_BeforeDownload = now.getTime();
			//String DateTime_Before_Download = sdfDate.format(now);
			//Click Download Icon
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("BtnExcelExportIcon")));
			boolean excel_IconDownloaded=false;
			int downloadTry=1;
			driver.switchTo().window(Prent_WNDID);			
			while(excel_IconDownloaded==false && downloadTry<5)
			{
				JavascriptExecutorClick("BtnExcelExportIcon");
				Thread.sleep(6000);
				File_Downloaded=false;
				Rob1=new Robot();
				WebElement element;			
				int Try_Count=0;
				while (File_Downloaded==false && Try_Count<5)
				{
			    	driver.switchTo().parentFrame();
			    	Thread.sleep(5000);		    	
			    	//JavascriptExecutorClick("LblPageFooter");		    // replaced since it keeps on clicking and no events happened
			    	element=driver.findElement(By.xpath("//table/tbody/tr[@class = 'PageFooter']/td/img"));
			    	Actions act2=new Actions(driver);
			    	act2.moveToElement(element).click().build().perform();
			    	//ActionsDriverClick("LblPageFooter");//replaced by original code
			    	Thread.sleep(3000);	
			    	Rob1.keyPress(KeyEvent.VK_TAB);
			    	Thread.sleep(500);
			    	Rob1.keyPress(KeyEvent.VK_TAB);
			    	Thread.sleep(500);
			    	Rob1.keyPress(KeyEvent.VK_ENTER);
			    	Thread.sleep(6000);		    	
			    	//Check whether file has downloaded or not...
			    	File_Downloaded=Check_File_Download(Time_BeforeDownload);
			    	excel_IconDownloaded=File_Downloaded;
			    	Try_Count=Try_Count+1;		    	
				}
				downloadTry=downloadTry+1;
			}
			
			//close download notification
			WebElement element=driver.findElement(By.xpath("//table/tbody/tr[@class = 'PageFooter']/td/img"));
	    	Actions act2=new Actions(driver);
	    	act2.moveToElement(element).click().build().perform();
	    	Thread.sleep(1000);	
	    	Rob1.keyPress(KeyEvent.VK_TAB);
	    	Thread.sleep(500);
	    	Rob1.keyPress(KeyEvent.VK_TAB);
	    	Thread.sleep(500);
	    	Rob1.keyPress(KeyEvent.VK_TAB);
	    	Thread.sleep(500);
	    	Rob1.keyPress(KeyEvent.VK_TAB);
	    	Thread.sleep(500);
	    	Rob1.keyPress(KeyEvent.VK_ENTER);
	    	Thread.sleep(3000);	
			
			WriteLog("--------------------------------- Done");
			now = new Date();
			strDate = sdfDate.format(now);
			WriteLog("Ends RTM at : " + strDate);
			System.out.println("Download_Excel_Report  ###   Method Ended");
			ExecutionStatus =true;
		}
		catch(Exception Download_Excel_Report_Error)
		{
			System.out.println(" Error:"+ Download_Excel_Report_Error);		
			ExecutionStatus =false;	
		}
		if (File_Downloaded==false)
		{
			ExecutionStatus =false;
		}
		return ExecutionStatus;
	}
		
	public static void Create_File_Path()
	{
		Working_Dir = System.getProperty("user.dir");
		System.out.println("Working_Dir: " + Working_Dir );
		Ab_File1 = new File(Working_Dir);
		Parent_Dir = Ab_File1.getParent();		
		System.out.println(Parent_Dir);
		//Input_File_Path = Parent_Dir + "\\Input Files\\InputFile.xls";
		//Input_File_Path = Parent_Dir + "\\Input Files\\InputFile_new.xls";
		Input_File_Path = Parent_Dir + "\\Input Files\\InputFile.xls";
		//InputFile_new
		System.out.println(Input_File_Path);
		JARS_File_Path = Parent_Dir + "\\Jars 3.4.0";
		System.out.println(JARS_File_Path);
		IE_Driver_Path = JARS_File_Path + "\\IEDriverServer_Win32_3.4.0\\IEDriverServer.exe"; 
		System.out.println(IE_Driver_Path);
		RTM_Folder_Path = Parent_Dir  + "\\RTM";
		System.out.println(RTM_Folder_Path);
		Result_Folder_Path = Parent_Dir + "\\Results";
		Download_Path = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads";
		System.out.println(Download_Path);		
	}
	
	public static void Create_IE_Driver()
	{
		System.setProperty("webdriver.ie.driver", IE_Driver_Path);
		driver = new InternetExplorerDriver();
	}
	
	public static String Get_Current_Date_Time()
	{		
		now = new Date();
		return sdfDate.format(now);				
	}
	
	public static void Open_Main_Input_Sheet() throws BiffException, IOException
	{
		f1=new File(Input_File_Path);
		WB=Workbook.getWorkbook(f1);		
		WS =WB.getSheet(0);
		BServe_URL = WS.getCell(0, 1).getContents();
		User_Name = WS.getCell(1, 1).getContents();
		Pawd = WS.getCell(2, 1).getContents();
	}
	
	public static void Open_RTM_Folder_Path()
	{
		RTM_FPath = new File(RTM_Folder_Path);
		RTM_files = RTM_FPath.listFiles();
	}
	
	public static void DeleteCookies()
	{
		driver.manage().deleteAllCookies();
	}
	
	public static void Open_Current_RTM(String RTM_File_Name)
	{		
		System.out.println(RTM_File_Name);
		RTM_File_FullPath = RTM_Folder_Path  + "\\" + RTM_File_Name;		
	}
	
	public static boolean Open_Current_RTM_File() throws IOException
	{
		try
		{
			File sRTM_File=new File(RTM_File_FullPath);
			Workbook RTMWB=Workbook.getWorkbook(sRTM_File);
			Thread.sleep(2000);
			RTM_Sheet1 = RTMWB.getSheet(0);
			Thread.sleep(2000);
			RTM_Sheet2 = RTMWB.getSheet(1);
			return true;
		}
		catch(Exception Excel_Error)
		{
			System.out.println("Exception Excel_Error");
			System.out.println("Excel File format is not valid");
			WriteLog("Excel File Format is Incorrect");
			return false;
		}
	}
	public static boolean Launch_IE_Browser() throws Exception
	{
		System.out.println("Launch_IE_Browser  ###   Method Started");
		try{
				driver.get(BServe_URL);
				//Maximize Browser
				Maximize_Browser();		
				
				//Get Parent window Handle
				Get_Parent_window_Handle();		
				
				System.out.println("Parent Window ID: " + Prent_WNDID);
				//overridelink
				if (iteration==1)
				{
					driver.navigate().to ("javascript:document.getElementById('overridelink').click()");
				}
				
				wait3  = new WebDriverWait(driver, 120);
				wait31 = new WebDriverWait(driver, 3);
				System.out.println("Launch_IE_Browser: " + strDate);
				Thread.sleep(500);
				
				Wait_For_Element("LoginUserUserName");
				
				if (driver.findElement(ObjectMap.getLocator("LoginUserUserName")).isDisplayed())
				{
					System.out.println("Launch IE Browser is displayed");
					System.out.println("Launch_IE_Browser  ###   Method Ended");
					ExecutionStatus =true;			
					Take_A_Screenshot( "Launch_IE_Browser");
				}
			}
			catch(Exception Launch_IE_Browser_Error)
			{
				System.out.println("Launch IE Browser Error:"+ Launch_IE_Browser_Error);
				ExecutionStatus =false;			
			}
		return ExecutionStatus;		
	}
	
	public static void Maximize_Browser()
	{
		driver.manage().window().maximize();
	}
	
	public static void Get_Parent_window_Handle()
	{
		Prent_WNDID = driver.getWindowHandle();
	}
	
	public static boolean Login_Bserve() throws Exception
	{
		System.out.println("Login_Bserve  ###   Method Started");
		try{
			System.out.println("Log In - Start");
			System.out.println(driver.findElement(ObjectMap.getLocator("LoginUserUserName")).getCssValue("background"));		
			SendKeys_TO_Object("LoginUserUserName", User_Name);					
			SendKeys_TO_Object("LoginUserPassword",Pawd);
			JavascriptExecutorClick("BtnSubmit");
			Wait_For_Element("lblUserName");
			System.out.println("Login_Bserve  ###   Method Ended");
			ExecutionStatus =true;
			WriteLog("Logged In");
			System.out.println("Logged In - Done");
			Take_A_Screenshot( "Login_Bserve");
		}
		catch(Exception Login_Bserve_Error)
		{
			System.out.println("Launch IE Browser Error:"+ Login_Bserve_Error);		
			ExecutionStatus =false;	
		}		
		return ExecutionStatus;	
	}	
	//##########################################			Utility				##########################
	
	public static void RadioButton_IsSelected(String ObjectMapName) throws Exception
	{
		WebElement RadioButtonTarget = driver.findElement(ObjectMap.getLocator(ObjectMapName));	
		if(RadioButtonTarget.isDisplayed()){
			RadioButtonTarget.click();
			if(RadioButtonTarget .isSelected()){
				System.out.println("Radio Button "+ObjectMapName+" Is Selected");
			}else{			
				JavascriptExecutorClick(ObjectMapName);
				System.out.println("Radio Button "+ObjectMapName+" Is Selected For The Second Time");
			}
		}
	}
	//##########################################			Utility				##########################
		/*// Create a boolean variable to store true/false.
		Boolean is_selected = target.get(0).isSelected();
		// If 'is_selected' is true that means the first radio button is
		// selected.
		if (is_selected == true) {
		// If the first radio button is selected by default then,
		// select the second radio button.
			target.
		} else {
		// If the first radio button is not selected then, click the first
		// radio button.
			target.get(0).click();
		}*/
	//##########################################			Utility				##########################
public static void SelectDesiredLI(String UL_ClassName,String Li_ClassName, String AttributeText) throws Exception
{
	//wait3.until(ExpectedConditions.presenceOfElementLocated(By.className(UL_ClassName)));
	List<WebElement> listOfLiElements= driver.findElement(By.className(UL_ClassName)).findElements(By.className(Li_ClassName));
	//List<WebElement> listOfLiElements= driver.findElement(By.className("level1 static")).findElements(By.className("static"));
	 for(WebElement li : listOfLiElements){
		 String text = li.getText();
		 if(text.equals(AttributeText)) {
			 li.click();
			 Thread.sleep(3000);
		     break;
		 }
	 }
}
//##########################################			Utility				##########################
	public static void WaitTillTabColorChange(String ObjectMapName, String ColorText, String ExpectedColorText) throws Exception
	{		
		String Bg_Color=ColorText;
		boolean color_changed;
		color_changed=false;
		do
		{
			Bg_Color= getCssValueColor(ObjectMapName);	
			Thread.sleep(1000);
			color_changed= Bg_Color.contains(ExpectedColorText);
		} while(color_changed ==false);		
	}
	//##########################################			Utility				##########################	
	public static String getCssValueColor(String ObjectMapName) throws Exception
	{		
		String Bg_Color= driver.findElement(ObjectMap.getLocator(ObjectMapName)).getCssValue("background-image"); //background
		return Bg_Color;	
	}
	//##########################################			Utility				##########################
	public static WebElement isElementPresentW2(String ObjectMapName) throws Exception
	{
		WebElement ele = null;
		for(int i=0;i<10;i++)
		{
			try{
				ele=driver.findElement(ObjectMap.getLocator(ObjectMapName));
				break;
			}
			catch(Exception e)
			{
				try 
				{
					Thread.sleep(1000);
				} 
				catch (InterruptedException e1) 
				{
					System.out.println("Waiting for element to appear on DOM");
				}
			}
		}
		return ele;
	}
	//##########################################			Utility				##########################
	public static WebElement isElementPresentW(String ObjectMapName) throws Exception
	{
		WebElement ele = null;
		for(int i=0;i<10;i++)
		{
			try{
				ele=driver.findElement(ObjectMap.getLocator(ObjectMapName));
				break;
			}
			catch(Exception e)
			{
				try 
				{
					Thread.sleep(1000);
				} 
				catch (InterruptedException e1) 
				{
					System.out.println("Waiting for element to appear on DOM");
				}
			}
		}
		return ele;
	}
	//##########################################			Utility				##########################
	public static WebElement isElementPresent(String ObjectMapName,int time)
	{
		WebElement ele = null;
		for(int i=0;i<time;i++)
		{
			try{
				ele=driver.findElement(ObjectMap.getLocator(ObjectMapName));
				break;
			}
			catch(Exception e)
			{
				try 
				{
					Thread.sleep(1000);
				} 
				catch (InterruptedException e1) 
				{
					System.out.println("Waiting for element to appear on DOM");
				}
			}
		}
		return ele;
	}
	//##########################################			Utility				##########################
	public static void ActionsDriverClick(String ObjectMapName) throws Exception
	{	 	
		wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator(ObjectMapName)));
		WebElement wObject = driver.findElement(ObjectMap.getLocator(ObjectMapName));	
		driver.switchTo().window(Prent_WNDID);
		if (driver.findElement(ObjectMap.getLocator(ObjectMapName)).isDisplayed())
		{
			new Actions(driver).moveToElement(wObject).click().build().perform() ;
			System.out.println(ObjectMapName+" is clicked with ActionsDriverClick");
		}
	}
	//##########################################			Utility				##########################

	public static void SelectByVisibleText(String ObjectMapName,String Text) throws Exception
	{
		Thread.sleep(500);
		wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator(ObjectMapName)));
		if (driver.findElement(ObjectMap.getLocator(ObjectMapName)).isDisplayed())
		{
			Select Drop_Down_1 =new Select(driver.findElement(ObjectMap.getLocator(ObjectMapName)));
			Drop_Down_1.selectByVisibleText(Text);
			System.out.println(ObjectMapName+" is selected  with value:"+Text);
		}		
		else
		{
			System.out.println(ObjectMapName+" is not displayed");	
		}		
	}
	//##########################################			Utility				##########################
	public static void Take_A_Screenshot(String ModuleName)
	{
	// Take screenshot and store as a file format
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try 
		{
		// now copy the  screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, new File(FileName+ModuleName+".jpg"));
		}
		catch (IOException e)
		{ 
				System.out.println(e.getMessage());
		}
	}
	//##########################################			Utility				##########################
	public static  void JavascriptExecutorClickWo(String ObjectMapName) throws Exception{		
		WebElement element = driver.findElement(ObjectMap.getLocator(ObjectMapName));
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(1000);
		System.out.println(ObjectMapName+" is clicked with JS Wo");
		
	}
	//##########################################			Utility				##########################
	public static  void JavascriptExecutorClick(String ObjectMapName) throws Exception{		
		WebElement element = driver.findElement(ObjectMap.getLocator(ObjectMapName));
		wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator(ObjectMapName)));
		if (driver.findElement(ObjectMap.getLocator(ObjectMapName)).isDisplayed())
		{
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(1000);
			System.out.println(ObjectMapName+" is clicked with JS");
		}
	}
	//##########################################			Utility				##########################
	public static void Clear_Object(String ObjectMapName) throws Exception
	{
		wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator(ObjectMapName)));
		if (driver.findElement(ObjectMap.getLocator(ObjectMapName)).isDisplayed())
		{
			driver.findElement(ObjectMap.getLocator(ObjectMapName)).clear();	
			System.out.println(ObjectMapName+" is cleared");
		}
	}
	//##########################################			Utility				##########################
	public static void SendKeys_TO_Object(String ObjectMapName, String IP_Value) throws Exception
	{							
		wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator(ObjectMapName)));
		if (driver.findElement(ObjectMap.getLocator(ObjectMapName)).isDisplayed())
		{
			driver.findElement(ObjectMap.getLocator(ObjectMapName)).clear();
			driver.findElement(ObjectMap.getLocator(ObjectMapName)).sendKeys(IP_Value);	
			System.out.println(ObjectMapName+" is entered with the value: "+IP_Value);
		}
	}
	//##########################################			Utility				##########################
		public static void SendKeys_TO_Object_SC(String ObjectMapName, String IP_Value) throws Exception
		{										
			driver.findElement(ObjectMap.getLocator(ObjectMapName)).clear();
			driver.findElement(ObjectMap.getLocator(ObjectMapName)).sendKeys(IP_Value);		
			System.out.println(ObjectMapName+" is entered with the value: "+IP_Value);
		}
	//##########################################			Utility				##########################
	public static void Click_Object(String ObjectMapName) throws Exception
	{
		wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator(ObjectMapName)));
		if (driver.findElement(ObjectMap.getLocator(ObjectMapName)).isDisplayed())
		{
			driver.findElement(ObjectMap.getLocator(ObjectMapName)).click();
			System.out.println(ObjectMapName+" is clicked");
		}
	}
	//##########################################			Utility				##########################
		public static void Click_ObjectWo(String ObjectMapName) throws Exception
		{
			wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator(ObjectMapName)));
			if (driver.findElement(ObjectMap.getLocator(ObjectMapName)).isDisplayed())
			{
				driver.findElement(ObjectMap.getLocator(ObjectMapName)).click();
				System.out.println(ObjectMapName+" is clicked");
			}
		}
	//####################################################################################################
	/*public static void Clear_Object(String Identifier,String ObjectMapName)
	{
		switch(Identifier.toUpperCase())
		{
			case "XPATH":
				driver.findElement(By.xpath(ObjectMapName)).clear();
				break;
			case "ID":
				driver.findElement(By.id(ObjectMapName)).clear();
				break;
			case "NAME":
				driver.findElement(By.name(ObjectMapName)).clear();	
				break;			
		}
	}
	
	
	public static void SendKeys_TO_Object(String Identifier,String ObjectMapName, String IP_Value)
	{
		switch(Identifier.toUpperCase())
		{
			case "XPATH":
				driver.findElement(By.xpath(ObjectMapName)).sendKeys(IP_Value);
				break;
			case "ID":
				driver.findElement(By.id(ObjectMapName)).sendKeys(IP_Value);
				break;
			case "NAME":
				driver.findElement(By.name(ObjectMapName)).sendKeys(IP_Value);	
				break;			
		}
	}
	
	public static void Click_Object(String Identifier,String ObjectMapName)
	{
		switch(Identifier.toUpperCase())
		{
			case "XPATH":
				driver.findElement(By.xpath(ObjectMapName)).click();
				break;
			case "ID":
				driver.findElement(By.id(ObjectMapName)).click();
				break;
			case "NAME":
				driver.findElement(By.name(ObjectMapName)).click();	
				break;			
		}
	}*/
	
	public static boolean CreatePlanDesign() throws Exception
	{
		try{					
					//------------------------------------------------------------- Page 1 - Create Plan Design
					System.out.println("CreatePlanDesign  ###   Method Started ");		
					String Plan_Design_Name = GetValueFromExcel("CreatePlanDesign", 1, x);
					String ClientName 		= GetValueFromExcel("CreatePlanDesign", 6, x);	
					String Effective_Date 	= GetValueFromExcel("CreatePlanDesign", 2, x);
					
				    BServe_All_Class.Wait_For_Element("btnNext");
				    Thread.sleep(2000);
				    if (ClientName.isEmpty())
				    {}
				    else
				    {
				    	BServe_All_Class.SendKeys_TO_Object("Edt_Clientname",ClientName);
				    }
				    BServe_All_Class.SendKeys_TO_Object("Edt_PlanDesignName",Plan_Design_Name);
				    
					//Thread.sleep(500);
					js = (JavascriptExecutor) driver;		
					//Set Date
			        WebElement inputs = driver.findElement(ObjectMap.getLocator("PlanEffectivedate"));
			        System.out.println("Displayed: " + inputs.isDisplayed());
					System.out.println("Effective Date: " + inputs.getText());
					js.executeScript("arguments[0].removeAttribute('readonly','readonly')",inputs);
					
			        inputs.clear();
			        inputs.click();
			        inputs.sendKeys(Effective_Date); 
			
					BServe_All_Class.JavascriptExecutorClick("btnNext");
					ExecutionStatus =true;
		}
		catch(Exception CreatePlanDesign_Error)
		{
			System.out.println(" Create Plan Design Error:"+ CreatePlanDesign_Error);		
			ExecutionStatus =false;	
		}
		return ExecutionStatus;
	}
	
	//------------------------------------------------------------- Page 2 - Delivery System
	public static boolean DeliverySystem() throws Exception
		{
		try{
				System.out.println("DeliverySystem  ###   Method Started ");
				String MailMaxDaysSupply = GetValueFromExcel("DeliverySystem", 0, x);
				
				BServe_All_Class.Wait_For_Element("btnSave");
				WriteLog("Page 1 - Create Plan Design Done");
				
				if (MailMaxDaysSupply.isEmpty())
				{}
				else
				{
					BServe_All_Class.SendKeys_TO_Object("MailMaxDaysSupply",MailMaxDaysSupply);
				}
			
				BServe_All_Class.JavascriptExecutorClick("btnSave");					
				//Thread.sleep(2000);
				ExecutionStatus =true;
		}
		catch(Exception DeliverySystem_Error)
		{
			System.out.println(" Delivery System Error:"+ DeliverySystem_Error);		
			ExecutionStatus =false;	
		}
		return ExecutionStatus;
		}
//------------------------------------------------------------- Page 3- CAG & Contract Details
public static boolean CAGAndContractDetails() throws Exception{
			try{
				System.out.println("CAGAndContractDetails  ###   Method Started ");
				String Carrier_Code = GetValueFromExcel("CAGAndContractDetails", 0, x);//CAGAndContractDetails
				String Carrier_Name = GetValueFromExcel("CAGAndContractDetails", 1, x);
				String Plan_Name    = GetValueFromExcel("CAGAndContractDetails", 2, x);

				JavascriptExecutorClick("CAG_ContractDetails");
				Thread.sleep(2000);
				BServe_All_Class.Wait_For_Element("btnSaveNext");
				BServe_All_Class.SendKeys_TO_Object("EdtAddCarrierCodefoot_0",Carrier_Code);
				BServe_All_Class.SendKeys_TO_Object("EdtAccountCodefoot_0",Carrier_Name);
				BServe_All_Class.SendKeys_TO_Object("EdtGroupCodefoot_0",Plan_Name);
				BServe_All_Class.JavascriptExecutorClick("btnSaveNext");
				Thread.sleep(3000); 
				ExecutionStatus =true;
			}
			catch(Exception CAGAndContractDetails_Error)
			{
				System.out.println(" Error:"+ CAGAndContractDetails_Error);		
				ExecutionStatus =false;	
			}
			return ExecutionStatus;
		}
//-------------------------------------------------------f------ Page 4 - DAW & Excluded
public static boolean DAWAndExcluded() throws Exception{											
			try{
					System.out.println("DAWAndExcluded  ###   Method Started ");					
					String MacType    = GetValueFromExcel("DAWAndExcludedPrescriber", 6, x);					

					JavascriptExecutorClick("DAW_ExcludedPrescriber");
					BServe_All_Class.Wait_For_Element("btnAddDAW");
					Thread.sleep(2000); 
					//For Pac Source
					switch (MacType){
					case "MAC A":
						BServe_All_Class.JavascriptExecutorClick("rbtnYesDAW1");
						BServe_All_Class.JavascriptExecutorClick("rbtnYesDAW2");
						//Click save DAW
						JavascriptExecutorClick("btnSave2");
						WriteLog("CAG Done");
						Thread.sleep(1000);							
						ExecutionStatus =true;// pac source
						return ExecutionStatus ;
					case "MAC B":
						BServe_All_Class.JavascriptExecutorClick("rbtnYesDAW2");
						BServe_All_Class.JavascriptExecutorClick("rbtnNoDAW1");						
						//Click save DAW
						JavascriptExecutorClick("btnSave2");
						WriteLog("CAG Done");												
						ExecutionStatus =true;
						return ExecutionStatus ;
					case "MAC C":							
						//Click save DAW
						JavascriptExecutorClick("btnSave2");
						WriteLog("CAG Done");
						ExecutionStatus =true;
						return ExecutionStatus ;
					default:						
				         break;// for tufts 
					}
					
					//Edit Icon 1
					BServe_All_Class.Wait_For_Element("Edit_DawDetails_img");					
					JavascriptExecutorClick("Edit_DawDetails_img_0");
					boolean Image1_Clicked = false;
					boolean Client_Pay_Displayed=false;
					while (Image1_Clicked==false)
					{
						try
						{
							WebElement Client_Pay_Btn = wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("Edit_MembeClientPay")));
							Client_Pay_Displayed =  Client_Pay_Btn.isDisplayed();
							if (Client_Pay_Displayed==true)
							{
								Image1_Clicked=true;
							}
						}
						catch(Exception EdE)
						{
							System.out.println(EdE);				
						}
					}			
					//Thread.sleep(1000);
					String DAW1Rule1Daw= GetValueFromExcel("DAWAndExcludedPrescriber", 0, x);
					String DAW1Rule1Cost= GetValueFromExcel("DAWAndExcludedPrescriber", 1, x);
					SelectByVisibleText("Edit_MembeClientPay",DAW1Rule1Daw) ;					
					SelectByVisibleText("Edit_CostShare",DAW1Rule1Cost) ;
					WebElement DAW_ADD_Btn;
					boolean DAW_Saved = false;
					while (DAW_Saved==false)
					{
						BServe_All_Class.Wait_For_Element("btnSaveDAWInterim");
						JavascriptExecutorClick("btnSaveDAWInterim");
						System.out.println("Edit Icon 1 --Added");
						try
						{
							DAW_ADD_Btn = wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("btnAddDAW")));
							DAW_Saved = DAW_ADD_Btn.isDisplayed();
						}
						catch(Exception DAWEx)
						{
							System.out.println(DAWEx);					
						}				
					}					
					//Edit Icon 2
					wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("Edit_DawDetails_img_1")));
					BServe_All_Class.Wait_For_Element("Edit_DawDetails_img_1");
					JavascriptExecutorClick("Edit_DawDetails_img_1");
					boolean Image2_Clicked = false;
					Client_Pay_Displayed=false;
					while (Image2_Clicked==false)
					{
						try
						{
							WebElement Client_Pay_Btn = wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("Edit_MembeClientPay")));
							Client_Pay_Displayed =  Client_Pay_Btn.isDisplayed();
							if (Client_Pay_Displayed==true)
							{
								Image2_Clicked=true;
							}
						}
						catch(Exception EdE)
						{
							System.out.println(EdE);				
						}
					}			
					String DAW2Rule1Daw = GetValueFromExcel("DAWAndExcludedPrescriber", 2, x);
					String DAW2Rule1Cost= GetValueFromExcel("DAWAndExcludedPrescriber",3, x);
					SelectByVisibleText("Edit_MembeClientPay",DAW2Rule1Daw) ;
					SelectByVisibleText("Edit_CostShare",DAW2Rule1Cost) ;
					DAW_Saved = false;
					System.out.println("DAW_Saved 2 --Current");
					while (DAW_Saved==false)
					{
						//Thread.sleep(3000);
						wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("btnSaveDAWInterim")));
						BServe_All_Class.Wait_For_Element("btnSaveDAWInterim");
						Thread.sleep(500);
						JavascriptExecutorClick("btnSaveDAWInterim");
						System.out.println("Edit Icon 2 --Added");
						try
						{ 
							DAW_ADD_Btn = wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("btnAddDAW")));
							DAW_Saved = DAW_ADD_Btn.isDisplayed();
						}
						catch(Exception DAWEx2)
						{
							System.out.println(DAWEx2);
						}
					}
					
					//Edit Icon 3	
					Tier_Type=GetValueFromExcel("CreatePlanDesign", 4, x);//CreatePlanDesign
					if (Tier_Type.contains("4"))
					{
						wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("Edit_DawDetails_img_2")));
						BServe_All_Class.Wait_For_Element("Edit_DawDetails_img_2");					
						JavascriptExecutorClick("Edit_DawDetails_img_2");
						System.out.println("DAW_Saved 3 --Clicked on edit");
						boolean Image3_Clicked = false;
						Client_Pay_Displayed=false;
						while (Image3_Clicked==false)
						{
							try
							{
								WebElement Client_Pay_Btn = wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("Edit_MembeClientPay")));
								Client_Pay_Displayed =  Client_Pay_Btn.isDisplayed();
								if (Client_Pay_Displayed==true)
								{
									Image3_Clicked=true;
								}
							}
							catch(Exception EdE)
							{
								System.out.println(EdE);				
							}
						}			
						System.out.println("DAW_Saved 3 --Current");
						DAW_Saved = false;
						while (DAW_Saved==false)
						{
							wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("btnSaveDAWInterim")));
							BServe_All_Class.Wait_For_Element("btnSaveDAWInterim");						
							JavascriptExecutorClick("btnSaveDAWInterim");
							System.out.println("Edit Icon 3 --Added");
							try
							{
								DAW_ADD_Btn = wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("btnAddDAW")));
								DAW_Saved=DAW_ADD_Btn.isDisplayed();
							}
							catch(Exception DAWEx3)
							{
								System.out.println(DAWEx3);
							}
						}
					}
					wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("btnSave2"))); //btnSave //id:ContentPlaceHolder1_btnSave// ContentPlaceHolder1_btnsave
					//Click save DAW
					JavascriptExecutorClick("btnSave2");
					WriteLog("DAW Done");
						Thread.sleep(4000);
						ExecutionStatus =true;
					}
				catch(Exception DAWAndExcluded_Error)
				{
					System.out.println(" DAW And Excluded Error:"+ DAWAndExcluded_Error);		
					ExecutionStatus =false;	
				}
				return ExecutionStatus;
				}
//********************************************************************

public static boolean Click_Stepped_Copay() throws Exception
{
	try
	{
		JavascriptExecutorClick("SteppedCopayTab");
		BServe_All_Class.Wait_For_Element("btn_Clear");
		ExecutionStatus=true;		
	}
	catch(Exception eClick_Stepped_Copay)
	{
		System.out.println("Error Click_Stepped_Copay: " + eClick_Stepped_Copay);
		ExecutionStatus=false;
	}
	return ExecutionStatus;	
}
/*public static boolean SteppedCopay() throws Exception
		{
					try
					{
						System.out.println("SteppedCopay  ###   Method Started ");
						///##########  				madhu;						///##########
						// Can be considered as a single functions and can be taken into excel
						Click_Stepped_Copay();
						SteppedCopayGenericTier1();//Call this function for GenericTier1
						SteppedCopayPreferredBrandTier2();
						SteppedCopayNonPreferredTier3();
						SteppedCopaySpecialityTier4();
						Close_Unexpected_Alert();
						ExecutionStatus =true;
					}
					catch(Exception _Error)
					{
						System.out.println(" Error:"+ _Error);		
						ExecutionStatus =false;	
					}
					return ExecutionStatus;
}*/
public static boolean Click_SteppedCopayVA() throws Exception
{
	try
	{
		Tier_Type=GetValueFromExcel("CreatePlanDesign", 4, x);
		System.out.println("SteppedCopay_VA  ###   Method Started ");
		JavascriptExecutorClick("SteppedCopayVA");
		BServe_All_Class.Wait_For_Element("SteppedCopayVA_WaitElm");		//id:ContentPlaceHolder1_lgndSteppedcopayVA //SteppedCopayVA_WaitElm
		System.out.println("Wait for Element Stepped copay VA - Wait - Done");
		ExecutionStatus=true;
	}
	catch(Exception Click_Stp_Excp)
	{
		System.out.println("Error in Click Stepped copay: " + Click_Stp_Excp);
		ExecutionStatus=false;
	}
	return ExecutionStatus;
}
public static boolean SteppedCopayVA_Tier1 () throws Exception
{
	//Tier 1 *****************************************************************************
	try
	{
		resval = BServe_All_Class.GetValueFromExcel("SteppedCopay_VA", 0, x);
		//Thread.sleep(500);
		BServe_All_Class.stepped_Copay_retail30(resval);
		
		// Retail Stepped Copay - 60
		resval = BServe_All_Class.GetValueFromExcel("SteppedCopay_VA", 1, x);
		//Thread.sleep(500);
		BServe_All_Class.stepped_Copay_retail60(resval);
		
		//Retail Stepped Copay - 90
		resval = BServe_All_Class.GetValueFromExcel("SteppedCopay_VA", 2, x);
		//Thread.sleep(500);
		BServe_All_Class.stepped_Copay_retail90(resval);
		
		System.out.println("Stepped Copay - Tier 1 Done...");
		Thread.sleep(100);	
		JavascriptExecutorClick("btnSave2");
		System.out.println("SC - Tier 1 Done");
		ExecutionStatus=true;		
	}
	catch(Exception Tier1Stp_Excp)
	{
		System.out.println("Error in Click Stepped copay VA Tier 1: " + Tier1Stp_Excp);
		ExecutionStatus=false;
	}

	return ExecutionStatus;
}

public static boolean SteppedCopayVA_Tier2 () throws Exception
{
// Tier 2 *****************************************************************************
	try
	{
	System.out.println("Stepped Copay - Tier 2 Start...");
	JavascriptExecutorClick("Preferred_Brand_Link");
	Thread.sleep(5000);
	
// Retail Stepped Copay - 30 
	resval = BServe_All_Class.GetValueFromExcel("SteppedCopay_VA", 3, x);
	BServe_All_Class.stepped_Copay_retail30(resval);
	
// Retail Stepped Copay - 60
	resval = BServe_All_Class.GetValueFromExcel("SteppedCopay_VA", 4, x);
	BServe_All_Class.stepped_Copay_retail60(resval);
	
//Retail Stepped Copay - 90
	resval = BServe_All_Class.GetValueFromExcel("SteppedCopay_VA", 5, x);
	BServe_All_Class.stepped_Copay_retail90(resval);
	
	System.out.println("Stepped Copay - Tier 2 Done...");
	Thread.sleep(100);
	JavascriptExecutorClick("btnSave2");
	Thread.sleep(6000);
	System.out.println("SC - Tier 2 Done...");
	ExecutionStatus=true;		
	}
	catch(Exception eSteppedCopayVA_Tier2)
	{
		System.out.println("Error in SteppedCopayVA_Tier2: " + eSteppedCopayVA_Tier2);
		ExecutionStatus=false;
	}
	return ExecutionStatus;
}
public static boolean SteppedCopayVA_Tier3 () throws Exception
{
//Tier 3 *****************************************************************************
	try
	{
	System.out.println("Stepped Copay - Tier 3 Start...");
	JavascriptExecutorClick("Non_Preferred_Brand_Link");
	Thread.sleep(6000);	
//Retail Stepped Copay - 30 	
	resval = BServe_All_Class.GetValueFromExcel("SteppedCopay_VA", 6, x);
	BServe_All_Class.stepped_Copay_retail30(resval);
	
// Retail Stepped Copay - 60
	resval = BServe_All_Class.GetValueFromExcel("SteppedCopay_VA", 7, x);
	BServe_All_Class.stepped_Copay_retail60(resval);
	
//Retail Stepped Copay - 90
	System.out.println("Stepped Copay - Tier 3 Done...");
	resval = BServe_All_Class.GetValueFromExcel("SteppedCopay_VA", 8, x);
	BServe_All_Class.stepped_Copay_retail90(resval);
	Thread.sleep(100);
	
	JavascriptExecutorClick("btnSave2");
	Thread.sleep(6000);
	System.out.println("SC - Tier 3 Done...");
	ExecutionStatus=true;		
	}
	catch(Exception eSteppedCopayVA_Tier3)
	{
		System.out.println("Error in SteppedCopayVA_Tier3: " + eSteppedCopayVA_Tier3);
		ExecutionStatus=false;
	}
	return ExecutionStatus;
}
public static boolean SteppedCopayVA_Tier4 () throws Exception
{
//Tier 4 *****************************************************************************
	try
	{
	if (Tier_Type.contains("4"))
		{
			System.out.println("Stepped Copay - Tier 4 Start...");
			JavascriptExecutorClick("Speciality_Link");
			 Thread.sleep(6000);
			if (Tier_Type.contains("4"))
			{
				// Retail Stepped Copay - 30
				resval = BServe_All_Class.GetValueFromExcel("SteppedCopay_VA", 9, x);
				BServe_All_Class.stepped_Copay_retail30(resval);
			}												
			JavascriptExecutorClick("btnSave2");
			Thread.sleep(6000);
			System.out.println("Stepped Copay - Tier 4 Done...");		
			ExecutionStatus=true;		
		}
	else{
			ExecutionStatus=true;
		}
	}//for try block 
	catch(Exception eSteppedCopayVA_Tier4)
	{
		System.out.println("Error in SteppedCopayVA_Tier4 " + eSteppedCopayVA_Tier4);
		ExecutionStatus=false;
	}
	return ExecutionStatus;
}

/*public static boolean SteppedCopay_VA() throws Exception
{
	try
	{
		Click_SteppedCopayVA();
		SteppedCopayVA_Tier1();
		SteppedCopayVA_Tier2();
		SteppedCopayVA_Tier3();
		SteppedCopayVA_Tier4();
		WriteLog("Stepped Copay - Done");
		ExecutionStatus =true;
	}
	catch(Exception SteppedCopay_VA_Error)
	{
		System.out.println(" Stepped Copay VA Error:"+ SteppedCopay_VA_Error);		
		ExecutionStatus =false;	
	}
	return ExecutionStatus;
}	*/			

/*public static boolean DrugClassification() throws Exception
{
	
	//Drug Classification    	
	String excludetier1 = BServe_All_Class.GetValueFromExcel("Accumulation", 7, x);
	BServe_All_Class.JavascriptExecutorClick("DrugClass");				    	
	//Thread.sleep(500);
	BServe_All_Class.Wait_For_Element("cblDrugClass_0");
	try
	{
		BServe_All_Class.JavascriptExecutorClick("cblDrugClass_3");
	}
	catch (Exception Cc)
	{
		System.out.println("Exception at Accumulation Edit Page: " + Cc);
	}
    	
	//Thread.sleep(500);
	if (excludetier1.contains("All Except Tier 1"))
	{
		try
		{
			BServe_All_Class.JavascriptExecutorClick("cblDrugClass_0");
		}
		catch(Exception Ccc)
		{
			System.out.println("Exception at accumulation: " + Ccc);
		}
	}
	
	//BServe_All_Class.Clear_Object("EdtIndividualAmt");
	BServe_All_Class.SendKeys_TO_Object("EdtIndividualAmt",individual);
	//BServe_All_Class.Clear_Object("EdtFamilyAmt");
	BServe_All_Class.SendKeys_TO_Object("EdtFamilyAmt",family);
	//Thread.sleep(1000);
	BServe_All_Class.JavascriptExecutorClick("btnSave");		// ContentPlaceHolder1_btnSave
    wait3.until(ExpectedConditions.alertIsPresent());
    Alert alert = driver.switchTo().alert();
    alert.accept();				    					    	
    BServe_All_Class.Wait_For_Element("Btn_DeductibleSave");
    }

    else	// if (dedcov.contains("Yes"))
    {
    	BServe_All_Class.JavascriptExecutorClick("rblDeductibleCoverage_1");				    	
    }
    BServe_All_Class.Wait_For_Element("Btn_DeductibleSave");
    BServe_All_Class.JavascriptExecutorClick("Btn_DeductibleSave");
    Thread.sleep(6000);
    }*/

public static boolean DeductibleTab() throws Exception
{
	//##### Deductible Tab
	try
	{
		String dedcov = BServe_All_Class.GetValueFromExcel("Accumulation", 3, x);
		String dedtype= BServe_All_Class.GetValueFromExcel("Accumulation", 4, x);		
		//String quaterval = BServe_All_Class.GetValueFromExcel("Accumulation", 1, 4);
		//-------------------------String quaterval=BServe_All_Class.Read_Excel(2, 1, 4);
		String quaterforth = BServe_All_Class.GetValueFromExcel("Accumulation", 5, x);
//Deductible Coverage
		if (dedcov.contains("Yes"))
		{
			JavascriptExecutorClick("rblDeductibleCoverage_0");
		    //Deductible Type
			if (dedtype.contains("Yes"))
			{
				JavascriptExecutorClick("rdbtnDedType_0");
			}
			else
			{
				JavascriptExecutorClick("rdbtnDedType_1");
			}
			//Deductible Accumulates 4th Quarter Carry Over
			if (quaterforth.equalsIgnoreCase("Yes"))
			{
				JavascriptExecutorClick("rddedaccinfourthquarter_0");
			}
			else if (quaterforth.contains("No"))
			{
				JavascriptExecutorClick("rddedaccinfourthquarter_1");
			}
			else
			{
				JavascriptExecutorClick("rddedaccinfourthquarter_2");
			}
			
//Deductible Inclusions - Edit
		JavascriptExecutorClick("Edit_Ded_Icon");			//Click Edit Button		
			BServe_All_Class.Wait_For_Element("btnSave");
			String temp_individual = BServe_All_Class.GetValueFromExcel("Accumulation", 8, x);
			String individual=temp_individual.replace("$", "").trim();
			String temp_family = BServe_All_Class.GetValueFromExcel("Accumulation", 9, x);			
			String family = temp_family.replace("$", "");
			String excludetier1 = BServe_All_Class.GetValueFromExcel("Accumulation", 7, x);			
			//---New code
			//Drug Classification    				
			BServe_All_Class.JavascriptExecutorClick("DrugClass");
			BServe_All_Class.Wait_For_Element("cblDrugClass_0");
			try
			{
				BServe_All_Class.JavascriptExecutorClick("cblDrugClass_3");
			}
			catch (Exception Cc)
			{
				System.out.println("Exception at Accumulation Edit Page: " + Cc);
			}
			if (excludetier1.contains("All Except Tier 1"))
			{
				try
				{
					BServe_All_Class.JavascriptExecutorClick("cblDrugClass_0");
				}
				catch(Exception Ccc)
				{
					System.out.println("Exception at accumulation: " + Ccc);
				}
			}
	    	BServe_All_Class.SendKeys_TO_Object("EdtIndividualAmt",individual);
	    	BServe_All_Class.SendKeys_TO_Object("EdtFamilyAmt",family);
	    	BServe_All_Class.JavascriptExecutorClick("btnSave");		// ContentPlaceHolder1_btnSave
	        wait3.until(ExpectedConditions.alertIsPresent());
	        Alert alert = driver.switchTo().alert();
	        alert.accept();				    					    	
	        BServe_All_Class.Wait_For_Element("Btn_DeductibleSave");
	    }
	    else	// if (dedcov.contains("Yes"))
	    {
	    	BServe_All_Class.JavascriptExecutorClick("rblDeductibleCoverage_1");				    	
	    }
		
	    BServe_All_Class.Wait_For_Element("Btn_DeductibleSave");
	    BServe_All_Class.JavascriptExecutorClick("Btn_DeductibleSave");
	    Thread.sleep(6000);
	    ExecutionStatus=true;
	}
	catch(Exception Ded_Excp)
	{
		System.out.println("Error in Deductible Tab: " + Ded_Excp);
		ExecutionStatus=false;
	}	
	return ExecutionStatus;	
}
public static boolean MaximumOutOfPocketTab() throws Exception
{
//########## Maximum Out Of Pocket (Tab)
	try
	{
		String dedcov = BServe_All_Class.GetValueFromExcel("Accumulation", 3, x);
		//String dedtype= BServe_All_Class.GetValueFromExcel("Accumulation", 4, x);
		//String quaterforth = BServe_All_Class.GetValueFromExcel("Accumulation", 5, x);
		
		JavascriptExecutorClick("MaximumOutOfPocket");		
		BServe_All_Class.Wait_For_Element("lblMOOPCoverage");
		String MOOP_Coverage= BServe_All_Class.GetValueFromExcel("Accumulation",10, x);
		
		if (MOOP_Coverage.contains("Yes"))
		{
			//MOOP Coverage
			BServe_All_Class.JavascriptExecutorClick("rbtnMOOPCoverage_0");			
				//MOOP Type		
			String maxcovmoodtype = BServe_All_Class.GetValueFromExcel("Accumulation", 11, x);
			if (maxcovmoodtype.contains("Yes"))
			{
				BServe_All_Class.JavascriptExecutorClick("rdbtnMOOPType_0");
			}
			else
			{
				BServe_All_Class.JavascriptExecutorClick("rdbtnMOOPType_1");
			}			
				// Should member pay DAW even after MOOP is met?
			String DAW_Event = BServe_All_Class.GetValueFromExcel("Accumulation", 12, x);
			
			if (DAW_Event.contains("Yes"))
			{
				BServe_All_Class.JavascriptExecutorClick("rdbtnDaw_1");	// No 					
			}
			else
			{
				BServe_All_Class.JavascriptExecutorClick("rdbtnDaw_0");	//Yes
			}
			
				// Does deductible applies towards MOOP?
			String DED_Towards_MOOP = BServe_All_Class.GetValueFromExcel("Accumulation", 13, x);
			if (dedcov.contains("Yes"))
			{
				if (DED_Towards_MOOP.contains("Yes"))
				{
					BServe_All_Class.JavascriptExecutorClick("rdbtnDedAppliesToMOOP_0");
				}
				else if (DED_Towards_MOOP.contains("No"))
				{	
					JavascriptExecutorClick("rdbtnDedAppliesToMOOP_1");
				}
			}
			else
			{
				BServe_All_Class.JavascriptExecutorClick("rdbtnDedAppliesToMOOP_2");
			}
		
			Thread.sleep(500);
				//Edit
			JavascriptExecutorClick("btnMOOPInclusions");
			BServe_All_Class.Wait_For_Element("btnSave");
			 maxcov1 = BServe_All_Class.GetValueFromExcel("Accumulation",15, x);
			 maxcov2= BServe_All_Class.GetValueFromExcel("Accumulation",16, x);
			String indv= maxcov1.replace(" ###  ", "").replace(" ", "").trim(); 
			String faml= maxcov2.replace(" ###  ", "").replace(" ", "").trim();
			BServe_All_Class.SendKeys_TO_Object("EdtIndividualAmt",indv);
			BServe_All_Class.SendKeys_TO_Object("EdtFamilyAmt",faml);
			BServe_All_Class.JavascriptExecutorClick("btnSave");
			wait3.until(ExpectedConditions.alertIsPresent());
		    Alert alert = driver.switchTo().alert();
		    alert.accept();							
		}
		else
		{
			BServe_All_Class.JavascriptExecutorClick("rbtnMOOPCoverage_1");
		}
	
		BServe_All_Class.Wait_For_Element("Btn_MoopSave");
		BServe_All_Class.JavascriptExecutorClick("Btn_MoopSave");
		Thread.sleep(6000);
		ExecutionStatus=true;
	}
	catch(Exception MOOP_Excp)
	{
		System.out.println("Error in MOOP: " + MOOP_Excp);
		ExecutionStatus=false;
	}
	return ExecutionStatus;
}
public static boolean MaximumAllowableBenefitsTab() throws Exception
{
//Maximum Allowable Benefits Tab
try
{
	JavascriptExecutorClick("Max_Allowed_Ben");
	BServe_All_Class.Wait_For_Element("Btn_MabSave");
	BServe_All_Class.JavascriptExecutorClick("rbtnMABCoverage_1");
	BServe_All_Class.JavascriptExecutorClick("Btn_MabSave");
	Thread.sleep(4000); 
	WriteLog("Accumulation - Done");
	ExecutionStatus=true;
}
catch(Exception MAB_Excp)
{
	System.out.println("Error in MAB_Excp" + MAB_Excp);
	ExecutionStatus=false;
}
return ExecutionStatus;
}
//-------------------------------------------------------------
//------------------------------------------------------------- Accumulation
//-------------------------------------------------------------				
public static boolean Click_Accumulation() throws Exception
{
	System.out.println("Accumulation  ###   Method Started ");					 
	try
	{	//Close_Unexpected_Alert();//closed by madhu
		JavascriptExecutorClick("Accumulations");						
		BServe_All_Class.Wait_For_Element("Legend1");
		
		String Month_Name=GetValueFromExcel("Accumulation", 0, x);
		
		SelectByVisibleText("Legend1" ,Month_Name);String Ben_Period = GetValueFromExcel("Accumulation", 1, x);
		SelectByVisibleText("benefitperiod" ,Ben_Period);
		
		String Reset_Date_Str = GetValueFromExcel("Accumulation", 2, x);
		
		if ( Ben_Period.contains("Reset Date"))
		{
			WebElement Cal_inputs = driver.findElement(ObjectMap.getLocator("Resetdate"));
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')",Cal_inputs);
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('onkeypress','return false;')",Cal_inputs);
			Cal_inputs.clear();
			//Cal_inputs.click();
			Cal_inputs.sendKeys(Reset_Date_Str);
		}
		ExecutionStatus =true;
		System.out.println("Accumulation Method Ended");
	}
	catch(Exception _Error)
	{
		System.out.println("Accumulation Method Failed");
		System.out.println(" Error:"+ _Error);		
		ExecutionStatus =false;	
	}
	return ExecutionStatus;
}

	public static boolean UniqueCopay() throws Exception							
				{	
				String ClientName 		= GetValueFromExcel("CreatePlanDesign", 6, x);
				String formulary_val    = GetValueFromExcel("UniqueCopay", 12, x);
				
				System.out.println("UniqueCopay  ###   Method Started ");
				try{					
				JavascriptExecutorClick("ObjUniqueCopay");
				BServe_All_Class.Wait_For_Element("TabDrugCopay");
					int iRowID = 0;			
					int iExcel_Col_ID=0;
					if (ClientName.contains("Pac Source"))
					{
						for (int Iteration=1; Iteration<=4; Iteration++)
						{
							String iCostshare = BServe_All_Class.GetValueFromExcel("UniqueCopay", iExcel_Col_ID, x);
							UpdateUniqueCopayDetails(iRowID,iCostshare);// Function call 								
							iRowID		      = iRowID+1;						
				            iExcel_Col_ID	  = iExcel_Col_ID+1;
				            if(iRowID==4){
				            	System.out.println("UniqueCopay Method Ended for ClientName: Pac Source");
				            	BServe_All_Class.JavascriptExecutorClick("btnsavedrugcopay");
								BServe_All_Class.JavascriptExecutorClick("btnsavedrugcopay");										
								//Thread.sleep(6000);
								WriteLog("Unique Copay - Done");	
								ExecutionStatus =true;
								return ExecutionStatus ;						            	
				            }				            
						}				
						System.out.println("UniqueCopay Method Ended for ClientName: Pac Source");
					}					
					else{// ########## for tufts
						
						char Act_Formul_Type = formulary_val.charAt(0);
						int tiercount = 0;
						if (Act_Formul_Type=='M' || Act_Formul_Type=='m')
						{
							tiercount=2;
						}
						else if (Act_Formul_Type=='H' || Act_Formul_Type=='h')
						{
							tiercount=1;
						}
						
						int RowID = 0;
						int Excel_Col_ID=0;
						
						if (tiercount==2)
						{
							//Retial - Tier 1
							for (int RetailIteration=1; RetailIteration <=3; RetailIteration++)
							{
								String costshare = BServe_All_Class.GetValueFromExcel("UniqueCopay", Excel_Col_ID, x);
								UpdateUniqueCopayDetails(RowID,costshare);// Function call 								
								RowID		     =   RowID+1;						
					            Excel_Col_ID	 = Excel_Col_ID+1;
							}
							
							//Retail - Tier 3
							Excel_Col_ID=3;
							for (int RetailIteration=1; RetailIteration <=3; RetailIteration++)
							{
								String costshare = BServe_All_Class.GetValueFromExcel("UniqueCopay", Excel_Col_ID, x);						    
								UpdateUniqueCopayDetails(RowID,costshare);// Function call 
					            RowID=RowID+1;
					            Excel_Col_ID=Excel_Col_ID+1;
							}
							
							//Mail - Tier 1
							Excel_Col_ID=6;
							for (int RetailIteration=1; RetailIteration <=3; RetailIteration++)
							{
								String costshare = BServe_All_Class.GetValueFromExcel("UniqueCopay", Excel_Col_ID, x);
								UpdateUniqueCopayDetails(RowID,costshare);
					            RowID=RowID+1;
					            Excel_Col_ID=Excel_Col_ID+1;
							}
							
							//Mail - Tier 3
							Excel_Col_ID=9;
							for (int RetailIteration=1; RetailIteration <=3; RetailIteration++)
							{								
								String costshare = BServe_All_Class.GetValueFromExcel("UniqueCopay", Excel_Col_ID, x);
								UpdateUniqueCopayDetails(RowID,costshare);
					            RowID=RowID+1;
					            Excel_Col_ID=Excel_Col_ID+1;
							}							
						}
						else if (tiercount==1)
						{
							RowID=0;
							Excel_Col_ID=3;
							//Retails - Tier 3
							for (int RetailIteration=1; RetailIteration <=3; RetailIteration++)
							{								
								String costshare = BServe_All_Class.GetValueFromExcel("UniqueCopay",  Excel_Col_ID, x);
								UpdateUniqueCopayDetails(RowID,costshare);
					            RowID=RowID+1;
					            Excel_Col_ID=Excel_Col_ID+1;
							}
							
							//Mail - Tier 3
							Excel_Col_ID=9;
							for (int RetailIteration=1; RetailIteration <=3; RetailIteration++)
							{
								String costshare = BServe_All_Class.GetValueFromExcel("UniqueCopay", Excel_Col_ID, x);
						    	UpdateUniqueCopayDetails(RowID,costshare);
					            RowID=RowID+1;
					            Excel_Col_ID=Excel_Col_ID+1;
							}
						}						
						BServe_All_Class.JavascriptExecutorClick("btnsavedrugcopay");
						BServe_All_Class.JavascriptExecutorClick("btnsavedrugcopay");
						//Thread.sleep(6000);
						WriteLog("Unique Copay - Done");
						ExecutionStatus =true;
					}
				}	
				catch(Exception UniqueCopay_Error)
				{
					System.out.println(" Error:"+ UniqueCopay_Error);		
					ExecutionStatus =false;	
				}
				return ExecutionStatus;
			}				
			public static boolean FormularyMapping() throws Exception
				{
				System.out.println("FormularyMapping   ###   Method Started ");
				try{
					//------------------------------------------------------------------------ Formulary Mapping 					
					boolean Form_ID_Disp=false;
					while (Form_ID_Disp==false)
					{
						JavascriptExecutorClick("ucmenu_65");
						Wait_For_Element  ("EdtFormularyID");
						WebElement Form_ID= wait3.until(ExpectedConditions.visibilityOfElementLocated(ObjectMap.getLocator("EdtFormularyID")));
						Form_ID_Disp	  = Form_ID.isDisplayed();
						SendKeys_TO_Object("EdtFormularyID",GetValueFromExcel("FormularyMapping", 0, x));				
					}					
					JavascriptExecutorClick("FM_BtnSearch");
					//Wait_For_Element(GetValueFromExcel("FormularyMapping", 1, x));
					//JavascriptExecutorClick(GetValueFromExcel("FormularyMapping", 1, x));
					Thread.sleep(2000);
driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_BCListViewMain_gvList']//a[contains(text(),'"+GetValueFromExcel("FormularyMapping", 1, x)+"')]/ancestor::tr[1]/td[1]/span/input[@type='radio']")).click(); 
					JavascriptExecutorClick      ("btnAssociate");
					wait3.until(ExpectedConditions.alertIsPresent());
					Alert a3=driver.switchTo().alert();
					a3.accept();
					WriteLog("Formulary Mapping - Done");
					Thread.sleep(1000);
					ExecutionStatus = true;
				}
				catch(Exception _Error)
				{
					System.out.println(" Error:"+ _Error);		
					ExecutionStatus = false;	
				}
				return ExecutionStatus;
			}
			public static boolean NavigatingToCopyFromTemplate() 
			{
				try{
						//Navigating to Copy From Template
						driver.get("https://10.118.62.189/BenefitServUI/BPD/Template/TemplateSearch.aspx?BPDLevel=oRJu1I7wB0pytzys2C1Qhw==&BPDMode=N6ECp5IkZvLZRqgMrqoxdw==&BPDStatus=xiw+JPqmAfzuzL1rxrj1Zw==&_JobID=oQPgJhMK1z0KaIu4hgX1DA==");
//						https://10.118.62.189/BenefitServUI/BPD/Template/TemplateSearch.aspx?BPDLevel=oRJu1I7wB0pytzys2C1Qhw==&BPDMode=N6ECp5IkZvLZRqgMrqoxdw==&BPDStatus=xiw+JPqmAfzuzL1rxrj1Zw==&_JobID=oQPgJhMK1z0KaIu4hgX1DA==
						Wait_For_Element("ContainerHeader");
						ExecutionStatus = true;
					}
				catch(Exception NavigatingToCopyFromTemplate_Error)
					{
						System.out.println(" Error:"+ NavigatingToCopyFromTemplate_Error);		
						ExecutionStatus = false;	
					}
				return ExecutionStatus;
			}
			public static boolean NavigatingToCopyPlan_Design() throws Exception
			{
				try
				{
					//Navigating to Copy Plan Design
					driver.get("https://10.118.62.189/BenefitServUI/BPD/Template/BPDSearch.aspx?BPDLevel=oRJu1I7wB0pytzys2C1Qhw==&BPDMode=8kyIfarjMVS49uK/7tClWA==&BPDStatus=xiw+JPqmAfzuzL1rxrj1Zw==&_JobID=lG4wLdRwYE3gjd8BpyCmVQ==");
					Wait_For_Element("Edt_BPDName");
					if (driver.findElement(ObjectMap.getLocator("Edt_BPDName")).isDisplayed())
					{
						ExecutionStatus =true;
					}
				}
				catch(Exception _Error)
				{
					System.out.println(" Error:"+ _Error);		
					ExecutionStatus =false;	
				}
				return ExecutionStatus;
			}
				
			public static boolean SearchPlanDesignAndSelect() throws Exception 
				{
					try
					{					
						String Search_Temp_Type=GetValueFromExcel("CreatePlanDesign", 3, x);
						Tier_Type=GetValueFromExcel("CreatePlanDesign", 4, x);
						
						boolean Table_Data_exist = false;
						while (Table_Data_exist==false)
						{
							Clear_Object("Edt_BPDName");				
							SendKeys_TO_Object("Edt_BPDName", Search_Temp_Type );				
							JavascriptExecutorClick("Btn_Search");
							IsDisplayed("Tbl_Data");
							isElementPresentW("Tbl_Data");
							Table_Data_exist = driver.findElements(ObjectMap.getLocator("Tbl_Data")).size()>0;
							System.out.println("Table_Data_exist: " + Table_Data_exist);
						}
						Wait_For_Element("Paging1_ddlPageNumber");	
						Thread.sleep(4000);
						int Link_Clicked=0;
						while (Link_Clicked==0)
						{
							boolean Element_Present = driver.findElements(By.linkText(Search_Temp_Type)).size()>0;
							if (Element_Present)
							{
								WebElement Template_Link = driver.findElement(By.linkText(Search_Temp_Type));
								driver.switchTo().window(Prent_WNDID);
								new Actions(driver).moveToElement(Template_Link).click().build().perform();
								Link_Clicked=1;
							}
							else
							{					
								JavascriptExecutorClick("Btn_Next");
								//Thread.sleep(2000);
							}
						}
						Wait_For_Element("header");
						WriteLog("Opened copy from Template");	
						ExecutionStatus =true;
					}
					catch(Exception _Error)
					{
						System.out.println(" Error:"+ _Error);		
						ExecutionStatus =false;	
					}
					return ExecutionStatus;
				}
			public static boolean ChangeDesignation() throws Exception
			{
				try
				{
					Actions action = new Actions(driver);						
					//IE Code
					String Tester_Analyst = "Benefit Tester";		
					int try_count=0;
					do
					{
						driver.switchTo().window(Prent_WNDID);
						action.moveToElement(driver.findElement(ObjectMap.getLocator("ImggGlobeIcon"))).click().perform();
						Thread.sleep(500);
						Robot robot=new Robot();
						robot.keyPress(KeyEvent.VK_TAB);
						Thread.sleep(200); 
						driver.switchTo().window(Prent_WNDID);
						robot.keyPress(KeyEvent.VK_DOWN);
						Thread.sleep(200);
						driver.switchTo().window(Prent_WNDID);
						robot.keyPress(KeyEvent.VK_ENTER);
						Thread.sleep(3000);
						//WebElement we2= wait3.until(ExpectedConditions.visibilityOfElementLocated(ObjectMap.getLocator("lblUserDesignation")));
						WebElement we3= driver.findElement(ObjectMap.getLocator("lblUserDesignation"));
						Thread.sleep(5000);
						Tester_Analyst = we3.getText();					
						System.out.println("Desg: " + Tester_Analyst);
						if(Tester_Analyst.contains("Benefit Analyst"))
						{	
							ExecutionStatus =true;					
						}
						//Tester_Analyst= we2.isDisplayed();	
						try_count=try_count+1;
					}
					while(Tester_Analyst.contains("Benefit Tester") && try_count<10);					
					System.out.println("Benefit Tester Changed as Analyst");					
				}
				catch(Exception _Error)
				{
					System.out.println(" Error:"+ _Error);		
					ExecutionStatus =false;	
				}
				return ExecutionStatus;
			}
			/*public static boolean Error_Handling()
			{
				//Handling Empty page / Page Not found error
				try
				{
					wait3.until(ExpectedConditions.presenceOfElementLocated(ObjectMap.getLocator("Page_Logo")));
					ExecutionStatus =true;
				}
				catch(Exception LogoNotPresent)
				{
					System.out.println(LogoNotPresent)
					
				}
				
				ExecutionStatus =true;
				return ExecutionStatus;
				
			}*/
	public static boolean SearchTemplateAndSelect() throws Exception 
			{
				System.out.println("SearchTemplateAndSelect  ###   Method Started ");
			try{					
								
				String Search_Temp_Type=GetValueFromExcel("CreatePlanDesign", 3, x);
				Tier_Type			   =GetValueFromExcel("CreatePlanDesign", 4, x);
				
				boolean Table_Data_exist = false;
				while (Table_Data_exist==false)
				{
					Clear_Object("Edt_BPDName");				
					SendKeys_TO_Object("Edt_BPDName", Search_Temp_Type );				
					JavascriptExecutorClick("Btn_Search");
					//Thread.sleep(1000);
					Table_Data_exist = driver.findElements(ObjectMap.getLocator("Tbl_Data")).size()>0;
					System.out.println("Table_Data_exist: " + Table_Data_exist);
				}
				
				//Thread.sleep(2000);
				Wait_For_Element("Paging1_ddlPageNumber");
				
				int Link_Clicked=0;
				while (Link_Clicked==0)
				{
					boolean Element_Present = driver.findElements(By.linkText(Search_Temp_Type)).size()>0;
					if (Element_Present)
					{
						//Thread.sleep(1000);
						WebElement Template_Link = driver.findElement(By.linkText(Search_Temp_Type));
						driver.switchTo().window(Prent_WNDID);
						new Actions(driver).moveToElement(Template_Link).click().build().perform();
						Link_Clicked=1;
					}
					else
					{					
						JavascriptExecutorClick("Btn_Next");
						//Thread.sleep(2000);
					}
				}
				//Thread.sleep(1000);				
				Wait_For_Element("header");
				WriteLog("Opened copy from Template");	

				ExecutionStatus =true;
			}
			catch(Exception _Error)
			{
				System.out.println(" Error:"+ _Error);		
				ExecutionStatus =false;	
			}
			return ExecutionStatus;
			}
//######
	
public static boolean EditExistingPlanDesign() throws InterruptedException
{
	String Plan_Design_Name=GetValueFromExcel("CreatePlanDesign", 1, x);
	System.out.println("EditExistingPlanDesign	 ###   Method Started ");
	try
	{
		wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("ContentPlaceHolder1_txtBPDName")));
		driver.findElement(By.id("ContentPlaceHolder1_txtBPDName")).clear();
		driver.findElement(By.id("ContentPlaceHolder1_txtBPDName")).sendKeys(Plan_Design_Name);//change the plan design name or hard code here
		driver.findElement(By.id("ContentPlaceHolder1_btnSearch123")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("ContentPlaceHolder1_BCListView1_gvList_Edit_0")).click();
		Thread.sleep(3000);
		ExecutionStatus=true;
	/*	String Search_Temp_Type="F207MM9V";
        WebElement Template_Link = driver.findElement(By.linkText(Search_Temp_Type));
        driver.switchTo().window(Prent_WNDID);
        new Actions(driver).moveToElement(Template_Link).click().build().perform();		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Template_Link);
		Thread.sleep(1000);		
        ExecutionStatus=true;*/
        return ExecutionStatus;
	}
	catch(Exception eEditExistingPlanDesign)
	{
		System.out.println("Error in Edit Existing Plan Design: " + eEditExistingPlanDesign);
		ExecutionStatus=false;
	}
	return ExecutionStatus;
}
public static boolean SteppedCopayGenericTier1() throws InterruptedException
			{
	try
	{
			Tier_Type				= GetValueFromExcel("CreatePlanDesign", 4, x);
			String ClientName 		= GetValueFromExcel("CreatePlanDesign", 6, x);	
			
			//Generic (Tier 1) ********************************************************************
			
			Thread.sleep(2000);
	    	//Retial-30
	    	resval=BServe_All_Class.GetValueFromExcel("SteppedCopay", 0, x).trim();				    	
	    	switch (ClientName)
	    	{
		    	case "Pac Source":
		    		if(resval.isEmpty()){
		    			JavascriptExecutorClick("RbtnRetailRejectOf_0");
		    			System.out.println("retail30 value is Empty");
		    		}else{
		    			JavascriptExecutorClick("imgCancelOf_0");
		    			retail30_For_PacSource(resval);
		    		}		    		
		    		System.out.println("retail30_For_PacSource Method Done");
		    		break;
		    		
		    	default:
		    		BServe_All_Class.retail30(resval);//for tufts
		    		System.out.println("retail30 Method Done");
		    		break;
	    	}			    	
	    	
	    	//Retial-60
	    	resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 1, x);
	    	switch (ClientName)
	    	{
		    	case "Pac Source":
		    		if(resval.isEmpty()){
		    			JavascriptExecutorClick("RbtnRetailRejectOf_1");
		    			System.out.println("retail60 value is Empty");
		    		}else{
		    			JavascriptExecutorClick("imgCancelOf_1");
		    			retail60_For_PacSource(resval);
		    		}		    		
		    		System.out.println("retail60_For_PacSource Method Done");
		    		break;
		    		
		    	default:
		    		Retail60(resval);//for tufts
		    		System.out.println("Retail60 Method Done");
		    		break;
	    	}			 
	    	
	    	//Retial-90
	    	resval=BServe_All_Class.GetValueFromExcel("SteppedCopay", 2, x);
	    	switch (ClientName)
	    	{
		    	case "Pac Source":
		    		if(resval.isEmpty()){
		    			JavascriptExecutorClick("RbtnRetailRejectOf_2");
		    			System.out.println("retail90 value is Empty");
		    		}else{
		    			JavascriptExecutorClick("imgCancelOf_2");
		    			retail90_For_PacSource(resval);
		    		}		    		
		    		ExecutionStatus =true;
		    		System.out.println("retail90_For_PacSource Method Done");
		    		return ExecutionStatus;
		    		
		    	default:
		    		Retail90(resval);//for tufts
		    		System.out.println("Retail90 Method Done");
		    		break;
	    	}				    	
	    	
	    	System.out.println("Retail90 Method Done");
	    	
	    	//Mail-30
	    	resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 12, x);	    	
    		if(resval.isEmpty()){
    			JavascriptExecutorClick("RbtnMailRejectOf_3");//need to click on mail 30 reject
    			System.out.println("Mail-30 value is Empty");
    		}else{					
    			JavascriptExecutorClick("imgCancelOf_3");
    			mail30(resval);
	    	}		
    		
	    	System.out.println("mail30 Method Done");
	    	//Mail-60
	    		resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 13, x);
		    		if(resval.isEmpty()){
		    			JavascriptExecutorClick("RbtnMailRejectOf_4");//need to click on mail 30 reject
		    			System.out.println("Mail-60 value is Empty");
		    		}else{
		    		//default for tufts	    	
		    			JavascriptExecutorClick("imgCancelOf_4");
				    	mail60(resval);
				    	System.out.println("mail60 Method Done");
		    		}		
	    	//Mail-90
	    			resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 14, x);
		    		if(resval.isEmpty()){
		    			JavascriptExecutorClick("RbtnMailRejectOf_5");//need to click on mail 30 reject
		    			System.out.println("Mail-90 value is Empty");
		    		}else{
			    		//default for tufts	  
		    			JavascriptExecutorClick("imgCancelOf_5");
				    	mail90(resval);
				    	System.out.println("mail90 Method Done");
		    		}		
	    	BServe_All_Class.Wait_For_Element("btnSave2");
	    	JavascriptExecutorClick("btnSave2"); 
	    	Thread.sleep(6000);
	    	System.out.println("Tier 1 Done...");
	    	ExecutionStatus=true;		
	}
	catch(Exception eGenericTier1)
	{
		System.out.println("Error in GenericTier1: " + eGenericTier1);
		ExecutionStatus=false;
	}

			return ExecutionStatus;
}	
	public static boolean SteppedCopayPreferredBrandTier2() throws InterruptedException
	{
		Tier_Type			    = GetValueFromExcel("CreatePlanDesign", 4, x);
		String ClientName 		= GetValueFromExcel("CreatePlanDesign", 6, x);	
			//#############   Preferred Brand (Tier 2) ********************************************************************
		try
		{	
			JavascriptExecutorClick("Preferred_Brand_Link");
			Thread.sleep(3000);
			//Retial-30
			resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 3, x);
			switch (ClientName)
			{
				case "Pac Source":
					if(resval.isEmpty()){
						JavascriptExecutorClick("RbtnRetailRejectOf_0");
		    			System.out.println("retail30 value is Empty");
		    		}else{
		    			JavascriptExecutorClick("imgCancelOf_0");
		    			retail30_For_PacSource(resval);
		    		}		    							
					break;
				default:
					retail30(resval);
					break;
			}			    	
			System.out.println("retail30 Method Done");
			
			//Retial-60
			resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 4, x);
			switch (ClientName){
			case "Pac Source":
				if(resval.isEmpty()){
					JavascriptExecutorClick("RbtnRetailRejectOf_1");
	    			System.out.println("retail60 value is Empty");
	    		}else{
	    			JavascriptExecutorClick("imgCancelOf_1");
	    			retail60_For_PacSource(resval);
	    		}
				break;
			default:
				Retail60(resval);
				break;
			}
			
			//Retial-90
			resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 5, x);
			switch (ClientName){
			case "Pac Source":
				if(resval.isEmpty()){
					JavascriptExecutorClick("RbtnRetailRejectOf_2");
	    			System.out.println("retail90 value is Empty");
	    		}else{
	    			JavascriptExecutorClick("imgCancelOf_2");
	    			retail90_For_PacSource(resval);
	    		}	
				ExecutionStatus =true;
				return ExecutionStatus;
			default:
				Retail90(resval);
				break;
			}
			
			//Mail-30
					resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 15, x);
		    		if(resval.isEmpty()){
		    			JavascriptExecutorClick("RbtnMailRejectOf_3");//need to click on mail 30 reject
		    			System.out.println("Mail-30 value is Empty");
		    		}else{
		    		//default for tufts	  
			    		JavascriptExecutorClick("imgCancelOf_3");//for clearing
						mail30(resval);
		    		}			
			//Mail-60			
	    			resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 16, x);
		    		if(resval.isEmpty()){
		    			JavascriptExecutorClick("RbtnMailRejectOf_4");//need to click on mail 30 reject
		    			System.out.println("Mail-60 value is Empty");
		    		}else{
						JavascriptExecutorClick("imgCancelOf_4");//for clearing
						mail60(resval);
	    			}
			//Mail-90
				resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 17, x);
	    		if(resval.isEmpty()){
	    			JavascriptExecutorClick("RbtnMailRejectOf_5");//need to click on mail 30 reject
	    			System.out.println("Mail-90 value is Empty");
	    		}else{					
	    			JavascriptExecutorClick("imgCancelOf_5");
	    			mail90(resval);					
				}			
			//resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 3, x);
			JavascriptExecutorClick("btnSave2");
			
			Thread.sleep(6000);				    	
			System.out.println("Tier 2 Done..."); 
			ExecutionStatus=true;		
		}
		catch(Exception ePreferredBrandTier2)
		{
			System.out.println("Error in xxx: " + ePreferredBrandTier2);
			ExecutionStatus=false;
		}
		return ExecutionStatus;
	}
		public static boolean SteppedCopayNonPreferredTier3() throws InterruptedException
		{
			Tier_Type			= GetValueFromExcel("CreatePlanDesign", 4, x);
			String ClientName 	= GetValueFromExcel("CreatePlanDesign", 6, x);	
			try
			{
				//	Non Preferred (Tier 3) ********************************************************************				
				System.out.println("Non_Preferred_Brand_Link Tab Click Started");
				JavascriptExecutorClick("Non_Preferred_Brand_Link");
				Thread.sleep(3000);
				
				//Retial-30
				resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 6, x);
				switch (ClientName)
		    	{
			    	case "Pac Source":
			    		if(resval.isEmpty()){
			    			JavascriptExecutorClick("RbtnRetailRejectOf_0");
			    			System.out.println("retail30 value is Empty");
			    		}else{
			    			JavascriptExecutorClick("imgCancelOf_0");
			    			retail30_For_PacSource(resval);
			    		}
			    		return ExecutionStatus;
			    	default:
			    		//default for tufts
						retail30(resval);
						break;
		    	}	
				//Retial-60
	    		resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 7, x);
				switch (ClientName)
		    	{
			    	case "Pac Source":
			    		if(resval.isEmpty()){
			    			JavascriptExecutorClick("RbtnRetailRejectOf_0");
			    			System.out.println("retail60 value is Empty");
			    		}else{						
			    			JavascriptExecutorClick("imgCancelOf_1");
			    			retail60_For_PacSource(resval);
			    		}
			    		return ExecutionStatus;

			    	default:
			    		//default for tufts
						Retail60(resval);
						break;
		    	}	
				//Retial-90
				resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 8, x);
				switch (ClientName)
		    	{
			    	case "Pac Source":
			    		if(resval.isEmpty()){
			    			JavascriptExecutorClick("RbtnRetailRejectOf_0");
			    			System.out.println("retail90 value is Empty");
			    		}else{						
			    			JavascriptExecutorClick("imgCancelOf_2");
			    			retail90_For_PacSource(resval);
			    		}	
			    		return ExecutionStatus;
			    	default:
			    		//default for tufts				
						Retail90(resval);
						break;
		    	}	
				//Mail-30###########
				resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 18, x);
	    		if(resval.isEmpty()){
	    			JavascriptExecutorClick("RbtnMailRejectOf_3");//need to click on mail 30 reject
	    			System.out.println("Mail-30 value is Empty");
	    		}else{
	    			JavascriptExecutorClick("imgCancelOf_3");
	    			mail30(resval);
	    		}
				//Mail-60
				resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 19, x);
	    		if(resval.isEmpty()){
	    			JavascriptExecutorClick("RbtnMailRejectOf_4");//need to click on mail 60 reject
	    			System.out.println("Mail-60 value is Empty");
	    		}else{
	    			JavascriptExecutorClick("imgCancelOf_4");
	    			mail60(resval);
	    		}
				//Mail-90
				resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 20, x);				
	    		if(resval.isEmpty()){
	    			JavascriptExecutorClick("RbtnMailRejectOf_5");//need to click on mail 90 reject
	    			System.out.println("Mail-90 value is Empty");
	    		}else{						
	    			JavascriptExecutorClick("imgCancelOf_5");
	    			mail90(resval);			    		
	    		}
	    		
				Thread.sleep(6000);				    	
				JavascriptExecutorClick("btnSave2");
				System.out.println("Tier 3 Done..."); 
				ExecutionStatus=true;		
			}
			catch(Exception eNonPreferredTier3)
			{
				System.out.println("Error in Non Preferred Tier3: " + eNonPreferredTier3);
				ExecutionStatus=false;
			}
			return ExecutionStatus;
	}
		public static boolean SteppedCopaySpecialityTier4() throws InterruptedException
		{
			Tier_Type			= GetValueFromExcel("CreatePlanDesign", 4, x);
			String ClientName 	= GetValueFromExcel("CreatePlanDesign", 6, x);	
		try
			{
			//###### Speciality (Tier 4) ********************************************************************
				
				if (Tier_Type.contains("4"))
				{
					Wait_For_Element("Speciality_Link");
					JavascriptExecutorClick("Speciality_Link");
					Thread.sleep(3000);
					if (Tier_Type.contains("4"))
					{
						//Retial-30
						resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 9, x);
				    	retail30_Tier4(resval);
				    	
				    	//Mail-30
					    	resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 21, x);
					    								    	
					    	if (resval=="" || resval==null)
					    	{
					    		resval = BServe_All_Class.GetValueFromExcel("SteppedCopay", 15, x);
					    	}
					    	mail30_Tier4(resval);
					}							
					
					JavascriptExecutorClick("btnSave2");
					System.out.println("Tier 4 Done...");	
					ExecutionStatus=true;		
					WriteLog("Stepped Copay - Done");
					System.out.println("SteppedCopay Done");
					}
			else{
					ExecutionStatus=true;
			}	
			}//for try block 
			catch(Exception eSpecialityTier4)
			{
				System.out.println("Error in Speciality Tier4 : " + eSpecialityTier4);
				ExecutionStatus=false;
			}
		return ExecutionStatus;

}
		/*public static boolean Close_Unexpected_Alert() throws InterruptedException
		{
		BServe_All_Class.Close_Unexpected_Alert();
		WriteLog("Stepped Copay - Done");
		System.out.println("SteppedCopay Done");
		}*/
}
