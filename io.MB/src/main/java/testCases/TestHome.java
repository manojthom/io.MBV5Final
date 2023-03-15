package testCases;

//import static com.aventstack.extentreports.ExtentReports.ExtentTestManager.startTest;
import org.testng.annotations.Test;

//import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import utils.Log;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeSuite;

import static ExtentReports.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class TestHome extends BasePage {
	
	
	
	@Test(priority =1 , description = "TC Validdate all prices of class A model is in range")
  public void TC_Validate_price_range_of_ClassA_is_in_range (Method method) throws InterruptedException, ParseException, NumberFormatException, IOException {
	  //startTest(method.getName(), "Validate all prices of class A models are in range");
			logger= extent.createTest(method.getName(),"TC Validdate all prices of class A model is in range");	
			Log.info("TC Validate price range of ClasssA is in range");
			logger.info("Message: Validating step1 ");
			
			homePage.selectClassAHatchback();
			int [] listOfCarPrices = homePage.listofCarPrices();
			homePage.writeMinMaxValueToTxtFile(listOfCarPrices); 
			Assert.assertEquals(homePage.validate_Price_in_range(listOfCarPrices), true," TC Failed Price out of range");
			
			
			extent.flush();
  }

   
public WebDriver getDriver() {
	// TODO Auto-generated method stub
	 return driver;
	
}


}
