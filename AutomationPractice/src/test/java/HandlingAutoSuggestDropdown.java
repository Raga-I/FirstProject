import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingAutoSuggestDropdown {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.xpath("//div[@class='cen-left-align'] //input[@id='autocomplete']")).sendKeys("uni");

		List<WebElement> allSuggestions = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='ui-id-1'] //li[@class='ui-menu-item']")));
		for (int i = 0; i < allSuggestions.size(); i++) {
			String listText = allSuggestions.get(i).getText();
			driver.findElement(By.xpath("//div[@class='cen-left-align'] //input[@id='autocomplete']"))
					.sendKeys(Keys.ARROW_DOWN);
			if (listText.equals("United Kingdom (UK)")) {
				System.out.println(
						driver.findElement(By.xpath("//div[@class='cen-left-align'] //input[@id='autocomplete']"))
								.getAttribute("value"));
				break;
			}
		}
	}
}
