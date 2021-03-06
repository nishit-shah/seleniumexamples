package com.examples.webdrivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class actionsTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testMouseActions() {
		driver.get("https://www.amazon.in/");
		Actions mouse = new Actions(driver);
	
		//single action
		//mouse.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build().perform();
		
		//composite actions
		//mouse.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.LEFT_SHIFT).sendKeys("All in one Printers").build().perform();
		
		//Right Click
		mouse.moveToElement(driver.findElement(By.id("nav-link-accountList"))).contextClick().build().perform();
		
	
	}
	
	
}
