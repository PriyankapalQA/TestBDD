package testSelenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.cucumber.listener.Reporter;

import testUtils.*;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;


public class StepDefination {

	String scenarioName;
	String featureName;
	String testData;

	@Before
	public void before(Scenario scenario) 
	{
		scenarioName= scenario.getName();
		String scenarioId =scenario.getId();
		String[] parts = scenarioId.split(";");
		featureName = parts[0];
	}


	@Given("^I open \"([^\"]*)\" application$")
	public void i_open_application(String arg1)  
	{
		try
		{
			LibraryFunctions.OpenUrl(StaticData.url);
		}
		catch (Exception e)
		{
			LibraryFunctions.captureScreenshot();
			logout();
			System.out.println(e.getMessage());
			Reporter.addStepLog("Unable to open the application: "+arg1);
			Assert.fail();
		}
	}


	@Given("^I wait till the page loads$")
	public void i_wait_till_the_page_loads_seconds()
	{
		try
		{
			// Thread.sleep(2000);
			WaitUtil.waitUntilJQueryReady();
			Thread.sleep(3000);
			LibraryFunctions.captureScreenshot();
		} 
		catch (Exception e) 
		{
			LibraryFunctions.captureScreenshot();
			logout();
			System.out.println(e.getMessage());
			Reporter.addStepLog("Unable to wait");
			Assert.fail();

		}
	}

	@Given("^I wait \"([^\"]*)\" seconds$")
	public void i_wait_seconds(String arg1)
	{
		try
		{
			int seconds = Integer.parseInt(arg1);
			seconds=seconds*1000;
			Thread.sleep(seconds);
			LibraryFunctions.captureScreenshot();
		} 
		catch (Exception e) 
		{
			LibraryFunctions.captureScreenshot();
			logout();
			System.out.println(e.getMessage());
			Reporter.addStepLog("Unable to wait");
			Assert.fail();

		}
	}

	@When("^I enter \"([^\"]*)\"$")
	public void i_enter(String arg1) 
	{
		try
		{   
			testData =TestDataUtil.readTestData(featureName, scenarioName,arg1);
			ConfigUtil.readIdentifierValues(arg1);

			//     LibraryFunctions.waitTillElementClickable(StaticData.identifierType, StaticData.identifierValue);
			//    Thread.sleep(StaticData.threadWait);
			LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).click();
			// LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).sendKeys("");
			LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).clear();
			LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).sendKeys(testData);

			LibraryFunctions.captureScreenshot();
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			LibraryFunctions.captureScreenshot();
			logout();
			System.out.println(e.getMessage());
			Reporter.addStepLog("Unable to enter data in " + arg1);
			Assert.fail();
		}
	}


	@When("^I select \"([^\"]*)\" from dropdown$")
	public void i_select_from_dropdown(String arg1)  
	{

		try
		{
			testData =TestDataUtil.readTestData(featureName, scenarioName,arg1);
			ConfigUtil.readIdentifierValues(arg1);

			// Select drp = new Select(StaticData.driver.findElement(By.xpath("//*[@id='profile']/form/div[3]/div/div/select")));
			// drp.selectByIndex(1);
			// LibraryFunctions.selectDropdown(StaticData.identifierType, StaticData.identifierValue).selectByIndex(1);

			LibraryFunctions.selectDropdown(StaticData.identifierType, StaticData.identifierValue).selectByVisibleText(testData);

			LibraryFunctions.captureScreenshot();
			Thread.sleep(1000);
		}

		catch (Exception e)
		{

			LibraryFunctions.captureScreenshot();
			logout();
			System.out.println(e.getMessage());
			// System.out.println(e.toString());
			//e.printStackTrace();
			Reporter.addStepLog("Unable to select " + arg1);
			Assert.fail();

		}


	}

	@When("^I click on \"([^\"]*)\"$")
	public void i_click_on(String arg1)  
	{
		try
		{
			ConfigUtil.readIdentifierValues(arg1);
			/*		 if (arg1.equalsIgnoreCase("SearchResult"))
			 {
				 testData = TestDataUtil.readTestData(featureName, scenarioName,"SearchText");					
				 StaticData.identifierValue=StaticData.identifierValue+"'"+testData+"'"+")]";  

			 }  */
			//    LibraryFunctions.waitTillElementVisible(StaticData.identifierType, StaticData.identifierValue);
			//     LibraryFunctions.waitTillElementClickable(StaticData.identifierType, StaticData.identifierValue);	
			LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).click();

			LibraryFunctions.captureScreenshot();
			Thread.sleep(2000);

		}
		catch (Exception e)
		{
			LibraryFunctions.captureScreenshot();
			logout();
			System.out.println(e.getMessage());
			Reporter.addStepLog("Unable to click on " + arg1);
			Assert.fail();

		}
	}


	

	@Then("^\"([^\"]*)\" should be displayed as \"([^\"]*)\"$")
	public void should_be_displayed_as(String arg1,String arg2) 
	{
		String displayText = null;
		try
		{
			ConfigUtil.readIdentifierValues(arg1);
			if (arg1.equalsIgnoreCase("SearchResultHeader"))
			{
				testData = TestDataUtil.readTestData(featureName, scenarioName,"SearchText");					
				StaticData.identifierValue=StaticData.identifierValue+"'"+testData+"'"+")]";

				testData =TestDataUtil.readTestData(featureName, scenarioName,arg2);
				displayText =  LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).getText();

				Assert.assertEquals(arg1+" is displayed as: "+displayText,testData,displayText);
				LibraryFunctions.captureScreenshot(); 
			}
			else if(arg1.equalsIgnoreCase("PopUpMsg"))	 
			{
				StaticData.driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
				testData =TestDataUtil.readTestData(featureName, scenarioName,arg2);
				displayText = LibraryFunctions.waitForElement(StaticData.identifierType, StaticData.identifierValue).getText();
				Assert.assertEquals(arg1+" is displayed as: "+displayText,testData,displayText);
				LibraryFunctions.captureScreenshot();

			} 

			else
			{
				testData =TestDataUtil.readTestData(featureName, scenarioName,arg2);
				displayText =  LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).getText();

				Assert.assertEquals(arg1+" is displayed as: "+displayText,testData,displayText);
				LibraryFunctions.captureScreenshot(); 
			}	 

		}
		catch (AssertionError ae)
		{ 
			LibraryFunctions.captureScreenshot();
			Reporter.addStepLog("Validation Failed: "+testData+ " is not displayed, Actual text : "+displayText);
			Assert.fail();
		}
		catch (Exception e)
		{
			LibraryFunctions.captureScreenshot();
			Reporter.addStepLog("Unable to validate if message: "+testData+ " is displayed");
			logout();
			System.out.println(e.getMessage());
			
			Assert.fail();
		}
	}

	/*

	 @When("^I enter \"([^\"]*)\" if \"([^\"]*)\" not present$")
	 public void i_enter_if_not_already_present(String arg1, String arg2) 
	 {
		 try
		 {   
			 ConfigUtil.readIdentifierValues(arg2);
			 String displayText =  LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).getText();
			 if (displayText.equals(""))
			 {  
				 testData =TestDataUtil.readTestData(featureName, scenarioName,arg1);
			     ConfigUtil.readIdentifierValues(arg1);
			//     LibraryFunctions.waitTillElementClickable(StaticData.identifierType, StaticData.identifierValue);
			 //    Thread.sleep(StaticData.threadWait);
			     LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).click();
			     LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).sendKeys(testData);
			     LibraryFunctions.captureScreenshot();
				 Thread.sleep(1000);
			 }
			 else
				 Reporter.addStepLog(arg1+" is already present");
		 }
		 catch (Exception e)
		 {
			 LibraryFunctions.captureScreenshot();
			 logout();
			 System.out.println(e.getMessage());
			 Reporter.addStepLog("Unable to enter data in " + arg1);
			 Assert.fail();
		 }
   	 }



	 @When("^I enter unique \"([^\"]*)\"$")
	 public void i_enter_unique(String arg1) 
	 {
		 try
		 {   
		     testData=TestDataUtil.readTestData(featureName, scenarioName,arg1);
		    	 StaticData.snapshotName=testData+RandomStringUtils.randomAlphanumeric(8);
		    	 testData=StaticData.snapshotName;

		     ConfigUtil.readIdentifierValues(arg1);
		//     LibraryFunctions.waitTillElementClickable(StaticData.identifierType, StaticData.identifierValue);
		//    Thread.sleep(StaticData.threadWait);
		     LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).click();
		     LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).sendKeys(testData);
		     LibraryFunctions.captureScreenshot();
			 Thread.sleep(1000);
		 }
		 catch (Exception e)
		 {
			 LibraryFunctions.captureScreenshot();
			 logout();
			 System.out.println(e.getMessage());
			 Reporter.addStepLog("Unable to enter data in " + arg1);
			 Assert.fail();
		 }
   	 }

	 */ 



	/*	 @Given("^I click on checkbox \"([^\"]*)\"$")
	 public void i_click_on_checkbox(String arg1)
	 {
		 try
		 {
			 ConfigUtil.readIdentifierValues(arg1);

		//	 LibraryFunctions.waitTillElementVisible(StaticData.identifierType, StaticData.identifierValue);
		 //    LibraryFunctions.waitTillElementClickable(StaticData.identifierType, StaticData.identifierValue);	
			 String b = LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).getAttribute("class");
			 Boolean a = b.equalsIgnoreCase("large-icon-active icon-check");
			if(!a)
				LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).click();
			else
				Reporter.addStepLog("The checkbox "+arg1+" is already checked");

			 LibraryFunctions.captureScreenshot();
		 }
		 catch (Exception e)
		 {
			 LibraryFunctions.captureScreenshot();
			 logout();
			 System.out.println(e.getMessage());
			 Reporter.addStepLog("Unable to click on " + arg1);
			 Assert.fail();

		 }
	 }  */

	/*	 @Given("^I click on checkbox for \"([^\"]*)\"$")
	 public void i_click_on_checkbox_for(String arg1)
	 {
		 try{
		testData=TestDataUtil.readTestData(featureName, scenarioName,arg1);
		String[] parts = testData.split(",");

		 for (int i=0;i<parts.length;i++)
		 {
			 ConfigUtil.readIdentifierValues(parts[i]);
			 String b = LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).getAttribute("class");
			 Boolean a = b.equalsIgnoreCase("large-icon-active icon-check");
			if(!a)
				LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).click();
			else
				Reporter.addStepLog("The checkbox "+parts[i]+" is already checked");

			 LibraryFunctions.captureScreenshot();
			 Thread.sleep(1000);
		 }

		 }
		 catch (Exception e)
		 {
			 LibraryFunctions.captureScreenshot();
			 logout();
			 System.out.println(e.getMessage());
			 Reporter.addStepLog("Unable to click to provide access");
			 Assert.fail();

		 }

	 }
	 */	 
	@Then("^I am re-directed to \"([^\"]*)\"$")
	public void re_directed_to(String arg1)
	{
		try
		{			
			// ConfigUtil.readIdentifierValues(arg1);
		//	StaticData.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

			String expectedPageTitle =TestDataUtil.readTestData(featureName, scenarioName,arg1);
			// Thread.sleep(3000);
			Assert.assertEquals("Could not re-direct to "+arg1,LibraryFunctions.pageLoaded(expectedPageTitle), true);
			LibraryFunctions.captureScreenshot();
		}
		catch (AssertionError ae)
		{ 
			LibraryFunctions.captureScreenshot();
			Reporter.addStepLog("Could not re-direct to "+arg1);
			logout();
			Assert.fail();
		}
		catch (Exception e)
		{
			LibraryFunctions.captureScreenshot();
			logout();
			Reporter.addStepLog("Could not re-direct to "+arg1);
			Assert.fail();
		}
	}

	/*	 @Then("^I am re-directed to \"([^\"]*)\" after loading$")
	 public void re_directed_to_after_loading(String arg1)
	 {
		try
		{			
			 ConfigUtil.readIdentifierValues(arg1);
			 String expectedPageTitle =TestDataUtil.readTestData(featureName, scenarioName,arg1);
			 //LibraryFunctions.hibernate();
			 Thread.sleep(3000);
			 Assert.assertEquals("Could not re-direct to "+arg1,LibraryFunctions.pageLoaded(expectedPageTitle), true);
			 Thread.sleep(3000);
		//	 LibraryFunctions.waitTillElementClickable(StaticData.identifierType, StaticData.identifierValue);		
			 LibraryFunctions.captureScreenshot(); 
		}
		catch (AssertionError ae)
		{ 
			 LibraryFunctions.captureScreenshot();
			 Reporter.addStepLog("Could not re-direct to "+arg1);
			 logout();
			 Assert.fail();
		}
		 catch (Exception e)
		 {
			LibraryFunctions.captureScreenshot();
			logout();
			Reporter.addStepLog("Could not re-direct to "+arg1);
			Assert.fail();
		 }
	 }   */


	/*	 @When("^\"([^\"]*)\" is visible in the bottom tray$")
	 public void is_visible_in_the_bottom_tray(String arg1)
	 {
		 try
		 {
		 ConfigUtil.readIdentifierValues(arg1);
		 List <WebElement> c = LibraryFunctions.FocusOnElements(StaticData.identifierType, StaticData.identifierValue);
		 for(WebElement cc:c)
		 {
			 String text = cc.getText();
			 if(text.equalsIgnoreCase(arg1))
				 Reporter.addStepLog(arg1+" is visible in the bottom tray");

		 }
		 LibraryFunctions.captureScreenshot();
		 }

		 catch (Exception e)
		 {
			LibraryFunctions.captureScreenshot();
			logout();
			Reporter.addStepLog("Could not validate if "+arg1+" is visible in the bottom tray");
			Assert.fail();
		 }

	 }



	 @Given("^\"([^\"]*)\" is loaded$")
	 public void is_loaded(String arg1) 
	 {
		try
		{
			 ConfigUtil.readIdentifierValues(arg1);
			 LibraryFunctions.waitTillElementClickable(StaticData.identifierType, StaticData.identifierValue);
			 LibraryFunctions.captureScreenshot();
		}
		 catch (Exception e)
		 {
			 LibraryFunctions.captureScreenshot();
			 logout();
			 System.out.println(e.getMessage());
			 Reporter.addStepLog("Unable to load " + arg1);
			 Assert.fail();
		 }
     }


		@Then("^I click on the recently created snapshot$")
		public void i_click_on_the_recently_created_snapshot()
		{
			try
			{   
				String revieweeCountry=TestDataUtil.readTestData(featureName, scenarioName,"RevieweeCountry");
				if(TestDataUtil.readTestData(featureName, scenarioName,"ReviewerCountry").equals(revieweeCountry))
				{
					ConfigUtil.readIdentifierValues("SnapshotToReturn");
					List<WebElement> listOptions1 = LibraryFunctions.FocusOnElements(StaticData.identifierType, StaticData.identifierValue);
			 		listOptions1.get(0).click();
			// /*	
					for (int i=0; i<listOptions1.size();i++ )
				    {
				    	if(listOptions1.get(i).getText().equals(StaticData.snapshotName))
					//	if(listOptions1.get(i).getText().equals("test"))
				    	{
				     		listOptions1.get(i).click();
				     		Thread.sleep(1000);
				    	}
	        // 	

				}

				else 
				{
					 ConfigUtil.readIdentifierValues("CountryDropDown");
				    if(LibraryFunctions.ElementIsPresent(StaticData.identifierType, StaticData.identifierValue))
				    {
				      ConfigUtil.readIdentifierValues("CountryInitialsInDropDown");
				      String xpathValue = StaticData.identifierValue+revieweeCountry+"')]";
				      LibraryFunctions.FocusOnElement(StaticData.identifierType,xpathValue).click();
				    }
				    else
				    {
				    	 Reporter.addStepLog("Country dropdown is not in open state even when reviewee is from a different country");
				    	 ConfigUtil.readIdentifierValues("CountryDropDownOpenLink");
				    	 LibraryFunctions.FocusOnElement(StaticData.identifierType,StaticData.identifierValue).click();
				    }
				}
			}
			 catch (Exception e)
			 {
				 LibraryFunctions.captureScreenshot();
				 logout();
				 System.out.println(e.getMessage());
				 Reporter.addStepLog("Unable to view recently created snapshot");
				 Assert.fail();

			 }

		}

		@When("^I navigate to bottom of the page with \"([^\"]*)\"$")
		public void I_navigate_to_bottom_of_the_page_with(String arg1) 
		{
			try
			{
				ConfigUtil.readIdentifierValues(arg1);
				LibraryFunctions.Scroll_DragScrollBarToEnd(LibraryFunctions.FocusOnElement(StaticData.identifierType,StaticData.identifierValue));

			}

			 catch (Exception e)
			 {
				 LibraryFunctions.captureScreenshot();
				 logout();
				 System.out.println(e.getMessage());
				 Reporter.addStepLog("Unable to scroll to the bottom of the page");
				 Assert.fail();

			 }
		}	

	 */

	@When("^I dismiss the \"([^\"]*)\"$")
	public void i_dismiss_the(String arg1) 
	{	
		try
		{
			Thread.sleep(2000);
			StaticData.driver.switchTo().alert().dismiss();
			LibraryFunctions.captureScreenshot();
		}

		catch (Exception e)
		{
			LibraryFunctions.captureScreenshot();
			logout();
			System.out.println(e.getMessage());
			Reporter.addStepLog("Unable to dismiss the "+arg1);
			Assert.fail();	
		}

	}



	@When("^I upload \"([^\"]*)\"$")
	public void i_upload(String arg1)  
	{

		try
		{
			ConfigUtil.readIdentifierValues(arg1);
			testData = TestDataUtil.readTestData(featureName, scenarioName,arg1);			
			WebElement TextBox = LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue);

			((JavascriptExecutor)StaticData.driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", TextBox);
			TextBox.sendKeys(testData);
			Thread.sleep(1000);

		}

		catch (Exception e)
		{
			LibraryFunctions.captureScreenshot();
			logout();
			System.out.println(e.getMessage());
			Reporter.addStepLog("Unable to upload " + arg1);
			Assert.fail();

		}


	}



	@Given("^I choose \"([^\"]*)\"$")
	public void i_choose(String arg1) 
	{
		try
		{
			ConfigUtil.readIdentifierValues(arg1);
			testData = TestDataUtil.readTestData(featureName, scenarioName,arg1);			
			WebElement TextBox = LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue);

			((JavascriptExecutor)StaticData.driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", TextBox);
			TextBox.sendKeys(testData);
			Thread.sleep(1000);
		}

		catch (Exception e)
		{
			LibraryFunctions.captureScreenshot();
			logout();
			System.out.println(e.getMessage());
			Reporter.addStepLog("Unable to choose " + arg1);
			Assert.fail();

		}
	}




	/*	 @Given("^I choose \"([^\"]*)\"$")
	 public void i_choose(String arg1)
	 {

		 try
		 {
			 ConfigUtil.readIdentifierValues(arg1);
			 testData = TestDataUtil.readTestData(featureName, scenarioName,arg1);			
			 WebElement TextBox = LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue);

			 ((JavascriptExecutor)StaticData.driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", TextBox);
			 TextBox.sendKeys(testData);
			 Thread.sleep(1000);

	 }

		 catch (Exception e)
		 {
			 LibraryFunctions.captureScreenshot();
			 logout();
			 System.out.println(e.getMessage());
			 Reporter.addStepLog("Unable to choose " + arg1);
			 Assert.fail();

		 }


	 }*/

	@When("^I scroll to view \"([^\"]*)\"$")
	public void I_scroll_to_view(String arg1) 
	{	
		try
		{
			ConfigUtil.readIdentifierValues(arg1);
			LibraryFunctions.Scroll_PageDown();
			LibraryFunctions.Scroll_ToElement(LibraryFunctions.FocusOnElement(StaticData.identifierType,StaticData.identifierValue));
		}

		catch (Exception e)
		{
			LibraryFunctions.captureScreenshot();
			logout();
			System.out.println(e.getMessage());
			Reporter.addStepLog("Unable to scroll to view "+arg1);
			Assert.fail();
		}

	}	

	/*	
	@When("^I drag SliderButton \"([^\"]*)\" with \"([^\"]*)\"$")
	public void I_drag_SliderButton(String arg1,String arg2) 
	{
		try
		{

			//WebElement From = StaticData.driver.findElement(By.id("slider-1"));	
			LibraryFunctions.Scroll_PageDown();
		//	je.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

		//	testData =TestDataUtil.readTestData(featureName, scenarioName,arg2);
		//    ConfigUtil.readIdentifierValues(arg2);





			for(int i=5;i>=1;i--)
			{
				String xpath = "//*[@id='right_content_"+i+"'"+"]/div/div/a/span";
				WebElement From = StaticData.driver.findElement(By.xpath(xpath));
				LibraryFunctions.Scroll_ToElement(From);
				Thread.sleep(2000);
				Actions act=new Actions(StaticData.driver);
				if (arg1.equalsIgnoreCase("> At Grade"))
				{
			//	act.clickAndHold(From).moveByOffset(350, 100).build().perform(); 
			//	act.clickAndHold(From).moveByOffset(450, 100).build().perform(); //dim 3
			//	act.clickAndHold(From).moveByOffset(450, 100).build().perform(); 
				act.clickAndHold(From).moveByOffset(650, 100).build().perform(); //dim 4
			//	act.clickAndHold(From).moveByOffset(750, 100).build().perform(); //dim 5
				act.release();				
				}
				else
					act.clickAndHold(From).moveByOffset(450, 10).build().perform();
				act.release();

				ConfigUtil.readIdentifierValues(arg2);
				StaticData.identifierValue=StaticData.identifierValue+i;

				testData =TestDataUtil.readTestData(featureName, scenarioName,arg2);
				testData= testData+" "+i;

					//     LibraryFunctions.waitTillElementClickable(StaticData.identifierType, StaticData.identifierValue);
					 //    Thread.sleep(StaticData.threadWait);
				LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).click();
				LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).sendKeys("");
				LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).sendKeys(testData);

				//WebElement To = StaticData.driver.findElement(By.id("slider-1"));
				//act.dragAndDrop(From, To).build().perform();
				//Actions.dragAndDropBy(Sourcelocator, x-axis pixel of Destinationlocator, y-axis pixel of Destinationlocator)
			}
		}

		 catch (Exception e)
		 {
			 LibraryFunctions.captureScreenshot();
			 logout();
			 System.out.println(e.getMessage());
			 Reporter.addStepLog("Unable to provide rating");
			 Assert.fail();

		 }
	}	


	@When("^I drag SliderButton \"([^\"]*)\"$")
	public void I_drag_SliderButton(String arg1) 
	{
		try
		{

			//WebElement From = StaticData.driver.findElement(By.id("slider-1"));	
			LibraryFunctions.Scroll_PageDown();
		//	je.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

			for(int i=5;i>=1;i--)
			{
				String xpath = "//*[@id='right_content_"+i+"'"+"]/div/div/a/span";
				WebElement From = StaticData.driver.findElement(By.xpath(xpath));
				LibraryFunctions.Scroll_ToElement(From);
				Thread.sleep(2000);
				Actions act=new Actions(StaticData.driver);
				if (arg1.equalsIgnoreCase("> At Grade"))
					act.clickAndHold(From).moveByOffset(450, 10).build().perform();
				else
					act.clickAndHold(From).moveByOffset(250, 10).build().perform();
				act.release();
					//     LibraryFunctions.waitTillElementClickable(StaticData.identifierType, StaticData.identifierValue);
					 //    Thread.sleep(StaticData.threadWait);
				//WebElement To = StaticData.driver.findElement(By.id("slider-1"));
				//act.dragAndDrop(From, To).build().perform();
				//Actions.dragAndDropBy(Sourcelocator, x-axis pixel of Destinationlocator, y-axis pixel of Destinationlocator)
			}
		}

		 catch (Exception e)
		 {
			 LibraryFunctions.captureScreenshot();
			 logout();
			 System.out.println(e.getMessage());
			 Reporter.addStepLog("Unable to provide rating");
			 Assert.fail();

		 }
	}	


	@When("^I select switch \"([^\"]*)\"$")
	public void i_select_switch(String arg1)
	{
		try
		{
		 testData = TestDataUtil.readTestData(featureName, scenarioName,arg1);
	     ConfigUtil.readIdentifierValues(arg1);
	     String a = LibraryFunctions.FocusOnElement(StaticData.identifierType,StaticData.identifierValue).getAttribute("aria-valuetext");
	     if(!a.equalsIgnoreCase(testData))
	    	 LibraryFunctions.FocusOnElement(StaticData.identifierType,StaticData.identifierValue).click();

		}
	 	catch (Exception e)
		{
		 LibraryFunctions.captureScreenshot();
		 logout();
		 System.out.println(e.getMessage());
		 Reporter.addStepLog("Unable to change the switch");
		 Assert.fail();	
		}
	}
	 */	


	@When("^I select \"([^\"]*)\"$")
	public void i_select(String arg1) 
	{
		try
		{
			testData = TestDataUtil.readTestData(featureName, scenarioName,arg1);
			ConfigUtil.readIdentifierValues(arg1);
			// Thread.sleep(3000);
			List<WebElement> listOptions1 = LibraryFunctions.FocusOnElements(StaticData.identifierType,StaticData.identifierValue);

			for(WebElement a : listOptions1)
			{
				String a1=a.getText();
				if (a.getText().equalsIgnoreCase(testData))
				{
					a.click();
					break;
				}

			} 

			//   LibraryFunctions.FocusOnElement("xpath", "//a[contains(@id,'ui-id-') and contains(@class,'ui-corner-all')]").click();
			Thread.sleep(1000);
			LibraryFunctions.captureScreenshot();
			
		}
		catch (Exception e)
		{
			LibraryFunctions.captureScreenshot();
			Reporter.addStepLog("Unable to select from dropdown :"+arg1);
			logout();
			System.out.println(e.getMessage());
		
			Assert.fail();	
		}

	}




	@Given("^I Logout from the application$")
	public void logout()   
	{
		try
		{  

			//	 LibraryFunctions.captureScreenshot();
			//	 ConfigUtil.readIdentifierValues("Logout");
			//	 LibraryFunctions.FocusOnElement(StaticData.identifierType, StaticData.identifierValue).click();
			Thread.sleep(3000);
			//	 Thread.sleep(StaticData.maxWaitForElement);
			//	 String expectedPageTitle =TestDataUtil.readTestData(featureName, scenarioName,"Logout page");
			//	 LibraryFunctions.pageLoaded(expectedPageTitle); */
			DriverUtil.closeBrowser();
		}

		catch (Exception e)
		{
			DriverUtil.closeBrowser();
			System.out.println(e.getMessage());
			//	 Reporter.addStepLog("Unable to logout successfully from the application");
			// Assert.fail("Unable to logout successfully from the application");
		}
	}	 
}
