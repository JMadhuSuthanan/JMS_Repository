package ExPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExClass {

	public static void main(String[] args) {
		
		//public static void login(String[] args)  
				//Launch a new Firefox browser.
				/*Open Store.DemoQA.com
				Get Page Title name and Title lengthl
				Print Page Title and Title length on the Eclipse Console.
				Get Page URL and verify if the it is a correct page opened
				Get Page Source (HTML Source code) and Page Source length
				Print Page Length on Eclipse Console.
				Close the Browser.*/
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
		System.out.println (driver.getTitle());
		System.out.println (driver.getTitle().length());
		System.out.println (driver.getCurrentUrl());
		System.out.println (driver.getCurrentUrl().length()); 
		
		String x = driver.getCurrentUrl();
		
		if  ( driver.getCurrentUrl().equals("http://www.google.com")  ) {
		}
		else if(x.equals("www.google.com")){
			System.out.println ("it is not wrong");
		}
		else{
		}
		//if  ( x.equals("http://www.google.com")  ) {
		//if  "http://www.google.com" == driver.getCurrentUrl() {
			System.out.println ("it is wrong");
		}
	}
