import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingWindows {

	public static void main(String[] args) {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/");

		// Scroll the page and click on multiple windows
		WebElement multiWindows = driver.findElement(By.partialLinkText("Multiple Windows"));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", multiWindows);
		multiWindows.click();

		// in the parent window click the link
		driver.findElement(By.partialLinkText("Click Here")).click();

		// child window will be opened.
		// get the window handles in set and set the name for parent win and child win
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> itr = allWindows.iterator();
		String parentWindow = itr.next();
		String childWindow = itr.next();

		// driver switch from parent to child. print the text
		driver.switchTo().window(childWindow);
		System.out.println(driver.findElement(By.xpath("//div[@class=\"example\"]/h3")).getText());

		// driver switch from child to parent print the text
		driver.switchTo().window(parentWindow);
		System.out.println(driver.findElement(By.xpath("//div[@class=\"example\"]/h3")).getText());
	}
}
