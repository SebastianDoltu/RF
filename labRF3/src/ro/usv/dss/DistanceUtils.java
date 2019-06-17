package ro.usv.dss;

public class DistanceUtils {

	public static double calculateEuclidianDistance(double[] x, double[] y)
	{
		double edDistance = Math.sqrt(Math.pow(x[0]- y[0], 2) + Math.pow(x[1] - y[1], 2));
		return edDistance;
	}
	
	public static double calculateMahalanobisDistance(double[] x, double[] y, int nrOfPatterns)
	{
		double mahDistance = 0;
		double sum = 0;
		for(int featureIndex = 0; featureIndex < x.length; featureIndex ++)
		{
			sum += Math.pow(x[featureIndex] - y[featureIndex], nrOfPatterns);
		}
		mahDistance = Math.pow(sum, 1.0/nrOfPatterns);
		return mahDistance;
	}
	
	public static double calculateCebisevDistance(double[] x, double[] y, int numberOfFeatures)
	{
		double cebDistance = 0;
		
		for(int featureIndex = 0; featureIndex < x.length; featureIndex ++)
		{
			double newValue = Math.abs(x[featureIndex] - y[featureIndex]);
			cebDistance = newValue > cebDistance ? newValue: cebDistance;
		}
		
		return cebDistance;
	}
	
	public static double calculateCityBlockDistance(double[] x, double[] y )
	{
		double CityDistance = 0;
		for(int featureIndex = 0; featureIndex < x.length; featureIndex ++)
		{
			CityDistance += Math.abs(x[featureIndex] - y[featureIndex]);
		}

		return CityDistance;
	}
}
