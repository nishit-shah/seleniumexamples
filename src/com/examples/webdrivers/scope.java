package com.examples.webdrivers;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class scope {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//count links on the page
	//@Test
	public void countLinks() {
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		System.out.println(driver.findElements(By.tagName("a")).size());
	}
	
	//count links only in the footer section
	//Example of limiting scope of webdriver
	//@Test
	public void countFooterLinks() {
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		WebElement footer = driver.findElement(By.id("gf-BIG"));
		System.out.println(footer.findElements(By.tagName("a")).size());
		
		//count links of first section of footer
		WebElement firstColumn = driver.findElement(By.cssSelector("td:nth-of-type(1) > ul"));
		System.out.println(firstColumn.findElements(By.tagName("a")).size());
		
		//iterate through all the links of one column
		for(int i=1;i<firstColumn.findElements(By.tagName("a")).size();i++) {
			
			String clickOnLinks = Keys.chord(Keys.CONTROL,Keys.ENTER);
			firstColumn.findElements(By.tagName("a")).get(i).sendKeys(clickOnLinks);
			
			
		}
		
		Set<String> windows = driver.getWindowHandles();
		Iterator<String>it = windows.iterator();
		for(String window:windows) {
			window = it.next();
			System.out.println(driver.switchTo().window(window).getTitle());
		}
		
	}
	
	@Test
	public void testAssignment() throws InterruptedException {
		String chkBox;
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		System.out.println(driver.findElements(By.xpath("//div[@id='checkbox-example']/fieldset/label")).size());
		for(int i =0;i<driver.findElements(By.xpath("//div[@id='checkbox-example']/fieldset/label")).size();i++) {
			driver.findElements(By.xpath("//div[@id='checkbox-example']/fieldset/label/input")).get(i).click();
			chkBox=driver.findElements(By.xpath("//div[@id='checkbox-example']/fieldset/label")).get(i).getText();
			//System.out.println(chkBox);
			
			WebElement selectBox = driver.findElement(By.id("dropdown-class-example"));
			Select selectOptions = new Select(selectBox);
			selectOptions.selectByVisibleText(chkBox);
			Thread.sleep(2000);
			
			driver.findElement(By.id("name")).sendKeys(chkBox);
			driver.findElement(By.id("alertbtn")).click();
			if(driver.switchTo().alert().getText().contains(chkBox)) {
				driver.switchTo().alert().accept();
			}
		}
	}
	
}
