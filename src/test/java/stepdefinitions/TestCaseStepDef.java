package stepdefinitions;

import org.testng.Assert;
import io.cucumber.java.en.And;
import pageactions.TestCaseActions;
import pageobjects.DirectoryPage;

import static utilities.CommonFunctions.*;

public class TestCaseStepDef {
	TestCaseActions testCaseActions = new TestCaseActions();

	@And("I verify that test cases heading is displayed")
	public void I_verify_that_test_cases_heading_is_displayed(){
		waitForElement(testCaseActions.testCasesPage.testCasesHeading);
		Assert.assertTrue(testCaseActions.testCasesPage.testCasesHeading.isDisplayed());
	}
}
