package testUtils;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import testSelenium.StaticData;

public class DriverUtil {
	
	
//public static WebDriver driver; 
	
	public static void initializeDriver ()
	{
		String browserName = StaticData.browser;
       if (browserName.equalsIgnoreCase("Chrome"))
       {   
		System.setProperty("webdriver.chrome.driver", StaticData.browserDriverPath+"\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("useAutomationExtension", false);

	  //  options.addArguments("test-type");
       // options.addArguments("no-sandbox");
        //Fix for cannot get automation extension
     //   options.addArguments("disable-extensions");
    //    options.addArguments("start-maximized");
       // options.addArguments("--js-flags=--expose-gc");         

    //    options.addArguments("disable-plugins");

        //options.addArguments("--enable-precise-memory-info"); 
     //   options.addArguments("--disable-popup-blocking");
    //    options.addArguments("--disable-default-apps");
   //     options.addArguments("test-type=browser");
     //   options.addArguments("disable-infobars");  
	    
		 StaticData.driver = new ChromeDriver(options);
		 WaitUtil.setDriver(StaticData.driver);
       }
       else 
    	   System.out.print("Please select Chrome as browser for this machine");
	}
	
	public static void closeBrowser()
	{
		StaticData.driver.close();
		StaticData.driver.quit();
		StaticData.driver=null;
	}

}
