package com.examples.webdrivers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dropdowns {

	WebDriver driver;

	@BeforeTest
	public void setup() {

		WebDriverManager.chromedriver().setup();
		// WebDriverManager.firefoxdriver().setup();
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless");
		driver = new ChromeDriver();
		// driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test(alwaysRun=false)
	public void testStaticDropdown() {

		try {
			driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
			WebElement staticdropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
			Select selectdropdown = new Select(staticdropdown);
			selectdropdown.selectByVisibleText("USD");
			System.out.println(selectdropdown.getFirstSelectedOption().getText());
			selectdropdown.selectByValue("AED");
			selectdropdown.selectByIndex(1);
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void testDynamicDropdown() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//li[@class='city_selected ']/following-sibling::li[1]")).click();
		Thread.sleep(2000);
		// xpath using indexing
		// driver.findElement(By.xpath("(//a[@value='GOI'])[2]")).click();
		// xpath using parent-child relationship
		driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='GOI']"))
				.click();
		Thread.sleep(2000);

	}

	@Test(enabled=false)
	public void testAutosuggestiveDropdowns() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.findElement(By.id("autosuggest")).sendKeys("Ind");
		List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item']"));
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase("India")) {
				option.click();
				Thread.sleep(2000);
				break;
			}
		}
	}

	@Test(enabled=false)
	public void testFlightSearch() throws InterruptedException {
		driver.get("https://www.cleartrip.com/");
		Thread.sleep(3000);

		WebElement adults_we = driver.findElement(By.id("Adults"));
		Select adults = new Select(adults_we);
		adults.selectByVisibleText("2");

		WebElement childern_we = driver.findElement(By.id("Childrens"));
		Select children = new Select(childern_we);
		children.selectByIndex(2);

		driver.findElement(By.className("datePicker")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("MoreOptionsLink")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("airline")).sendKeys("spicejet");

		driver.findElement(By.id("SearchBtn")).click();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.id("homeErrorMessage")).getText());

	}

	@Test(enabled=true)
	public void AutoSuggestDropDownAssignment() throws InterruptedException {
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.id("autocomplete")).sendKeys("ind");
		List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item']"));
		Actions mouse = new Actions(driver);
		for(WebElement option: options) {
			if(option.getText().equalsIgnoreCase("india")) {
			
				mouse.moveToElement(option).click().build().perform();
				System.out.println(driver.findElement(By.id("autocomplete")).getAttribute("value"));
			}
		}
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
