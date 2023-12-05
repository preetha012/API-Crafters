package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
			plugin = {"pretty", "html:target/DieticianAPI_Cucumber.html",
					"json:target/cucumber.json",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
					"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
					}, //reporting purpose
			monochrome=false,  //console output color
<<<<<<< HEAD
			features = {
					"src/test/resources/features/01DieticianUserLogin.feature",
					"src/test/resources/features/05DieticianLogout.feature"
=======
			features = {"src/test/resources/features"
>>>>>>> ce525924e548847390bbcf66bb781413bae2ade2
					
					}, //location of feature files		

			glue= "api.stepdefinitions" //location of step definition files
		)
public class TestRunner {
	

}
