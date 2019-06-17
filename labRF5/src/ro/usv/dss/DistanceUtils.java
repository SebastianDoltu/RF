package ro.usv.dss;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistanceUtils {

	public static double calculateEuclidianDistance(double[] x, double[] y)
	{
		double edDistance = Math.sqrt(Math.pow(x[0]- y[0], 2) + Math.pow(x[1] - y[1], 2));
		return edDistance;
	}
	
	public static double calculateGeneralisedEuclidianDistance(double[] pattern, double[] pattern2)
	{
		double genEdDistance =  0;
		for (int featureIndex = 0; featureIndex < pattern.length; featureIndex ++)
		{
			genEdDistance += Math.pow(pattern[featureIndex] -  pattern2[featureIndex], 2);
		}
		return Math.sqrt(genEdDistance);
	}
	
	public static double[][] generateMatrixDistance(double[][] learningSet, int numberOfPatterns)
	{
		double[][] distancesMatrix =  new double[numberOfPatterns][numberOfPatterns];
		for(int featureIndex1 = 0; featureIndex1 < numberOfPatterns; featureIndex1 ++)
		{
			for(int featureIndex2 = featureIndex1 + 1; featureIndex2 < numberOfPatterns; featureIndex2 ++)
			{
				distancesMatrix [featureIndex1][featureIndex2] = calculateGeneralisedEuclidianDistance(learningSet[featureIndex1], learningSet[featureIndex2]);
				distancesMatrix[featureIndex2][featureIndex1] = distancesMatrix [featureIndex1][featureIndex2];
			}
		}
		return distancesMatrix;
	}
	
	public static TreeMap<Double, String> getClosestCities(List<City> learningSet, Map<City, double[]> cityDistances, int k)
	{	
		TreeMap<Double, String> closestCities = new TreeMap<>();
		Map.Entry<City, double[]> entry = cityDistances.entrySet().iterator().next();
		double[] distances = entry.getValue();

		// treemap initialization
		for(int i = 0; i < k; i++)
		{
			closestCities.put(distances[i], learningSet.get(i).GetCounty());
			
		}
		
		// get the closest k cities for our city
		for(int i = 0; i < distances.length; i++)
		{
				if(closestCities.lastKey() > distances[i])
				{
					closestCities.remove(closestCities.lastKey());
					closestCities.put(distances[i], learningSet.get(i).GetCounty());
				}				
		}
		
		return closestCities;
	}
	
	public static int clasifyPattern(double[][] learningSet, double[] rowDistancesMatrix, double[] pattern, int searchedPattern)
	{
		int patternClass = 0;
		int closestPattern = 0;
		double minDistance = rowDistancesMatrix[0];
		for(int distancesIndex = 0; distancesIndex < rowDistancesMatrix.length; distancesIndex++)
		{
			if(rowDistancesMatrix[distancesIndex] < minDistance && distancesIndex != searchedPattern)
			{
				minDistance = rowDistancesMatrix[distancesIndex];
				closestPattern = distancesIndex;
			}
		}
		
		return patternClass;
	}
	

}
