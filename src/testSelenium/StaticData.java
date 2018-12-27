package testSelenium;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StaticData {
	  public static WebDriver driver; 
	
	  public static String currentDateTime;
	  public static String env;
	  public static String url;
	  
	  public static String browser;
	  public static String browserDriverPath;
	  
	  public static String identifierType;
	  public static String identifierValue;
	  public static int threadWait;
	  public static int maxWaitForElement;
	  public static String jdkPath;
	  
	  public static String snapshotName="";
	  public static DataFormatter df = new DataFormatter();
	  
	  public static String configFilePath=".\\resources\\ConfigFiles";
	  public static String configFilename="Configuration.xlsx";
	  public static Workbook configDataWorkbook = null;
	  
	  public static String testDataFilePath=".\\resources\\TestData";
	  public static String testDataFilename="TestData.xlsx";
	  public static Workbook testDataWorkbook = null;

	  
	  
	  public static String configuationsSheetName = "Configuations";
	  public static String configValuesSheetName= "ConfigValues";
	  public static String identifierDetailsSheetName= "IdentifierDetails";
	  public static String tCDriverScriptSheetName = "TCDriverScript";
	  
	  
}
