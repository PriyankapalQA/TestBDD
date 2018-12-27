package testSelenium;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.experimental.theories.internal.SpecificDataPointsSupplier;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import testUtils.DriverUtil;
import org.openqa.selenium.support.ui.Select;

public class LibraryFunctions {

	public static WebDriverWait wait = new WebDriverWait(StaticData.driver,StaticData.maxWaitForElement);	
	public static JavascriptExecutor je = (JavascriptExecutor) StaticData.driver;
	
	 public static void OpenUrl(String url)
	 {
		 try
		 {
			 if (StaticData.driver==null)
			  
				 DriverUtil.initializeDriver();
		 
			 StaticData.driver.get(url);
			 StaticData.driver.manage().window().maximize();  
			 Thread.sleep(StaticData.threadWait);
			 
		
				 
			// DriverUtil.driver.manage().timeouts().implicitlyWait(,TimeUnit.SECONDS) ;
		 }
	/*	 catch(WebDriverException e)
		 {
				
			//StaticData.driver=null;
		//	DriverUtil.initializeDriver();
		//	StaticData.driver.get(url);
		//	StaticData.driver.manage().window().maximize();  
			 
		 } */
		 
		 catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
   }
	
	 


public static boolean isAlertPresent() 
{ 
    try 
    { 
    	StaticData.driver.switchTo().alert(); 
        return true; 
    }   
    catch (NoAlertPresentException Ex) 
    { 
        return false; 
    }  
}
	 
	 
	 
	public static WebElement FocusOnElement(String type, String value) 
	{
	
		WebElement webElement = null;
		switch(type)
		{
			case "id": webElement = StaticData.driver.findElement(By.id(value));
			break;
			case "name": webElement = StaticData.driver.findElement(By.name(value));
			break;
			case "xpath" : webElement = StaticData.driver.findElement(By.xpath(value));
			break;
			case "className" : webElement = StaticData.driver.findElement(By.className(value));
			break;
			case "partialLinkText": webElement = StaticData.driver.findElement(By.partialLinkText(value));
			break;
			case "linkText": webElement = StaticData.driver.findElement(By.linkText(value));
			break;
			
			default: System.out.print("Invalid indentifier type");
		}
		
		HighlightCurrentElement(webElement);
		return webElement;
	}
		
	
	
	public static Select selectDropdown(String type, String value) 
	{
		Select webElementDrp=null;
		switch(type)
		{
			case "id": webElementDrp = new Select(StaticData.driver.findElement(By.id(value)));
			break;
			case "name": webElementDrp = new Select(StaticData.driver.findElement(By.name(value)));
			break;
			case "xpath" : webElementDrp = new Select(StaticData.driver.findElement(By.xpath(value)));
			break;
			case "className" : webElementDrp = new Select(StaticData.driver.findElement(By.className(value)));
			break;
			case "partialLinkText": webElementDrp = new Select(StaticData.driver.findElement(By.partialLinkText(value)));
			break;
			case "linkText": webElementDrp = new Select(StaticData.driver.findElement(By.linkText(value)));
			break;
			
			default: System.out.print("Invalid indentifier type");
		}
		
	//	HighlightCurrentElement1(webElementDrp);
		return webElementDrp;
	}
	
	
	public static List <WebElement> FocusOnElements(String type, String value) 
	{
		List<WebElement> webElement = null;
		switch(type)
		{
			case "id": webElement = StaticData.driver.findElements(By.id(value));
			break;
			case "name": webElement = StaticData.driver.findElements(By.name(value));
			break;
			case "xpath" : webElement = StaticData.driver.findElements(By.xpath(value));
			break;
			case "className" : webElement = StaticData.driver.findElements(By.className(value));
			break;
			case "partialLinkText": webElement = StaticData.driver.findElements(By.partialLinkText(value));
			break;
			case "linkText": webElement = StaticData.driver.findElements(By.linkText(value));
			break;
			
			default: System.out.print("Invalid indentifier type");
		}
		
		//HighlightCurrentElement(webElement);
		return webElement;
	}
	
	public static void waitTillElementClickable(String type, String value)
	{
		switch(type)
		{
		    case "id": wait.until(ExpectedConditions.elementToBeClickable(By.id(value))); 
		    break;
			case "name": wait.until(ExpectedConditions.elementToBeClickable(By.name(value)));
			break;
			case "xpath" : wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
			break;
			case "className" : wait.until(ExpectedConditions.elementToBeClickable(By.className(value)));
			break;
			case "partialLinkText": wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(value)));
			break;
			case "linkText": wait.until(ExpectedConditions.elementToBeClickable(By.linkText(value)));
			break;
			
			default: System.out.print("Invalid indentifier type");
	    }
		
	}
	
	public static void waitTillElementVisible(String type, String value)
	{
		switch(type)
		{
		    case "id": wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));
		    break;
			case "name": wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(value)));
			break;
			case "xpath" : wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
			break;
			case "className" : wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(value)));
			break;
			case "partialLinkText": wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(value)));
			break;
			case "linkText": wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(value)));
			break;
			
			default: System.out.print("Invalid indentifier type");
	    }
		
	}
	
	
	public static WebElement waitForElement(String arg1,String arg2) 
	{
		int maxTime=5;
		int waitTime=0;
		WebElement element = null;
		
		while(waitTime<=maxTime)
		{
			try
			{
				 element = LibraryFunctions.FocusOnElement(arg1, arg2);
				if(element!=null)
				{
					je.executeScript("return window.stop");
					return element;
				}
				else
				{
					Thread.sleep(1000);
					waitTime++;
				}
				
			}
			catch(Exception e)
			{
				try {
					Thread.sleep(1000);
					waitTime++;
				} 
				catch (Exception e1) {
					waitTime++;
				}
				
				
			}
		}
		return element;
	}
	
	
	public static void hibernate()
	{
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>(){
		public Boolean apply(WebDriver sessionInstance){
		return((JavascriptExecutor) sessionInstance).executeScript("return document.readyState").equals("complete");
				}
			};
		
			wait.until(pageLoadCondition);
		
	}
	
	
	public static void HighlightCurrentElement(WebElement element)
		{
			for(int i=0;i<2;i++)
			{
				JavascriptExecutor jse = (JavascriptExecutor)StaticData.driver;
				jse.executeScript("arguments[0].setAttribute('style', arguments[1]);",element,"colour: orange; border:4px solid orange;");
				jse.executeScript("arguments[0].setAttribute('style', arguments[1]);",element,"colour: pink; border:4px solid pink;");
				jse.executeScript("arguments[0].setAttribute('style', arguments[1]);",element,"colour: yellow; border:4px solid yellow;");
				jse.executeScript("arguments[0].setAttribute('style', arguments[1]);",element,"");
			}
		}
   /*
	public static void HighlightCurrentElement1(Select element)
	{
		for(int i=0;i<2;i++)
		{
			JavascriptExecutor jse = (JavascriptExecutor)StaticData.driver;
			jse.executeScript("arguments[0].setAttribute('style', arguments[1]);",element,"colour: orange; border:4px solid orange;");
			jse.executeScript("arguments[0].setAttribute('style', arguments[1]);",element,"colour: pink; border:4px solid pink;");
			jse.executeScript("arguments[0].setAttribute('style', arguments[1]);",element,"colour: yellow; border:4px solid yellow;");
			jse.executeScript("arguments[0].setAttribute('style', arguments[1]);",element,"");
		}
	}  */
/*	public static boolean pageLoaded(final String title)
	{
		try
		{
			ExpectedCondition<Boolean> pageLoadCondition= new ExpectedCondition<Boolean>()
			 {
				public Boolean apply(WebDriver driver)
				{
					return driver.getTitle().equals(title);
  				}
			
		     };
		     
		     wait.until(pageLoadCondition);
		     return true;
		  }
		 catch(Exception e)
		  {
		    	
		   	 return false;
		  }
	}*/
	
	
	public static boolean pageLoaded(final String title)
	{
		
		int maxTime=15;
		int waitTime=0;
	
		
		while(waitTime<=maxTime)
		{
		try
		{
			if (StaticData.driver.getTitle().equals(title))
		      return true;
			else
				StaticData.driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
			    waitTime++;
		  }
		 catch(Exception e)
		  {
			 StaticData.driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
			    waitTime++;
		  }
		
		}
		
		return false;
		
	}
	
	public static void captureScreenshot()
	{
		try
		{
			String screenshotPath="\\Screenshot"+"\\"+RandomStringUtils.randomAlphanumeric(8)+".png";
			String fullSreenshotPath = ".\\output\\"+StaticData.env+"\\"+StaticData.currentDateTime+screenshotPath;
			
			File src= ((TakesScreenshot)StaticData.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(fullSreenshotPath));
					
			screenshotPath="."+screenshotPath;
			Reporter.addScreenCaptureFromPath(screenshotPath);
		}
		catch(Exception e)
		 {
			// Reporter.addStepLog("Unable to take screenshot");
			 System.out.println("Unable to take screenshot, Exception encountered : "+e.getMessage());
		 }
		
	}
		
	
	public static Boolean ElementIsPresent(String type, String value) 
	{
		try
		{
			switch(type)
			{
				case "id": StaticData.driver.findElement(By.id(value));
				break;
				case "name": StaticData.driver.findElement(By.name(value));
				break;
				case "xpath" : StaticData.driver.findElement(By.xpath(value));
				break;
				case "className" : StaticData.driver.findElement(By.className(value));
				break;
				case "partialLinkText": StaticData.driver.findElement(By.partialLinkText(value));
				break;
				case "linkText": StaticData.driver.findElement(By.linkText(value));
				break;
				
				default: System.out.print("Invalid indentifier type");
			}
			return true;
		}
		
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	public static void Scroll_PageDown() 
	{
		je.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}  
	
	public static void Scroll_ToElement(WebElement element) 
	{
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}  
	
	public static void Scroll_DragScrollBarToEnd(WebElement scrollbar)
	{
		Actions act=new Actions(StaticData.driver);
		//WebElement draggablePartOfScrollbar = StaticData.driver.findElement(By.xpath("//*[@id='mCSB_1']/div[2]/div/div[1]/div"));
		int numberOfPixelsToDragTheScrollbarDown = 5000;
		act.moveToElement(scrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
		//Actions.dragAndDropBy(Sourcelocator, x-axis pixel of Destinationlocator, y-axis pixel of Destinationlocator)
	}
	
	
	
	
	
	
}
	 
	 
