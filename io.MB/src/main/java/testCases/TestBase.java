package testCases;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import utils.CommonMethords;
import utils.Log;

public class TestBase {
	 public WebDriver driver; 
	HomePage homePage; 
	CommonMethords commonMethords = new CommonMethords();
	
	ChromeOptions chromeOptions = new ChromeOptions();
	ExtentSparkReporter  htmlReporter;
    ExtentReports extent;
    ExtentTest logger;
    
	@BeforeTest
	  public void beforeTest() throws InterruptedException, IOException {
		
		
		 htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/extent-reports/MBExtentReport"+ commonMethords.createFileName()+".html");
	     extent = new ExtentReports ();
	     extent.attachReporter(htmlReporter);
	     extent.setSystemInfo("Environment", " Production Envirionment");
	     extent.setSystemInfo("User Name", "Manoj Thomas");
	     BasicConfigurator.configure();
	     

	     htmlReporter.config().setDocumentTitle(" Technical Assignment Benz");
	     htmlReporter.config().setReportName("Extent Report - Tech Analysis");
	      	driver = commonMethords.openBrowser();
			driver.get(commonMethords.fetchPropertyValueFromEnvConfig("url"));
			driver.manage().window().maximize();
			homePage = new HomePage(driver);	
			homePage.acceptCookies();
	  }

	  @AfterSuite
	  public void afterSuite() throws InterruptedException {
		  driver.quit();
	  }

	  @BeforeClass
	  public void classLevelSetup() {
		  
	      Log.info("Tests is starting!");
	      String className = this.getClass().getName();
	      System.out.println("Class Name: " + className + "\n");
	  }
	  
	  @BeforeMethod
	  public void methodLevelSetup() {
	      
	  }
	  @AfterMethod
	     public void getResult(ITestResult result){
	     if(result.getStatus() == ITestResult.FAILURE){
	     //logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
	     //MarkupHelper is used to display the output in different colors
	     logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
	     logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	     }else if(result.getStatus() == ITestResult.SKIP){
	     //logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
	     logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
	     }
	     }
	  @AfterTest 
	  public void afterTest() throws InterruptedException {
		//homePage.tearDown();
		//driver.quit();
		  
	  }

}
