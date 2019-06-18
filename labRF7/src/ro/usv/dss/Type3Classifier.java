package ro.usv.dss;

import java.util.HashMap;
import java.util.List;

public class Type3Classifier {

	public static double[][] calculateWmatrix(double[][] learningSet, List<Double> classes, int numberOfClasses,
			int numberOfFeatures) {
		double[][] w = new double[numberOfClasses][numberOfFeatures + 1];

		for (int i = 0; i < numberOfClasses; i++) {
			double freeTermSum = 0.0;
			for (int j = 0; j < numberOfFeatures; j++) {
				double featureSum = 0;
				double featureNumber = 0;
				for (int k = 0; k < learningSet.length; k++) {
					if (learningSet[k][2] == classes.get(i)) {
						featureSum += learningSet[k][j];
						featureNumber++;
					}
				}
				w[i][j] = featureSum / featureNumber; // avarage
				freeTermSum += Math.pow(w[i][j], 2);
			}
			w[i][numberOfFeatures] = -0.5 * freeTermSum; // adding the free term
		}

		return w;
	}

	public static double[] calculateDiscriminant(double[] newPattern, double[][] w) {
		double[] discriminant = new double[w.length];
		for (int i = 0; i < w.length; i++) {
			double sum = 0;
			for (int j = 0; j < w[0].length; j++) {
				sum += w[i][j] * newPattern[j];
			}
			discriminant[i] = sum;
		}
		return discriminant;
	}

	protected static double GetClass(double[] discriminant, List<Double> classes)
	{
		double patternClass =  discriminant[0];
		int classIndex = 0;
		for(int i = 0; i < discriminant.length; i++)
		{
			if(patternClass < discriminant[i])
			{
				patternClass =  discriminant[i];
				classIndex =  i;
			}
		}
		patternClass = classes.get(classIndex);
		return patternClass;
	}
}
