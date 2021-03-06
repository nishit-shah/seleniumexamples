package com.examples.webdrivers;



import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class childWindows {
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
	
	//@Test
	public void testChildWindow() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
		driver.findElement(By.cssSelector(".blinkingText")).click();
		
		//Get Child Window using For Loop
		Set<String> windows = driver.getWindowHandles();
		//System.out.println(windows);
		for(String window:windows) {
			//System.out.println(window.);
			if(driver.switchTo().window(window).getCurrentUrl().contains("documents-request")) {
				System.out.println(driver.findElement(By.cssSelector("a[href='mailto:mentor@rahulshettyacademy.com']")).getText());
			}
		}
		
		//Get Child Window using Iterator
		Iterator<String> it = windows.iterator();
		String parentId= it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		System.out.println(driver.findElement(By.cssSelector(".im-para.red")).getText());
		String emailId = driver.findElement(By.cssSelector(".im-para.red")).getText().split("at")[1].trim().split(" ")[0];
		driver.switchTo().window(parentId);
		driver.findElement(By.id("username")).sendKeys(emailId);
		
	}
	
	//@Test
	public void windowAssignment() {
		driver.get("http://the-internet.herokuapp.com/");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@href='/windows']"))));
		driver.findElement(By.xpath("//a[@href='/windows']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@href='/windows/new']"))));
		driver.findElement(By.xpath("//a[@href='/windows/new']")).click();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();
		//System.out.println("Child Window ID:"+childId);
		driver.switchTo().window(childId);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='example']/h3"))));
		System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3")).getText());
		driver.switchTo().window(parentId);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='example']/h3"))));
		System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3")).getText());
	}
	
	//@Test
	public void testFrames() {
		driver.get("https://jqueryui.com/droppable/");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".demo-frame"))));
		driver.switchTo().frame(0);
		driver.findElement(By.id("draggable")).click();
		System.out.println(driver.findElement(By.id("draggable")).getText());
		Actions mouse = new Actions(driver);
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement destination = driver.findElement(By.id("droppable"));
		mouse.dragAndDrop(source, destination).build().perform();
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void testFramesAssignment() {
		driver.get("http://the-internet.herokuapp.com/");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@href='/nested_frames']"))));
		driver.findElement(By.xpath("//a[@href='/nested_frames']")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@src='/frame_top']")));
		driver.switchTo().frame("frame-middle");
		//driver.switchTo().frame(driver.findElement(By.xpath("//*[@src='/frame_middle']")));
		//System.out.println(driver.findElements(By.tagName("frame")).size());
		System.out.println(driver.findElement(By.id("content")).getText());
		
		driver.switchTo().defaultContent();
		
	}
}
