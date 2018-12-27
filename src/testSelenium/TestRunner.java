package testSelenium;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features = {"features"},
glue ={"testSelenium"},
tags={"@regression"}
,plugin={"com.cucumber.listener.ExtentCucumberFormatter:output/DEMO/12-26-2018 18-34-29/report.html"}
 )

public class TestRunner {}

