import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingFrames {

	public static void main(String[] args) {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/");

		// Scroll the page and click on nested frames
		WebElement nestedFrames = driver.findElement(By.linkText("Nested Frames"));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", nestedFrames);
		nestedFrames.click();
	
		//go to frame tag first
		//next traverse to frameset tag
		//there whe have left middle right. traverse to middle frame tag
		driver.switchTo().frame(driver.findElement(By.name("frame-top")));		
		driver.switchTo().frame(driver.findElement(By.xpath("//frameset[@name='frameset-middle'] //frame[@name='frame-middle']")));
		
		//find the text and print it.
		WebElement text = driver.findElement(By.xpath("//div[@id='content']"));
		System.out.println(text.getText());
		
		}

}
