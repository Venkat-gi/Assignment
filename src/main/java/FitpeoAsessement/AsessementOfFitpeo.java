package FitpeoAsessement;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AsessementOfFitpeo {

	public static void main(String[] args) throws InterruptedException, AWTException {
		WebDriver driver = new ChromeDriver();// here I'm Initialize WebDriver (Chrome in this case)
		driver.manage().window().maximize(); //here we use Maximize method it is used to maximize the our lunched application
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));// here I'm using the Implicit wait it is used for return the address of particular element 
		driver.get("https://www.fitpeo.com/"); // Navigate to the FitPeo homepage

		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();// from the home page we navigate to the Revenue Calculator page. 
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;  // Scroll down to the slider section

		js.executeScript("window.scrollTo(0,300)");//○	Scroll down the page until the revenue calculator slider is visible.
		Thread.sleep(5000);
		WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));// we navigate the slider element
		Robot r = new Robot();
		Actions actions = new Actions(driver);// here i'm using Action class to perform mouse handling
		actions.clickAndHold(slider).moveByOffset(93, 0).perform();
		js.executeScript("window.scrollTo(0,300)");

		for (;;) {
			r.keyPress(KeyEvent.VK_RIGHT);//The Robot class simulates the pressing of the "right arrow" key to increment the slider’s value.
		//A loop continues this process until the slider reaches the value 820 (checked by its aria-valuenow attribute).

			r.keyRelease(KeyEvent.VK_RIGHT);
			if (slider.getAttribute("aria-valuenow").equals("820")) {
				break;
			}
		}
		js.executeScript("window.scrollTo(0,300)");
		assertEquals(slider.getAttribute("aria-valuenow"), "820");
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0,300)");
		WebElement textfield = driver.findElement(By.xpath("//input[@id=':r0:']"));
		textfield.click();
		r.keyPress(KeyEvent.VK_BACK_SPACE);//The text field is clicked, and the Robot class is used to clear its current content by simulating pressing the "Backspace" key multiple times.
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		Thread.sleep(5000);
		js.executeScript("window.scrollTo(0,300)");
		textfield.sendKeys("560");//The text field is then populated with the value 560.
		Thread.sleep(5000);
		assertEquals(slider.getAttribute("aria-valuenow"), "560");
		js.executeScript("window.scrollTo(0,800)");
		WebElement frist_check_box = driver.findElement(By.xpath(
				"//p[text()='CPT-99091']/ancestor::div[@class='MuiBox-root css-1eynrej']/descendant::input[@type='checkbox']"));//	After each checkbox is clicked, the script checks if it is selected and prints a message to the console confirming the selection.
		frist_check_box.click();//The check boxes for different CPT codes (e.g., CPT-99091, CPT-99453, etc.) are located using XPath and clicked.
		if (frist_check_box.isSelected()) {
			System.out.println("frist box selected");
		}
		Thread.sleep(2000);
		WebElement second_check_box = driver.findElement(By.xpath(
				"//p[text()='CPT-99453']/ancestor::div[@class='MuiBox-root css-1eynrej']/descendant::input[@type='checkbox']"));
		second_check_box.click();
		if (second_check_box.isSelected()) {
			System.out.println("second box selected");
		}
		WebElement third_check_box = driver.findElement(By.xpath(
				"//p[text()='CPT-99454']/ancestor::div[@class='MuiBox-root css-1eynrej']/descendant::input[@type='checkbox']"));
		third_check_box.click();
		if (third_check_box.isSelected()) {
			System.out.println("third box selected");
		}
		js.executeScript("window.scrollTo(0,1500)");
		WebElement fourth_check_box = driver.findElement(By.xpath(
				"//p[text()='CPT-99474']/ancestor::div[@class='MuiBox-root css-1eynrej']/descendant::input[@type='checkbox']"));
		fourth_check_box.click();
		if (fourth_check_box.isSelected()) {
			System.out.println("fourth box selected");
		}
		WebElement ele=driver.findElement(By.xpath(
			"//p[@class='MuiTypography-root MuiTypography-body2 inter css-1xroguk' and contains(.,'Total Recurring Reimbursement for all Patients Per Month:')]/p"));//The script locates the element that displays the total recurring reimbursement value and extracts its text.
		String totalReimbursementValue = ele.getText();
		if (!totalReimbursementValue.equals("$110700"))//The value is compared with the expected value "$110700". If they don’t match, an error message is printed; otherwise, a success message is printed.
		{
           System.out.println("Total Recurring Reimbursement value is not $110700");
          	
        }
		else
			 System.out.println("Automation test passed successfully!"); 
       	
    
	    driver.quit();//Finally, the browser session is closed using quit() to release resource
	
}
		

	

}
