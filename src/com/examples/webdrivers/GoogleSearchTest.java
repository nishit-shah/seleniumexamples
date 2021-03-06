package com.examples.webdrivers;

import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleSearchTest extends LocationSearch{

	public void show() {
		super.searchLocation();
		
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		
		/*
		 * System.setProperty("webdriver.gecko.driver",
		 * "drivers/geckodriver/geckodriver.exe"); WebDriver driver = new
		 * FirefoxDriver(); driver.get("https://www.google.com");
		 * driver.findElement(By.name("q")).sendKeys("selenium",Keys.RETURN);
		 * //Thread.sleep(2000); driver.wait(2000); driver.quit(); //driver.close();
		 */		
		System.out.println("inside main function");
		GoogleSearchTest gsearch = new GoogleSearchTest();
		gsearch.add();
		//SoftAssert sa = new SoftAssert();
		//sa.assertEquals(actual, expected);
	}
	
	public void add() {
		System.out.println("inside add function of googlesearch class");
	}
}
