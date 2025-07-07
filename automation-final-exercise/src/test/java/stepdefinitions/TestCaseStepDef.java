package stepdefinitions;

import org.testng.Assert;
import io.cucumber.java.en.And;
import pageobjects.Directory;

import static utilities.CommonFunctions.*;

public class TestCaseStepDef {
	@And("I verify that test cases heading is displayed")
	public void I_verify_that_test_cases_heading_is_displayed(){
		waitForElement(Directory.testCasesPage.getTestCasesHeading());
		Assert.assertTrue(Directory.testCasesPage.getTestCasesHeading().isDisplayed());
	}
}
