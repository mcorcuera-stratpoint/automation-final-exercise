package stepdefinitions;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import components.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks{

    @After
    public static void tearDown(Scenario scenario) throws IOException {
        //validate if scenario has failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            BaseTest.closeBrowser();
        }   
       
    }
	
}
