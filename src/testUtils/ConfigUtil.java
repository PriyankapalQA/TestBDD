package testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testSelenium.StaticData;

public class ConfigUtil {
  
  static File file = new File(StaticData.configFilePath+"\\"+StaticData.configFilename);
  static String fileExtensionName = StaticData.configFilename.substring(StaticData.configFilename.indexOf("."));
  
  public static void readConfigExcelFile()
  {
	  		  try {
	  			FileInputStream inputStream = new FileInputStream(file);
				
				  if(fileExtensionName.equals(".xlsx"))
					  StaticData.configDataWorkbook = new XSSFWorkbook(inputStream);

				  else if(fileExtensionName.equals(".xls"))
					  StaticData.configDataWorkbook = new HSSFWorkbook(inputStream);
	  		  	 }
		  catch (IOException e) {
			 	//	e.printStackTrace();
			  System.out.println("Exception encountered : "+e.getMessage());
				 }
  }
  
	public static void readConfigurations()
	{
		try{
	   		  Sheet configDataWorksheet = StaticData.configDataWorkbook.getSheet(StaticData.configuationsSheetName);
		      int rowCount1 = configDataWorksheet.getLastRowNum()-configDataWorksheet.getFirstRowNum();
		      
			      for (int i=0;i<=rowCount1;i++)
			      {
			    	Row  row = configDataWorksheet.getRow(i);			    			   		    	
			    	String cell2= StaticData.df.formatCellValue(row.getCell(0));
			    	switch (cell2)
			    	{
			    	  case "Environment": StaticData.env = StaticData.df.formatCellValue(row.getCell(1));
			    	  	break;
			    	  case "Browser":  StaticData.browser = StaticData.df.formatCellValue(row.getCell(1));
			    	  	break;
			    	  case "ThreadWait": StaticData.threadWait = Integer.parseInt(StaticData.df.formatCellValue(row.getCell(1)));
			    	  	break;
			    	  case "MaxWaitForElement": StaticData.maxWaitForElement = Integer.parseInt (StaticData.df.formatCellValue(row.getCell(1))); 
			    	  	break;
			    	  case "jdk_Path": StaticData.jdkPath = StaticData.df.formatCellValue(row.getCell(1));
			    	  	break;
			    	  default: System.out.print("Not valid configuration");  
   		
		   	       }
			      
			  }
		}
	  catch (Exception e) {
		 	//	e.printStackTrace();
		  System.out.println("Exception encountered : "+e.getMessage());
			 }
			 
		}
	
	
	public static void readConfigValues()
	{
		try{
	      Sheet configValueWorksheet = StaticData.configDataWorkbook.getSheet(StaticData.configValuesSheetName);
	      int rowCount2 = configValueWorksheet.getLastRowNum()-configValueWorksheet.getFirstRowNum();   
        	 
	      for (int i=0;i<=rowCount2;i++)
	      {
	    	Row  row1 = configValueWorksheet.getRow(i);
	    	String cell3= StaticData.df.formatCellValue(row1.getCell(0));
        	 if (cell3.equalsIgnoreCase(StaticData.env))
        	 {
        		 StaticData.url = StaticData.df.formatCellValue(row1.getCell(1));
        	   
        	 }
        	 else if (cell3.equalsIgnoreCase(StaticData.browser))
        		 StaticData.browserDriverPath = StaticData.df.formatCellValue(row1.getCell(1));
        
            }
	  }
		catch (Exception e) {
		 	//	e.printStackTrace();
			 System.out.println("Exception encountered : "+e.getMessage());
			 }
	}
	
	public static void readIdentifierValues(String fieldName)
	{
		try{
	 		Sheet identifierDataWorksheet = StaticData.configDataWorkbook.getSheet(StaticData.identifierDetailsSheetName);
				    
	        int rowCount = identifierDataWorksheet.getLastRowNum()-identifierDataWorksheet.getFirstRowNum();
	         
	        for (int i = 0; i <= rowCount; i++)
              {
            	   Row row = identifierDataWorksheet.getRow(i);
            	   
            	   String cellValue= StaticData.df.formatCellValue(row.getCell(0));
            		if (cellValue.equalsIgnoreCase(fieldName))
            		{
            			StaticData.identifierType = StaticData.df.formatCellValue(row.getCell(1));
            			StaticData.identifierValue = StaticData.df.formatCellValue(row.getCell(2));
                 		break;			
            		}
            		
                }
		      }   
			  
    	 catch (Exception e) {
			//	e.printStackTrace();
    		 System.out.println("Exception encountered : "+e.getMessage());
			 }
			
		}

	
	public static String[] readTagName()
	{
		String tagName[] = null;
		try{
		Sheet runnerDataWorksheet = StaticData.configDataWorkbook.getSheet(StaticData.tCDriverScriptSheetName);
		int rowCount = runnerDataWorksheet.getLastRowNum()-runnerDataWorksheet.getFirstRowNum();
		   
	    tagName = new String[rowCount];
		int arrCount=0;
		   for (int i = 1; i <= rowCount; i++)
           {
         	   Row row = runnerDataWorksheet.getRow(i);
         	   
         	   String cellValue= StaticData.df.formatCellValue(row.getCell(1));
         		if (cellValue.equalsIgnoreCase("Y"))
         		{  
           			tagName[arrCount] = StaticData.df.formatCellValue(row.getCell(0));
         			arrCount++;
         			
         		}
         		
             }
	      }
		 catch (Exception e) {
			//	e.printStackTrace();
			 System.out.println("Exception encountered : "+e.getMessage());
			 }
		
		return tagName;
		
	}  
	
	
}
