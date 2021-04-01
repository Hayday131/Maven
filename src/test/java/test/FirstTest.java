package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTest {
	
	@Test
	public void login() throws InterruptedException  {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://magento.com");
		driver.findElement(By.xpath("//*[@id=\"block-header\"]/ul/li[9]/a/span[1]/div")).click();;
		driver.findElement(By.id("email")).sendKeys("unclespectrum@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("whatever");
		driver.findElement(By.id("send2")).click();
		
		Thread.sleep(3000);
		
		String error_msg = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText();
		
		Assert.assertEquals(error_msg, "Invalid login or password.");
		
//		if(error_msg.equals("Invalid login or password."))
//		{
//			System.out.println("Test Pass");
//		}
		
		driver.quit();
	}
	
	@Test
	public void register() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://magento.com");
		driver.findElement(By.className("account-icon")).click();
		driver.findElement(By.xpath("//*[@id=\"register\"]")).click();
		driver.findElement(By.id("firstname")).sendKeys("Maria");
		driver.findElement(By.id("lastname")).sendKeys("Jag");
		driver.findElement(By.id("email_address")).sendKeys("unclespectrum@gmail.com");
		driver.findElement(By.id("self_defined_company")).sendKeys("Oasis");
		
		Select cp = new Select(driver.findElement(By.id("company_type")));
		cp.selectByValue("deployment");
//		cp.selectByIndex(3);
		
		Select role = new Select(driver.findElement(By.id("individual_role")));
		role.selectByVisibleText("Technical/developer");
		
		Select country = new Select(driver.findElement(By.id("country")));
		country.selectByIndex(15);
		
		driver.findElement(By.id("password")).sendKeys("Whatever@123");
		driver.findElement(By.id("password-confirmation")).sendKeys("Whatever@123");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"recaptcha-f979c2ff515d921c34af9bd2aee8ef076b719d03\"]/div/div/iframe")));
		driver.findElement(By.className("recaptcha-checkbox-border")).click();
		driver.switchTo().defaultContent();
		
		if(!driver.findElement(By.id("agree_terms")).isSelected());
		{
			driver.findElement(By.id("agree_terms")).click();
		}
		driver.quit();
	}

}


