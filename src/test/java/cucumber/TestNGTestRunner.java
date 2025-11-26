package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {
				  "src/test/java/cucumber/ErrorValidations.feature",
				  "src/test/java/cucumber/SubmitOrder.feature"
				},
    glue = "V.TemplateSelFramewrk.stepDefinitions",
   tags = "@Regression or @errorValidation",

    plugin = {"html:target/cucumber.html"},
    monochrome = true
    
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}

