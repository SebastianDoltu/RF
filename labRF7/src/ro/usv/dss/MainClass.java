package ro.usv.dss;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// ================= read data from the file ============================ //
		
		double[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
		int numberOfPatterns =  learningSet.length;
		int numberOfFeatures =  learningSet[0].length -1;
		
		System.out.println(String.format("Our data set has %s patterns and %s features.", numberOfPatterns, numberOfFeatures));
		
		
		
		// ================ getting the classes ================= //
		
		
		List<Double> classes = new ArrayList<Double>(); // class, number of forms for each class
		for(int i = 0; i < learningSet.length; i++)
		{		
			if (!classes.contains(learningSet[i][2])) 
			{
				classes.add(learningSet[i][2]);
			}
		}
		
		int numberOfClasses = classes.size();
		System.out.println(classes);
		
		// ============ calculate W matrix ================= //
		
		double[][] w = Type3Classifier.calculateWmatrix(learningSet, classes, numberOfClasses, numberOfFeatures);
		
		System.out.println("W matrix");
		for(int i = 0; i < numberOfClasses; i++)
		{
			for(int j = 0; j < numberOfFeatures +1; j++)
			{
				System.out.print(w[i][j] + " | ");
			}
			System.out.println();
		}
		
		
		
		// ========== calculate discriminant functions ============= //
		double[] newPattern = {4,5,1}; // 1 because our w matrix has 3 lines
		
		System.out.println("Discriminant");
		double[] discriminant = Type3Classifier.calculateDiscriminant(newPattern, w);
		for(int i = 0; i < discriminant.length; i++)
		{
			System.out.println(discriminant[i] + " | ");
		}
		
		// ======= getting the right class for our form ======= //
		
		double newPatternClass = Type3Classifier.GetClass(discriminant, classes);
		System.out.println("The class for our new form is " + newPatternClass);
	}

}
