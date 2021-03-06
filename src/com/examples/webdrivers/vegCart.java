package com.examples.webdrivers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class vegCart {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setup() {

		WebDriverManager.chromedriver().setup();
		// WebDriverManager.firefoxdriver().setup();
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless");
		driver = new ChromeDriver();
		// driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait= new WebDriverWait(driver, 5);
	}

	@Test
	public void addItemsToCart() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//h4[@class='product-name']"))));
		List<WebElement> products = driver.findElements(By.xpath("//h4[@class='product-name']"));
		String[] items = { "Cucumber", "Brocolli" };
		List itemsList = Arrays.asList(items);
		int j = 0;
		for (int i = 0; i < products.size(); i++) {
			String[] product = products.get(i).getText().split(" ");
			if (itemsList.contains(product[0])) {
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				//Thread.sleep(10000);
				j++;
				if (j == itemsList.size()) {
					break;
				}

			}
		}
		if (!driver.findElement(By.xpath("//div[@class='cart-info']/table/tbody/tr[1]/td[3]")).getText().isEmpty()) {
			System.out.println("Viewing the cart details");
			driver.findElement(By.xpath("//a[@class='cart-icon']")).click();
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".promocode"))));
			driver.findElement(By.cssSelector(".promocode")).sendKeys("rahulshettyacademy");
			driver.findElement(By.cssSelector(".promoBtn")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".promoInfo"))));
			System.out.println(driver.findElement(By.cssSelector(".promoInfo")).getText());
		}

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
