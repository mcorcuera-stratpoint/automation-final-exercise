package components;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.json.JSONObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.core.Logger;
import pageobjects.DirectoryPage;
import utilities.CommonFunctions;
import utilities.Constants;

public class BaseTest{
	
	public static CacheHelper cache;
	public static JSONObject cartJson;
	public static DirectoryPage directory;
	public static CommonFunctions common;
	public static WebDriver driver;
	public static Logger log;
	
	public static WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fstream = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
		prop.load(fstream);
		
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browser.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
	    	WebDriverManager.chromedriver().setup();
	    	
	    	if(browser.contains("headless")) {
	    		options.addArguments("headless");
	    	}

	    	//Disable save password and address prompt
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("autofill.profile_enabled", false);
			
			//Set default file download path
			prefs.put("download.default_directory", Constants.DEFAULT_DOWNLOAD_PATH);
			prefs.put("savefile.default_directory", Constants.DEFAULT_DOWNLOAD_PATH);
			
			options.addArguments("--disable-save-password-bubble");
			options.setExperimentalOption("prefs", prefs);
	
	    	driver = new ChromeDriver(options); 
	    	driver.manage().window().setSize(new Dimension(1440, 990));
		}
		
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    	return driver;
	}
	
	public static void launchBrowser() throws IOException {
		driver = initializeDriver();
		driver.get(Constants.BASE_URL);

		//Initialize objects
		common = new CommonFunctions();
		cache = new CacheHelper();
		cartJson = new JSONObject();
	}
	
	public static void closeBrowser() {
		driver.quit();
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
}
