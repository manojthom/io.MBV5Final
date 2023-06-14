package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Timestamp;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.xml.xpath.XPath;

import org.checkerframework.checker.units.qual.min;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.yaml.snakeyaml.nodes.CollectionNode;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import utils.CommonMethords;
//import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Log;
import utils.PropertiesFileMethords;

public class HomePage extends BasePage {

	/**
	 * @author Manoj Thomas
	 * 
	 */
       
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
		By obj_shadowElementHost1HomePage = By.xpath(commonMethords.fetchFromObjectRepo("obj_shadowElementHost1HomePage"));
		By obj_shadowEleCarTypes = By.className(commonMethords.fetchFromObjectRepo("obj_shadowEleCarTypes"));
		By obj_shatbackAClasssArea = By.className(commonMethords.fetchFromObjectRepo("obj_shatbackAClasssArea"));
		By obj_sbuildYourCar = By.cssSelector(commonMethords.fetchFromObjectRepo("obj_sbuildYourCar")); 
		By obj_shadowHostCofigPage=By.xpath(commonMethords.fetchFromObjectRepo("obj_shadowHostCofigPage"));
		By obj_fuelTypeWebEle= By.cssSelector(commonMethords.fetchFromObjectRepo("obj_fuelTypeWebEle"));
		By obj_selecteModelPricesList= By.cssSelector(commonMethords.fetchFromObjectRepo("obj_selecteModelPricesList"));
		By obj_scolours= By.cssSelector(commonMethords.fetchFromObjectRepo("obj_scolours"));
		By obj_sterior = By.cssSelector(commonMethords.fetchFromObjectRepo("obj_sterior"));
		

	int minPriceOneWebsite=-1, maxPriceOnWebsite=0;
	
	public void acceptCookies () throws InterruptedException {
		
		Log.info("In Methord Accept Cookies");
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Log.info("Clicking on Accept Cookies JS ");
		delay(5000);
		WebElement accept_cookies = (WebElement) js.executeScript("return document.querySelector(\"body > cmm-cookie-banner\").shadowRoot.querySelector(\"div > div > div.cmm-cookie-banner__content > cmm-buttons-wrapper > div > div > button.wb-button.wb-button--primary.wb-button--small.wb-button--accept-all\")");
		accept_cookies.click();
		Log.info("Exiting AcceptCookies Methord ");
		delay(1000);
	}
	
	/**
	 * {@summary This functions navigates to the butt}
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	
	public void selectClassAHatchback() throws InterruptedException, ParseException {
		
		WebElement shadowElementHost1HomePage = driver.findElement(obj_shadowElementHost1HomePage);
		SearchContext shadowroot1HomePage = getShadowRootElement(shadowElementHost1HomePage);
		List<WebElement> shadowEleCarTypes = shadowroot1HomePage.findElements(obj_shadowEleCarTypes);
		Log.info("Click our Models");
		delay(1000);
	
		scrollDownPage();
		
		selectCarType("Hatchbacks",shadowEleCarTypes);
		Log.info("In Select Class A Hatchback ");
		
		WebElement shatbackAClasssArea= shadowroot1HomePage.findElement(obj_shatbackAClasssArea);
		Actions actions = new Actions(driver);
		actions.moveToElement(shatbackAClasssArea).perform();
		Log.info("Mousehovered");
		
		WebElement sbuildYourCar = shadowroot1HomePage.findElement(obj_sbuildYourCar);
		sbuildYourCar.click();
		Log.info("Clicked On hatchback ");
		delay(3000);
		
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,600)", "");
		Log.info("Moved to Filter ");
		delay(1000);
		
	}
		
	/**
	 * {@summary Gets List of element and return element with max or min value based on signature. find min or max for paint work}
	 * @param lt
	 * @param minOrMax
	 * @return
	 */

	public WebElement extriorItemMinMax(List <WebElement> lt, String minOrMax) {
		
		boolean flag = false; 
		int minPriceOnWebIndex=0;
		int maxPriceOnWebIndex=0;
		for(int i=0;i<lt.size();i++) {
			
			if((lt.get(i).getText()).contains("without extra charge")){
				minPriceOneWebsite=0;
				minPriceOnWebIndex=i;
				flag=true;
			}else {
			//System.out.println("Elements" + lt.get(i).getText());
			//System.out.println("value "+ removePoundComa(lt.get(i).getText()));
			
			int temp = Integer.parseInt(removePoundComa(lt.get(i).getText()));
			
			if (minPriceOneWebsite>(int)temp || flag==false ) {
				
				minPriceOneWebsite= temp; 
				minPriceOnWebIndex=i;
				flag=true;
			}
			
			if (maxPriceOnWebsite<temp ) {
				
				maxPriceOnWebsite= temp; 
				maxPriceOnWebIndex=i;
			}
		
		}
	}
		
		if (minOrMax.contains("min")) {
		return lt.get(minPriceOnWebIndex);
		}else
		{
		return lt.get(maxPriceOnWebIndex);	
		}
	}
	/**
	 *  This is to clean the price string. 
	 * @param str
	 * @return
	 */
	
	public String removePoundComa (String str ) {
		
		
		return str.replaceAll("£", "").replace(",","");
		
	}
	
	
	public SearchContext getShadowRootElement(WebElement element) {
		SearchContext ele = (SearchContext) ((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot", element);
		        return ele;
		    }
	
	public void delay(int milsec) throws InterruptedException {
		
		Thread.sleep(milsec);
	}
	
	
	public void scrollDownPage() throws InterruptedException {
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,600)", "");
		delay(1000);
		js1.executeScript("window.scrollBy(0,600)", "");
		delay(1000);
		js1.executeScript("window.scrollBy(0,800)", "");
		delay(3000);
		return ; 
	}
	
	public void selectCarType (String carType, List <WebElement> lstOfCarTypes) throws InterruptedException {
		
		
		for(int i=0;i<lstOfCarTypes.size();i++) {
			//Log.info("Elements" + lstOfCarTypes.get(i).getText());
			
			if (lstOfCarTypes.get(i).getText().equals(carType)){
				lstOfCarTypes.get(i).click();
				Log.info("Clicking hatchback " + i );
				delay(1000);
			}
			
		}
		
		return ;	
	}
	/**
	 *  Filter based on deisel, then get all the price of car to and return int array 
	 * @return
	 * @throws InterruptedException
	 */
	public int[] listofCarPrices () throws InterruptedException {
		
		delay(2000);
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.presenceOfElementLocated (obj_shadowHostCofigPage));
		WebElement shadowHostCofigPage = driver.findElement(obj_shadowHostCofigPage);
		SearchContext shadowRoot1CofigPage = getShadowRootElement(shadowHostCofigPage);
		delay(3000);
		WebElement fuelTypeWebEle = shadowRoot1CofigPage.findElement(obj_fuelTypeWebEle);
		//w.until(ExpectedConditions.presenceOfElementLocated (obj_fuelTypeWebEle));
		fuelTypeWebEle.click();
		Log.info("Selected FuelType button click ");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement selDeiselCheckBox = (WebElement) js.executeScript("return document.querySelector(\"body > div.root.responsivegrid > div > div > div > owcc-car-configurator\").shadowRoot.querySelector(\"#cc-app-container-main > div.cc-app-container__main-frame.cc-grid-container > div.cc-grid-container.ng-star-inserted > div > div:nth-child(2) > cc-motorization > cc-motorization-filters > cc-motorization-filters-form > form > div > div.cc-motorization-filters-form__primary > div.cc-motorization-filters-form__primary-filters.ng-star-inserted > cc-motorization-filters-primary-filters > div > fieldset > wb-multi-select-control > div > div > wb-checkbox-control:nth-child(1) > label\")");
		//WebElement selDeiselCheckBox = (WebElement) js.executeScript(commonMethords.fetchFromObjectRepo("JS_obj_selDeiselCheckBox"));
		selDeiselCheckBox.click();
		Log.info("Selected Deisel");
		fuelTypeWebEle.click();
		
		List<WebElement> selecteModelPricesList = shadowRoot1CofigPage.findElements(obj_selecteModelPricesList);
		Log.info("Master list of all A car Models Deisel  - "+selecteModelPricesList.size());
		
		int listofCarPricesArray [] = new int [selecteModelPricesList.size()];
		for(int j=0; j<selecteModelPricesList.size();j++) {
			listofCarPricesArray[j]= (Integer.parseInt(removePoundComa((selecteModelPricesList).get(j).getText())));
		}

		return listofCarPricesArray ;
	}
	
	/**
	 * {@summary Write min and max value to text file }
	 * @param integerArray
	 */
	public void writeMinMaxValueToTxtFile (int[] integerArray) {
		
		 int minCarPrice = commonMethords.minValueInArray(integerArray);
		 int maxCarPrice = commonMethords.maxValueInArray(integerArray);
		 String strTemp = " Min Car Price : " + Integer.toString(maxCarPrice) + "\n Max Car Price : "+ Integer.toString(minCarPrice);
		 commonMethords.createAndWriteTXTFile(strTemp);	
	}
	
	/**
	 * 
	 * @param integerArray
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean validate_Price_in_range(int [] integerArray) throws NumberFormatException, IOException, InterruptedException {
		
		boolean flag = true ; 
		 int minCarPrice = commonMethords.minValueInArray(integerArray);
		 int maxCarPrice = commonMethords.maxValueInArray(integerArray)+ deltaPriceForPaint();
		 if (minCarPrice < Integer.parseInt(commonMethords.fetchPropertyValueFromTestData("minCarValue")) || (maxCarPrice> Integer.parseInt(commonMethords.fetchPropertyValueFromTestData("maxCarValue")))) {
			 	flag = false ; 
		 		 }
		 
		 return flag;
	}		
	
	/**
	 * This to find max extra amount for paint work 
	 * @return
	 * @throws InterruptedException 
	 * @throws Exception 
	 */
	public int deltaPriceForPaint () throws InterruptedException  {
		
		Log.info("deltaPriceForPaint");
		WebElement shadowHostCofigPage = driver.findElement(obj_shadowHostCofigPage);
		SearchContext shadowRoot1CofigPage = getShadowRootElement(shadowHostCofigPage);
		WebElement sterior = shadowRoot1CofigPage.findElement(obj_sterior);
		
		sterior.click();	
		delay(3000);
		
		List <WebElement> scolours = shadowRoot1CofigPage.findElements(obj_scolours);	
		//System.out.println(" sColours size " +scolours.size());
		//WebElement minPriceColorObject = extriorItemMinMax(scolours,"min");
		//minPriceColorObject.click();
		//Log.info("Clicked On Minprice Color");
		delay(1000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,-250)", "");
		delay(1000);
		WebElement maxPriceColorObject = extriorItemMinMax(scolours,"max");
		//maxPriceColorObject.click();
		Log.info("Clicked On Maxprice Color"+ maxPriceColorObject.getText().toString());
		delay(4000);
		
		 try {
			commonMethords.takeSnapShot(driver,System.getProperty("user.dir") +"/scrPrint/SampleScreenPrinttDeltaPriceForPaint"+commonMethords.createFileName()+".png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(removePoundComa(maxPriceColorObject.getText().toString())); 
		
	}
	
	 
	public void tearDown() {
		driver.quit();
	}
	
}