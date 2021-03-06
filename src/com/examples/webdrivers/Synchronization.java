package com.examples.webdrivers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Synchronization {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 15);
		
	}
	
	//@Test
	public void testAjaxLoadingWait() {
		driver.get("http://www.itgeared.com/demo/1506-ajax-loading.html");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(),'Click to load get data via Ajax!')]"))));
		driver.findElement(By.xpath("//a[contains(text(),'Click to load get data via Ajax!')]")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("results"))));
		System.out.println(driver.findElement(By.id("results")).getText());
	}
	
	@Test
	public void testFluentWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='start']/button"))));
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		Wait<WebDriver> flWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class);
		
		WebElement loaderMsg = flWait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				if(driver.findElement(By.id("finish")).isDisplayed()) {
					return driver.findElement(By.id("finish"));
				}else {
					return null;
				}
				
	}
		});
		
		System.out.println(driver.findElement(By.id("finish")).isDisplayed());
		
	}
	
	@AfterTest
	public void teardown() {
		driver.close();
		driver.quit();
	}
}
