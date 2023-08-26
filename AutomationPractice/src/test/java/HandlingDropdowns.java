import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingDropdowns {

	public static void main(String[] args) {
		WebDriver driver;

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.findElement(By.name("name")).sendKeys("Raga Iswariya S");
		driver.findElement(By.name("email")).sendKeys("ishwarya@gmail.com");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("ishwarya");
		driver.findElement(By.id("exampleCheck1")).click();
		WebElement dropdown = driver.findElement(By.id("exampleFormControlSelect1"));
		dropdown.click();
		Select gender = new Select(dropdown);
		gender.selectByVisibleText("Female");

		driver.findElement(By.id("inlineRadio1")).click();
		driver.findElement(By.xpath("//div[@class='form-group']//input[@name='bday']")).sendKeys("01-08-1997");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		System.out.println(
				driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText());

	}

}
