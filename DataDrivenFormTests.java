package com.demoqa.tests;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDrivenFormTests {
	
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
	public void testCalenderHandling() {
		driver.get("https://demoqa.com/date-picker");
		
		WebElement dateInput = driver.findElement(By.id("datePickerMonthYearInput"));
		dateInput.click();
		
		WebElement monthDropdown = driver.findElement(By.className("react-datepicker__month-select"));
		Select selectMonth = new Select(monthDropdown);
		selectMonth.selectByVisibleText("May");
		
		WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
		Select selectYear = new Select(yearDropdown);
		selectYear.selectByVisibleText("1996");
		
		driver.findElement(By.xpath("//div[@class ='react-datepicker__day react-datepicker__day--015']")).click();
		
		@Nullable
		String selectedDate = dateInput.getAttribute("value");
		Assert.assertTrue(selectedDate.contains("05/15/1996"), "Date was not selected correctly");
	}
	
	@DataProvider(name = "studentData")
    public Object[][] getStudentData() {
        return new Object[][]{
                {"Sank", "kumar", "sank42@example.com", "Male", "9876543210"},
                {"sanky", "kumari", "sanky64@example.com", "Female", "9123456789"}
        };
    }

	
	@Test(priority = 2, groups = {"Regression"}, dataProvider = "studentData")
    public void testDataDrivenFormSubmission(String firstName, String lastName, String email, String gender, String mobile) {
        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);

        driver.findElement(By.xpath("//label[text()='" + gender + "']")).click();

        driver.findElement(By.id("userNumber")).sendKeys(mobile);

        WebElement submitBtn = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        submitBtn.click();

        WebElement modalContent = driver.findElement(By.className("modal-content"));
        String modalText = modalContent.getText();

        Assert.assertTrue(modalText.contains(firstName), "First name not found in modal!");
        Assert.assertTrue(modalText.contains(lastName), "Last name not found in modal!");
        Assert.assertTrue(modalText.contains(email), "Email not found in modal!");
        Assert.assertTrue(modalText.contains(mobile), "Mobile number not found in modal!");

        driver.findElement(By.id("closeLargeModal")).click();
	}
}
