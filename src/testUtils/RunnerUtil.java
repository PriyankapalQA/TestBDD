package testUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import testSelenium.StaticData;




public class RunnerUtil 
{
	public static void prepareRunnerFile(String tag[])
	{
		String runnerFilePath=".\\src\\testSelenium\\TestRunner.java";
    	File file = new File(runnerFilePath);
		 
		String line="";
		String tagLine = "";
		String finalLine = "";
		String pluginLine="";
		
		try{
			
			//creates the tag line by taking the tag names as an array from the argument
			tagLine =tagLine+"tags={"+'"'+"@"+tag[0]+'"';  
			 for(int a=1;a<tag.length;a++)
			  {
				 if(tag[a]!=null)
					 tagLine=tagLine+","+'"'+"@"+tag[a]+'"';
				 else
					 break;
		      }
			 
			 tagLine=tagLine+"}";
			 
				 
			 DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
			 Date date = new Date();
			
			 StaticData.currentDateTime = dateFormat.format(date);
			 File f = new File(".\\output\\"+StaticData.env+"\\"+StaticData.currentDateTime);
			 File f1 = new File(".\\output\\"+StaticData.env+"\\"+StaticData.currentDateTime+"\\Screenshot");
			 f.mkdir();
			 f1.mkdir();

			pluginLine=pluginLine+",plugin={"+'"'+"com.cucumber.listener.ExtentCucumberFormatter:output/"+StaticData.env +"/"+StaticData.currentDateTime+"/report.html"+'"'+"}";
				
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
		
			// Reads the file and adds the tag names and the output file location of the report
			while((line = reader.readLine()) != null)
			{ 
			  if(line.contains("tags"))
			  {
				line="";
				line=tagLine;						
			  }
			  else if (line.contains("plugin"))
			  {
				  line="";
				  line = pluginLine;
				  
			  }
			  
			  finalLine=finalLine+line+"\n";
	   	    }
		
			reader.close();
		  
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			writer.append(finalLine);
			writer.flush();
			writer.close();
			
			//after writing the contents to the test runner file, compiling it
		//	System.setProperty("java.home", "");
			
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
			StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
			Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(runnerFilePath));
			String[] options = new String[] { "-d", "bin" };
			JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, Arrays.asList(options), null, compilationUnits);
			boolean success = task.call();
			fileManager.close();
			System.out.println("Success: " + success);
		}
		catch (IOException e) {
		//  e.printStackTrace();
			 System.out.println("Exception encountered : "+e.getMessage());
		}
		
	}
	

	
} 
