import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingWebTables {

	public static void main(String[] args) {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		// scroll to web table
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,600)");

		// get the count of rows
		WebElement productTable = driver.findElement(By.xpath("//div[@class='left-align'] //table[@id='product']"));
		List<WebElement> allRows = productTable.findElements(By.tagName("tr"));
		System.out.println("Row count is: " + allRows.size());
		System.out.println();

		// Print the third row

		List<WebElement> row2Columns = allRows.get(2).findElements(By.tagName("td"));
		for (WebElement colText : row2Columns) {
			System.out.println(colText.getText());
		}

		// Get the count of columns
		System.out.println();
		List<WebElement> allColumns = productTable.findElements(By.tagName("th"));
		System.out.println("Column count is: " + allColumns.size());

	}

}
