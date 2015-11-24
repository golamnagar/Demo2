package demoVerifyElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class VerifyElement {

	
	public static void main(String[] args) throws Exception {
		String vURL;
   WebDriver driver = new FirefoxDriver();
   driver.get("http://google.com");
   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   driver.manage().window().maximize();
   Thread.sleep(2000);
   driver.findElement(By.cssSelector("#lst-ib")).clear();
   driver.findElement(By.cssSelector("#lst-ib")).sendKeys("New York Stock");
   driver.findElement(By.xpath(".//*[@id='sblsbb']/button")).click();
   Thread.sleep(5000);
   driver.findElement(By.xpath("//a[contains(text(),'The New York Stock Exchange')]")).click();
   //driver.findElement(arg0)
   // boolean linkPresent = driver.findElement(By.linkText("The New York Stock Exchange‎")).isDisplayed();
   //System.out.println(linkPresent);
   //  driver.findElement(By.linkText("The New York Stock Exchange‎")).click();
   // driver.findElement(By.)
   // driver.findElement(By.xpath("")).sendKeys("");
   
   System.out.println("The test is Pass");
   
   Thread.sleep(5000);
   driver.close();
	}

}
