package com.demoqa.tests;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AdvancedUITests {
	
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
	@Test(priority = 1 , groups = {"Regression"})
	public void testHandalingAlerts() {
		driver.get("https://demoqa.com/alerts");
		
		driver.findElement(By.id("confirmButton")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		WebElement confirmMessage = driver.findElement(By.id("confirmResult"));
        Assert.assertEquals(confirmMessage.getText(), "You selected Ok");
		
	}
	
	
@Test(priority = 2, groups = {"Regression"})
  public void testHandlingIFrames() {
      driver.get("https://demoqa.com/frames");

      driver.switchTo().frame("frame1");

      WebElement heading = driver.findElement(By.id("sampleHeading"));
      String headingText = heading.getText();

      Assert.assertEquals(headingText, "This is a sample page");

      driver.switchTo().defaultContent();
  }

@Test(priority = 3, groups = {"Regression"})
public void testHandlingBrowserWindows() {
	driver.get("https://demoqa.com/browser-windows");
	
	        String parentWindow = driver.getWindowHandle();
	
	        driver.findElement(By.id("windowButton")).click();
	
	        Set<String> windowHandles = driver.getWindowHandles();
	        for (String handle : windowHandles) {
	            if (!handle.equals(parentWindow)) {
	                driver.switchTo().window(handle);
	                break;
	            }
	        }
	        WebElement heading = driver.findElement(By.id("sampleHeading"));
	        String newWindowText = heading.getText();
	        Assert.assertEquals(newWindowText, "This is a sample page");
	
	        driver.close();
	
	        driver.switchTo().window(parentWindow);
	
}
}






















//package com.demoqa.tests;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//import java.util.Set;
//
//public class AdvancedUITests {
//
//    WebDriver driver;
//
//    @BeforeClass
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    }
//
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    /**
//     * Test 1: Handling Alerts (Confirm Box)
//     */
//    @Test(priority = 1, groups = {"Regression"})
//    public void testHandlingAlerts() {
//        driver.get("https://demoqa.com/alerts");
//
//        // Click confirm button
//        driver.findElement(By.id("confirmButton")).click();
//
//        // Switch to alert and accept
//        Alert alert = driver.switchTo().alert();
//        alert.accept();
//
//        // Verify confirmation message
//        
//    }
//
//    /**
//     * Test 2: Handling iFrames
//     */
//    @Test(priority = 2, groups = {"Regression"})
//    public void testHandlingIFrames() {
//        driver.get("https://demoqa.com/frames");
//
//        // Switch to iframe by ID
//        driver.switchTo().frame("frame1");
//
//        // Get text inside the iframe
//        WebElement heading = driver.findElement(By.id("sampleHeading"));
//        String headingText = heading.getText();
//
//        // Assert text
//        Assert.assertEquals(headingText, "This is a sample page");
//
//        // Switch back to default content
//        driver.switchTo().defaultContent();
//    }
//
//    /**
//     * Test 3: Handling Browser Windows
//     */
//    @Test(priority = 3, groups = {"Regression"})
//    public void testHandlingBrowserWindows() {
//        driver.get("https://demoqa.com/browser-windows");
//
//        // Store parent window handle
//        String parentWindow = driver.getWindowHandle();
//
//        // Click "New Window" button
//        driver.findElement(By.id("windowButton")).click();
//
//        // Switch to new window
//        Set<String> windowHandles = driver.getWindowHandles();
//        for (String handle : windowHandles) {
//            if (!handle.equals(parentWindow)) {
//                driver.switchTo().window(handle);
//                break;
//            }
//        }
//
//        // Verify text in new window
//        WebElement heading = driver.findElement(By.id("sampleHeading"));
//        String newWindowText = heading.getText();
//        Assert.assertEquals(newWindowText, "This is a sample page");
//
//        // Close new window
//        driver.close();
//
//        // Switch back to parent window
//        driver.switchTo().window(parentWindow);
//    }
//}