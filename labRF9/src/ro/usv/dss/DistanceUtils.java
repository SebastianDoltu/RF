package ro.usv.dss;

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
		return Math.floor(Math.sqrt(genEdDistance)*100)/100;
	}
	
	public static double calculateGeneralisedEuclidianDistance(String[] pattern, String[] pattern2)
	{
		double genEdDistance =  0;
		for (int featureIndex = 0; featureIndex < pattern.length; featureIndex ++)
		{
			genEdDistance += Math.pow(Double.valueOf(pattern[featureIndex]) -  Double.valueOf(pattern2[featureIndex]), 2);
		}
		return Math.floor(Math.sqrt(genEdDistance)*100)/100;
	}
	
	public static double[][] generateMatrixDistance(double[][] learningSet, int numberOfPatterns)
	{
		double[][] distancesMatrix =  new double[numberOfPatterns][numberOfPatterns];
		for(int featureIndex1 = 0; featureIndex1 < numberOfPatterns; featureIndex1 ++)
		{
			for(int featureIndex2 = featureIndex1 + 1; featureIndex2 < numberOfPatterns; featureIndex2 ++)
			{
				distancesMatrix [featureIndex1][featureIndex2] = 
						calculateGeneralisedEuclidianDistance(learningSet[featureIndex1], learningSet[featureIndex2]);
				distancesMatrix[featureIndex2][featureIndex1] = distancesMatrix [featureIndex1][featureIndex2];
			}
		}
		return distancesMatrix;
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
	
	public static double calculateMahalanobisDistance(double[] x, double[] y, int nrOfPatterns)
	{
		double mahDistance = 0;
		double sum = 0;
		for(int featureIndex = 0; featureIndex < x.length-1; featureIndex ++)
		{
			sum += Math.pow(x[featureIndex] - y[featureIndex], nrOfPatterns);
		}
		mahDistance = Math.pow(sum, 1.0/nrOfPatterns);
		return mahDistance;
	}
	
	public static double calculateCebisevDistance(double[] x, double[] y, int numberOfFeatures)
	{
		double cebDistance = 0;
		
		for(int featureIndex = 0; featureIndex < x.length-1; featureIndex ++)
		{
			double newValue = Math.abs(x[featureIndex] - y[featureIndex]);
			cebDistance = newValue > cebDistance ? newValue: cebDistance;
		}
		
		return cebDistance;
	}
	
	public static double calculateCityBlockDistance(double[] x, double[] y )
	{
		double CityDistance = 0;
		for(int featureIndex = 0; featureIndex < x.length-1; featureIndex ++)
		{
			CityDistance += Math.abs(x[featureIndex] - y[featureIndex]);
		}

		return CityDistance;
	}
}
