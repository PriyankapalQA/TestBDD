package testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testSelenium.StaticData;

public class TestDataUtil {

		 
	public static String readTestData(String featureName,String scenarioName,String colName)     //returns the test data from excel by matching the feature & scenario name
	{
	 
	  String sheetName= StaticData.env;
	  int colNo = 0;
	  String testData=null;
	  	   
	  File file = new File(StaticData.testDataFilePath+"\\"+StaticData.testDataFilename);
	  try {
			FileInputStream inputStream = new FileInputStream(file);
			  String fileExtensionName = StaticData.testDataFilename.substring(StaticData.testDataFilename.indexOf("."));
			  
			 
			  if(fileExtensionName.equals(".xlsx"))
				  StaticData.testDataWorkbook = new XSSFWorkbook(inputStream);

			  else if(fileExtensionName.equals(".xls"))
				  StaticData.testDataWorkbook = new HSSFWorkbook(inputStream);

			  Sheet testDataWorksheet = StaticData.testDataWorkbook.getSheet(sheetName);
			  
			  Row row = testDataWorksheet.getRow(0);
			  int colCount = row.getLastCellNum()- row.getFirstCellNum();
			  
			  for(int i=2;i<=colCount;i++)
			  { 
				  if(StaticData.df.formatCellValue(row.getCell(i)).equalsIgnoreCase(colName))
				  {	 
					 colNo = i;    //gets which column to read the test data
					 break;
				  }
			  }
			  int rowCount = testDataWorksheet.getLastRowNum()-testDataWorksheet.getFirstRowNum();
		      
			      for (int i=1;i<=rowCount;i++)
			      {
			    	 row = testDataWorksheet.getRow(i);
			   
			    	 // check to see which row matches the scenario & feature name
			    	 if (StaticData.df.formatCellValue(row.getCell(1)).equalsIgnoreCase(scenarioName) & StaticData.df.formatCellValue(row.getCell(0)).equalsIgnoreCase(featureName)) 
	            		 { 
		            		 testData= StaticData.df.formatCellValue(row.getCell(colNo));
		            		 break;
	            		 }
			      }
		  }
	   catch (IOException e) {
		 		//e.printStackTrace();
		   System.out.println("Cannot Read testdata, exception encountered : "+e.getMessage());
			 }
	  
	  return testData; 	 
		}	
		
}
