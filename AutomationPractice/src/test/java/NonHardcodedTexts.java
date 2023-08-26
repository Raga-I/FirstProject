import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NonHardcodedTexts {

	public static void main(String[] args) {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		// 1. select any checkbox
		WebElement optionClick = driver.findElement(By.id("checkBoxOption1"));
		optionClick.click();

		// 2. grab the label of selected checkbox. put into variable
		WebElement optionText = driver.findElement(By.xpath("//label[@for='bmw']"));
		String text = optionText.getText();

		// 3. select an option in dropdown. Here option to select should come from step2
		List<WebElement> allDropdowns = driver.findElements(By.xpath("//select[@id='dropdown-class-example']/option"));
		for (int i = 0; i < allDropdowns.size(); i++) {
			String dropDownText = allDropdowns.get(i).getText();
			if (dropDownText.equals(text)) {
				allDropdowns.get(i).click();
				break;
			}
		}

		// 4. enter the step 2 grabbed lable text in edit box
		driver.findElement(By.xpath("//input[@placeholder='Enter Your Name']")).sendKeys(text);

		// 5. click alert and the verify if text grabbed from step2 is present in the
		// popup message
		driver.findElement(By.id("alertbtn")).click();
		String alertText = driver.switchTo().alert().getText();
		String splitText = alertText.split(" ")[1];
		String actualText = splitText.split(",")[0];
		Assert.assertEquals(actualText, text);
	}
}
