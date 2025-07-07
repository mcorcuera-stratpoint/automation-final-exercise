package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.RuleBasedNumberFormat;

import components.CacheHelper;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.ParseException;
import pageobjects.*;

public class CommonFunctions {
	static WebDriver driver;
	static Wait<WebDriver> wait;
	static Actions action;
		
	public CommonFunctions(WebDriver driver) {
		PageFactory.initElements(driver, this);
		CommonFunctions.driver = driver;
		CommonFunctions.action = new Actions(driver);
		CommonFunctions.wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(5))
													   .pollingEvery(Duration.ofMillis(500))
													   .ignoring(NoSuchElementException.class);		
	}

	public static void waitForElement(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			
		} catch (Exception e) {
			
		}		
	}
	
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	public static void scrollToBottomOfPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
	}
	
	public static void scrollToTopOfPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,0)", "");
	}
	
	public static String takeScreenshot(String testCase, WebDriver driver) throws IOException {
		LocalDateTime currentDateTime = LocalDateTime.now();
    	String path = System.getProperty("user.dir")+ "//extent-reports//screenshots//" + testCase + "_" + currentDateTime.toString() + ".png";
    	File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	File file = new File(path);
    	FileUtils.copyFile(source, file);
    	
    	return path;
	}
	
	public static int ordinalStringToInteger(String ordinal) throws java.text.ParseException {
		RuleBasedNumberFormat formatter = new RuleBasedNumberFormat( Locale.US , RuleBasedNumberFormat.SPELLOUT );
		int result = 0;
		
		try{
		    result = formatter.parse(ordinal.toLowerCase( Locale.ROOT ) ).intValue();
		}
		catch ( ParseException e ){
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static void handleAlerts(String action) throws InterruptedException {
		
		try {
			Thread.sleep(Duration.ofSeconds(1));
			Alert alert = driver.switchTo().alert();
		   
			if(action.equals("accept")) {
				alert.accept();
			}
			else if (action.equals("dismiss")) {
				alert.dismiss();
			}
		
		} catch (NoAlertPresentException ex) {
			  
			//Alert not present
			ex.printStackTrace();
		}

	}

	public static void setCache(String key,String value){
        CacheHelper.getInstance().setContext(key,value);
	    //System.out.println("Stored " + value + " to cache.");
    }
	
	public static String getCache(String key){
	    Object value=CacheHelper.getInstance().getContext(key);
	    if(value!=null){
	        //System.out.println("Retrieved " + value + " from cache.");
	    }
	    return value.toString();
	}
	
	public static void hoverToElement(WebElement element) throws InterruptedException {
		action.moveToElement(element).build().perform();
		Thread.sleep(Duration.ofSeconds(1));
	}
	
	public static Boolean waitForDownload(String fileName, int waitTimeInSeconds) {
		boolean  isDownloadComplete  =  false ; 
		int  elapsedTime  =  0 ; //Time counter 
		
		while (!isDownloadComplete && elapsedTime < waitTimeInSeconds) { 
		    File  downloadedFile  =  new  File (Constants.DEFAULT_DOWNLOAD_PATH + "//" + fileName); 
		    if (downloadedFile.exists()) { 
		        isDownloadComplete = true ; 
		    } else { 
		        try { 
		            TimeUnit.SECONDS.sleep( 1 ); 
		            elapsedTime++; 
		        } catch (InterruptedException e) { 
		            e.printStackTrace(); 
		        } 
		    } 
		}
		return isDownloadComplete;
	}
	
	public static void deleteFile(String path, String fileName) {
        try {
            Path filePath = Paths.get(path + "//" + fileName);
            Files.delete(filePath);
            System.out.println("File " + fileName + " deleted");
            
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
	}
	
	
	public static String readFileToString(String path, String fileName) throws IOException {
        Path filePath = Paths.get(path + "//" + fileName);
        String textContent ="";
        
        try {
            textContent = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return textContent;
	}
	
	public static List<HashMap<String, String>> getJsonToMap(String filename) throws IOException {
		String json = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//java//data//" + filename), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(json, new TypeReference<List<HashMap<String, String>>>(){});
	
		return data;
	}
}
