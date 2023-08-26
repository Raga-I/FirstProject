import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingExplicitWait {

	public static void main(String[] args) {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");

		// Get the username and password from the webpage.
		String uiText = driver.findElement(By.xpath("//p[@class='text-center text-white']")).getText();
		String[] splittedString = uiText.split(" ");
		String uname = splittedString[2];
		String pwd = splittedString[6].split("\\)")[0];

		// Enter username and password
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pwd);

		// Click radio button and handle popup button
		driver.findElement(By.xpath("//input[@value='user']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn"))).click();

		// Select consultant from dropdown
		WebElement dropdown = driver.findElement(By.xpath("//div[@class='form-group']/select"));
		dropdown.click();
		Select selectItem = new Select(dropdown);
		selectItem.selectByVisibleText("Consultant");

		// Select checkbox and click on signIn
		driver.findElement(By.id("terms")).click();
		driver.findElement(By.id("signInBtn")).click();

		// navigate to next page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-footer']")));
		List<WebElement> list_carts = driver.findElements(By.xpath("//div[@class='card-footer']/button"));
		for (WebElement addToCart : list_carts) {
			addToCart.click();
		}

		// click on checkout
		driver.findElement(By.xpath("//a[@class='nav-link btn btn-primary']")).click();
	}

}
