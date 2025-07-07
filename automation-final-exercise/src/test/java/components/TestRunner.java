package components;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features="src/test/java/features", 
	glue="stepdefinitions", 
	monochrome=true, 
	tags ="@TC22",
	//plugin = {"pretty", "html:target/cucumber.html"}
	plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
	)

public class TestRunner extends AbstractTestNGCucumberTests{}
