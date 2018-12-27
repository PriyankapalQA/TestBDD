package testSelenium;

import java.io.File;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cucumber.listener.Reporter;
import testUtils.ConfigUtil;
import testUtils.DriverUtil;

import testUtils.RunnerUtil;

public class MainRunner {

	public static void main (String args[])
	{
	 	ConfigUtil.readConfigExcelFile();  // to read the environment details
		ConfigUtil.readConfigurations(); 
		ConfigUtil.readConfigValues();
		DriverUtil.initializeDriver();  //to trigger the selenium driver
 	    if(args.length==0)
		   {
			RunnerUtil.prepareRunnerFile(ConfigUtil.readTagName());
			}  
 	    else
	 	    {
 	    	RunnerUtil.prepareRunnerFile(args);
	 	    }  
 	    
 	   JUnitCore.runClasses(testSelenium.TestRunner.class);  //to run the Test runner class

  //   Reporter.loadXMLConfig(new File(".\\src\\extent-config.xml"));
	   Reporter.loadXMLConfig(new File("src/extent-config.xml"));
	   Reporter.setSystemInfo("user",System.getProperty("user.name"));
	   Reporter.setSystemInfo("os", "Mac OSX");
	   Reporter.setTestRunnerOutput("Sample Test runner output message"); 
	  
	   
	   if (StaticData.driver!=null)
	   	   DriverUtil.closeBrowser(); 
	} 
	
	
	
}
	