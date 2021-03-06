package com.examples.webdrivers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class calendar {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait =new WebDriverWait(driver,30);
	}
	
	@Test
	public void testDatepicker() throws InterruptedException {
		//open webpage
		driver.get("https://www.path2usa.com/travel-companions");
		
		//Wait for the Promo banner to be invisible
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//img[@src='https://www.path2usa.com/responsive_filemanager/source/CAG-No-Renewal-Fee/VC-CAG-No-Renewal-Fee_1680x655.jpg']"))));
		
		
		//open datepicker
		WebElement datepicker = driver.findElement(By.xpath("//input[@id='travel_date']"));
		datepicker.click();
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("arguments[0].click()", datepicker);
		
		//select the month
		while(!driver.findElement(By.cssSelector("[class='datepicker-days'] [class='datepicker-switch']")).getText().contains("April")) {
			driver.findElement(By.cssSelector("[class='datepicker-days'] [class='next']")).click();
		}
		
		//get all the dates from datepicker using css class
		List<WebElement> dates=driver.findElements(By.cssSelector(".day"));
		//count total dates in month
		int totaldates = driver.findElements(By.cssSelector(".day")).size();
		
		//Run for loop to find and click on selected date
		for(int i=0;i<totaldates;i++) {
			String selecteddate = dates.get(i).getText();
			if(selecteddate.equalsIgnoreCase("24")) {
				dates.get(i).click();
				System.out.println("Found the date");
				break;
			}
		}
	}
	
	
}
