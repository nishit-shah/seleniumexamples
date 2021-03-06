package com.examples.webdrivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Checkboxes {

	 WebDriver driver;
	 
		@BeforeMethod
		public void setup() {
		
			
			WebDriverManager.chromedriver().setup();
			//WebDriverManager.firefoxdriver().setup();
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--headless");
			driver = new ChromeDriver();
			//driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			
		}
		
		
		//@Test
		public void testCheckboxes() throws InterruptedException {
			driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
			System.out.println(driver.findElement(By.cssSelector("input[id='ctl00_mainContent_chk_IndArm']")).isSelected());
			Assert.assertFalse(driver.findElement(By.cssSelector("input[id='ctl00_mainContent_chk_IndArm']")).isSelected());
			driver.findElement(By.cssSelector("input[id='ctl00_mainContent_chk_IndArm']")).click();
			Assert.assertFalse(driver.findElement(By.cssSelector("input[id='ctl00_mainContent_chk_IndArm']")).isSelected());
			System.out.println(driver.findElement(By.cssSelector("input[id='ctl00_mainContent_chk_IndArm']")).isSelected());
			if(driver.findElement(By.cssSelector("input[id='ctl00_mainContent_chk_IndArm']")).isSelected()) {
				System.out.println(driver.findElement(By.cssSelector("div[id='ctl00_mainContent_IndArm']")).getText());
				
			}else {
				System.out.println("Checkbox is not selected");
			}
			System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());
			Thread.sleep(2000);
			
		}
		
		@Test
		public void checkBoxExercise() {
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
			driver.findElement(By.id("checkBoxOption1")).click();
			Assert.assertTrue(driver.findElement(By.id("checkBoxOption1")).isSelected());
			driver.findElement(By.id("checkBoxOption1")).click();
			Assert.assertFalse(driver.findElement(By.id("checkBoxOption1")).isSelected());
			
			//System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());
			System.out.println(driver.findElements(By.id("checkbox-example")).size());
		}
		
		@AfterMethod
		public void tearDown() {
			driver.close();
			driver.quit();
		}

}
