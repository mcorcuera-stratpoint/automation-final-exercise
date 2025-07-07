package components;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features="src/test/java/features", 
	glue="stepdefinitions", 
	monochrome=true, 
	tags ="@Regression",
	plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
	)

public class TestRunner extends AbstractTestNGCucumberTests{}
