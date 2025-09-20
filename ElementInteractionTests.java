package com.demoqa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ElementInteractionTests {

	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
	@Test(priority = 1)
	public void testTextBoxFormSubmission() {
		driver.get("https://demoqa.com/text-box");
		
		driver.findElement(By.id("userName")).sendKeys("Sanket kumar");
		driver.findElement(By.id("userEmail")).sendKeys("Sanket123@gmail.com");
		driver.findElement(By.id("currentAddress")).sendKeys("MQ-97 bokaro");
		driver.findElement(By.id("permanentAddress")).sendKeys("420 bihar");
		
		driver.findElement(By.id("submit")).click();
		
		    String outputName = driver.findElement(By.id("name")).getText();
	        String outputEmail = driver.findElement(By.id("email")).getText();
	        String outputCurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']")).getText();
	        String outputPermanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText();

	        Assert.assertTrue(outputName.contains("John Doe"), "Name not matching!");
	        Assert.assertTrue(outputEmail.contains("johndoe@test.com"), "Email not matching!");
	        Assert.assertTrue(outputCurrentAddress.contains("123 Main Street"), "Current Address not matching!");
	        Assert.assertTrue(outputPermanentAddress.contains("456 Elm Street"), "Permanent Address not matching!");
	    }
	
		@Test(priority = 2)
		public void testCheckBoxSelection() {
			driver.get("https://demoqa.com/checkbox");

	        driver.findElement(By.cssSelector("button[title='Expand all']")).click();

	        driver.findElement(By.xpath("//span[@class='rct-title' and text()='Downloads']")).click();

	       // Assert.assertTrue(driver.findElement(By.id("result")).getText().toLowerCase().contains("downloads"));
		}
	    
}
