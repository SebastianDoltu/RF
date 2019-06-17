package ro.usv.dss;

public class Main {

	private static double[][] normalizeLearningSet(double[][] learningSet) {

		double[][] normalizedSet = new double[learningSet.length][learningSet[0].length];
		for (int j = 0; j < learningSet[0].length; j++) {
			
			double maxValue = learningSet[0][j];
			double minValue = learningSet[0][j];
			
			for (int i = 0; i < learningSet.length; i++) {
				minValue = learningSet[i][j] < minValue ? learningSet[i][j] : minValue;
				maxValue = learningSet[i][j] > maxValue ? learningSet[i][j] : maxValue;
			}
			for (int i = 0; i < learningSet.length; i++) {
				minValue = learningSet[i][j] < minValue ? learningSet[i][j] : minValue;
				maxValue = learningSet[i][j] > maxValue ? learningSet[i][j] : maxValue;
			}
			for (int i = 0; i < learningSet.length; i++) {
				normalizedSet[i][j] = Math.floor((learningSet[i][j] - minValue) / (maxValue - minValue) * 100) / 100;
			}

		}

		return normalizedSet;
	}

	public static void main(String[] args) {
		double[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
		System.out.println(learningSet[0][3]);
		normalizeLearningSet(learningSet);
		FileUtils.writeLearningSetToFile("out.txt", normalizeLearningSet(learningSet));

	}

}
