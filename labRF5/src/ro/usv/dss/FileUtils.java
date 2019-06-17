package ro.usv.dss;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils 
{
	private static final String inputFileValuesSeparator = ",";
	private static final String outputFileValuesSeparator = ",";
	
	protected static List<City> readLearningSetFromFile(String fileName) throws USVInputFileCustomException
	{
		List<City> learningSet = null;
		try  {
			Stream<String> stream = Files.lines(Paths.get(fileName));
			learningSet = stream.map(FileUtils::convertLineToLearningSetRow).collect(Collectors.toList());
			
		} 
		catch (FileNotFoundException fnfe)
		{
			throw new USVInputFileCustomException(" We cannot find the scepicified file on USV computer");
		}	
		catch (IOException ioe) {
			throw new USVInputFileCustomException(" We encountered some errors while trying to read the specified file: " + ioe.getMessage());
		}
		catch (Exception e) {
			throw new USVInputFileCustomException(" Other errors: " + e.getMessage());
		}	
		return learningSet;
	}
	
	private static City convertLineToLearningSetRow(String line)
	{
		City learningSetRow;
		String[] stringValues = line.split(inputFileValuesSeparator);
	    learningSetRow = new City(Double.valueOf(stringValues[0]),Double.valueOf(stringValues[1]), stringValues[2], stringValues[3] );
		return learningSetRow;
	}
	
	
	private static double[][] convertToBiDimensionalArray(List<ArrayList<Double>> learningSet) {
		
		double[][] learningSetArray = new double[learningSet.size()][];
		
		for (int n = 0; n < learningSet.size(); n++) {
			ArrayList<Double> rowListEntry = learningSet.get(n);
			
			// get each row double values
			double[] rowArray = new double[learningSet.get(n).size()];
			
			for (int p = 0; p < learningSet.get(n).size(); p++) 
			{				
				rowArray[p] = rowListEntry.get(p);
			}
			learningSetArray[n] = rowArray;
			
		}
		return learningSetArray;
	}
	
	
	
	protected static void writeLearningSetToFile(String fileName, double[][] normalizedSet)
	{
		// first create the byte array to be written
		StringBuilder stringBuilder = new StringBuilder();
		for(int n = 0; n < normalizedSet.length; n++) //for each row
		{
			//for each column
			 for(int p = 0; p < normalizedSet[n].length; p++) 
			 {
				//append to the output string
				 stringBuilder.append(normalizedSet[n][p]+"");
				 //if this is not the last row element
			      if(p < normalizedSet[n].length - 1)
			      {
			    	  //then add separator
			    	  stringBuilder.append(outputFileValuesSeparator);
			      }
			 }
			//append new line at the end of the row
			 stringBuilder.append("\n"); 
		}
		try {
			Files.write(Paths.get(fileName), stringBuilder.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
