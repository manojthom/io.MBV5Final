package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



public class CommonMethords {
	

	/****************************************************************************
	 * {@summary : This class includes all function that are reusable }
	 * 
	 * @author Manoj Thomas
	 * @since 08-03-2023
	 */
	
	
	/**
	 * @param intArray
	 * @return 
	 * @description Takes an integer array returns minimum values in that array  
	 */
	
	private static final String DEFAULT_FILE_PATTERN = "yyyy-MM-dd-HH-mm-ss";
	public int minValueInArray (int [] intArray) {
		
		int minValue = intArray[0];
		for(int i =0 ; i<intArray.length;i++ ) {
			
			if(minValue > intArray[i]) {
				
				minValue = intArray[i];
				
			}
			
		}
		
		Log.info( " MinValue of Array  : " + minValue);
		
		
		return minValue;
		
		
	}
	
	/**
	 * @param intArray
	 * @return 
	 * @description Takes an integer array returns max value in that array  
	 */
public int maxValueInArray (int [] intArray) {
		
		int maxValue = intArray[0];
		for(int i =0 ; i<intArray.length;i++ ) {
			
			if(maxValue < intArray[i]) {
				
				maxValue = intArray[i];
				
			}
			
		}
		
		Log.info( " MaxValue of Array  : " + maxValue);
		
		
		return maxValue;
		
		
	}

/**
 * @param String 
 * @return  void 
 * @description Takes a string and writes to text file, in project directory 
 */
 public void createAndWriteTXTFile (String str) {
	 try {
	      FileWriter myWriter = new FileWriter("FileWriteForMB.txt");
	      myWriter.write(str);
	      myWriter.close();
	      System.out.println("Successfully wrote to the file." );
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	 
 }
 
 	/**
	 * 
	 * @description Retuns value  from property file for  property key in EnvCong  
	 */
 public String fetchPropertyValueFromEnvConfig (String strPropertyKey) throws IOException{
	
	 PropertiesFileMethords propertiesFileMethord = new PropertiesFileMethords();
	 Properties prop= propertiesFileMethord.readPropertiesFile(System.getProperty("user.dir")+"\\src\\main\\resources\\envConfig.properties");
	 return prop.getProperty(strPropertyKey).toString();
	 
}
	/**
	 * 
	 * @description Retuns value  from property file for  property key in EnvCong  
	 */
 	public String fetchPropertyValueFromTestData (String strPropertyKey) throws IOException{
	
	 PropertiesFileMethords propertiesFileMethord = new PropertiesFileMethords();
	 Properties prop= propertiesFileMethord.readPropertiesFile(System.getProperty("user.dir")+"\\src\\main\\resources\\testData.properties");
	 return prop.getProperty(strPropertyKey).toString();
	 
}
 	/**
	 * 
	 * @description Retuns value  from property file for  property key in EnvCong  
	 */
 	public String fetchFromObjectRepo (String strPropertyKey) {
	
	 PropertiesFileMethords propertiesFileMethord = new PropertiesFileMethords();
	 Properties prop=null;
	try {
		prop = propertiesFileMethord.readPropertiesFile(System.getProperty("user.dir")+"\\src\\main\\resources\\objectrepo.properties");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Log.fatal("Object Repo properties read Crashed ");
	}
	 return prop.getProperty(strPropertyKey).toString();
	 
}
 /**
	 * 
	 * @description Takes Values from property file and opens corresponding browser
	 */
 
 public WebDriver openBrowser ( ) throws IOException {
	 WebDriver driver = null; 
		switch (fetchPropertyValueFromEnvConfig("browser")){
		case "chrome":
			
		  	WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(chromeOptions);
		break ; 
		
		case "edge":
			
			WebDriverManager.edgedriver().setup();
			driver = new  EdgeDriver();
		
			break;
			
		default :
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions1 = new ChromeOptions();
			chromeOptions1.addArguments("--remote-allow-origins=*");
			chromeOptions1.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(chromeOptions1);
			break;
		
		}
		return driver;
	 	
 }
 public String removePoundComa (String str ) {
		
		
		return str.replaceAll("£", "").replace(",","").replace(":", "");
		
	}
 public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{


     TakesScreenshot scrShot =((TakesScreenshot)webdriver);
     File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
     File DestFile=new File(fileWithPath);
     FileUtils.copyFile(SrcFile, DestFile);

 }

 public  String createFileName() {
	    Date date = new Date(System.currentTimeMillis());
	    SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FILE_PATTERN);
	    return format.format(date);
	  }

}
