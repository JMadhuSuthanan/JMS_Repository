package Examples;

public class Unique {

	public static void main(String[] args) {


//Q #34) How to assert title of the web page?

		//verify the title of the web page
		assertTrue(“The title of the window is incorrect.”,driver.getTitle().equals(“Title of the page”));
		
//Q #35) How to mouse hover on a web element using WebDriver?
				// Instantiating Action Interface
				Actions actions=new Actions(driver);
				// howering on the dropdown
				actions.moveToElement(driver.findElement(By.id("id of the dropdown"))).perform();
				// Clicking on one of the items in the list options
				WebElement subLinkOption=driver.findElement(By.id("id of the sub link"));
				subLinkOption.click();
		
//Q #36) How to retrieve CSS properties of an element?
		driver.findElement(By.id(“id“)).getCssValue(“name of css attribute”);
		driver.findElement(By.id(“id“)).getCssValue(“font-size”);

//Q #37) How to capture screenshot in WebDriver?
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				// Code to copy the screenshot in the desired location
				FileUtils.copyFile(scrFile, new File("C:\\CaptureScreenshot\\google.jpg"));			
		
	}

}
