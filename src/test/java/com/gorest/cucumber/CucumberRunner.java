package com.gorest.cucumber;

import com.gorest.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
//@CucumberOptions(features = "src/test/java/resources/feature/")
@CucumberOptions(features = "src/test/java/resources/feature/userCRUD.feature")
public class CucumberRunner extends TestBase {

}
