package ro.usv.dss;

public class DynamicKernels {
	
	protected static int[] GetClasses(String[][] entrySet, int numberOfClasses, int numberOfFeatures)
	{
		String[][] kernels = new String[numberOfClasses][numberOfFeatures];
		
		int[] iClass = new int [entrySet.length]; // used to store the class for each element
		
		boolean done = false; // when we finish the clasification we exit the program
		
		
		// initialising kernels with first n patterns
		for(int i = 0; i < numberOfClasses; i++)
		{
			kernels[i] = entrySet[i]; 
		}
		
		// starting the algorithm
		
		while(!done)
		{
			done = true;
			
			double[][] centersOfGravity = new double[numberOfClasses][numberOfFeatures];
			int[] patternsCount = new int[numberOfClasses];
			int kMin = 0;
			for(int i = 0; i < entrySet.length; i++)
			{
				double minDistance = Double.MAX_VALUE;
				for(int k = 0; k < numberOfClasses; k++)
				{
					double distanceToKernel = calculateGeneralisedEuclidianDistance(entrySet[i], kernels[k]);
					if(distanceToKernel < minDistance)
					{
						minDistance = distanceToKernel;
						kMin = k;
					}
				}
				patternsCount[kMin] ++;
				
				for(int j = 0; j < numberOfFeatures; j++)
				{
					centersOfGravity[kMin][j] += Double.valueOf(entrySet[i][j]);
				}
				
				if(iClass[i] != kMin)
				{
					iClass[i] = kMin;
					done = false;
				}
			}
			
			if(!done)
			{
				for(int k = 0; k < numberOfClasses; k++)
				{
					for(int j = 0; j < numberOfFeatures; j++)
					{
						centersOfGravity[k][j] /= patternsCount[k];
					}
					
					String[][] classPatternSet = new String[patternsCount[k]][numberOfFeatures];
					int counter = 0;
					
					for(int nr = 0; nr < entrySet.length; nr++)
					{
						if(iClass[nr] == k)
						{
							classPatternSet[counter] = entrySet[nr];
							counter ++;
						}
					}
					kernels[k] = updateKernel(classPatternSet, centersOfGravity[k]);
				}
				
			}
		}
		return iClass;
	}
	
	private static String[] updateKernel(String[][] entrySet, double[] centerOfGravity)
	{
		double minDistance = Double.MAX_VALUE;
		int closestPattern = 0;
		for(int i = 0; i < entrySet.length; i++)
		{
			double distanceToKernel = calculateGeneralisedEuclidianDistance(entrySet[i], centerOfGravity);
			if(distanceToKernel < minDistance)
			{
				minDistance =  distanceToKernel;
				closestPattern = i;
			}
		}
		return entrySet[closestPattern];
	}
	
	
	protected static double calculateGeneralisedEuclidianDistance(String[] pattern, String[] pattern2)
	{
		double genEdDistance =  0;
		for (int featureIndex = 0; featureIndex < pattern.length; featureIndex ++)
		{
			genEdDistance += Math.pow(Double.valueOf(pattern[featureIndex]) -  Double.valueOf(pattern2[featureIndex]), 2);
		}
		return Math.floor(Math.sqrt(genEdDistance)*100)/100;
	}
	
	
	protected static double calculateGeneralisedEuclidianDistance(String[] pattern, double[] pattern2)
	{
		double genEdDistance =  0;
		for (int featureIndex = 0; featureIndex < pattern.length; featureIndex ++)
		{
			genEdDistance += Math.pow(Double.valueOf(pattern[featureIndex]) -  pattern2[featureIndex], 2);
		}
		return Math.floor(Math.sqrt(genEdDistance)*100)/100;
	}

}
