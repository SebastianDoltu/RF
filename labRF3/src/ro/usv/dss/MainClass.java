package ro.usv.dss;

public class MainClass {

	public static void main(String[] args) {
		double[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns,
					numberOfFeatures));
			for (int indexOfPatterns = 1; indexOfPatterns < numberOfPatterns; indexOfPatterns++) {
				double edDistance = DistanceUtils.calculateEuclidianDistance(learningSet[0], learningSet[indexOfPatterns]);
				System.out.println(String.format("Euclidian distance for form%s is %s", indexOfPatterns, edDistance));
				double mahDistance = DistanceUtils.calculateMahalanobisDistance(learningSet[0], learningSet[indexOfPatterns],
						numberOfPatterns);
				System.out.println(String.format("Mahalanobis distance for form%s is %s", indexOfPatterns, mahDistance));
				double cebDistance = DistanceUtils.calculateCebisevDistance(learningSet[0], learningSet[indexOfPatterns],
						numberOfFeatures);
				System.out.println(String.format("Cebisev distance for form%s is %s", indexOfPatterns, cebDistance));
				double cityDistance = DistanceUtils.calculateCityBlockDistance(learningSet[0], learningSet[indexOfPatterns]);
				System.out.println(String.format("Cityblock distance for form%s is %s", indexOfPatterns, cityDistance));
			}

		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
