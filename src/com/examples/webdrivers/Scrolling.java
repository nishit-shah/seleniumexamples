package com.examples.webdrivers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scrolling {

	WebDriver driver;
	
	@BeforeTest
	public void Setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test(enabled= false)
	public void testScrolling() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		//Create Javascript executor to execute javascripts in selenium scripts
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		//Scroll on window
		jse.executeScript("window.scrollBy(0,550)");
		
		//to scroll on page object like table
		jse.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
		
		//sum of table column values
		int sum =0;
		List<WebElement> values = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
		for(WebElement value: values) {
			String cell = value.getText();
			sum =sum+Integer.parseInt(cell);
		}
		System.out.println("Total:"+sum);
		String total=driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim();

		Assert.assertEquals(sum, Integer.parseInt(total));
	}
	
	@Test
	public void WebTableAssignment() {
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		
		System.out.println("Total Rows:"+driver.findElements(By.cssSelector(".table-display tr")).size());
		
		System.out.println("Total columns:"+driver.findElements(By.cssSelector(".table-display th")).size());
		System.out.println("2nd Row Data:"+driver.findElement(By.cssSelector(".table-display tr:nth-child(3)")).getText());
	}
}
