package runners;

//import org.junit.runner.RunWith;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(
			plugin = {"pretty", "html:target/Cucumber.html",
					"json:target/cucumber.json",
					"junit:target/cucumber-reports/Cucumber.xml",
					//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
					//"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
					}, //reporting purpose
			monochrome=false,  //console output color
			features = {
					"src/test/resources/features/01DieticianUserLogin.feature",
					"src/test/resources/features/05DieticianLogout.feature"
					
					}, //location of feature files		

			glue= "api.stepdefinitions" //location of step definition files
		)
public class TestRunner extends AbstractTestNGCucumberTests{
	
	//@Override
    //@DataProvider(parallel = false)
    //public Object[][] scenarios() {
				
		//return super.scenarios();

}
