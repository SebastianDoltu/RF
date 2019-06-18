package ro.usv.dss;

public class MainClass {

	public static void main(String[] args) {
		String[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patterns and %s features", numberOfPatterns,
					numberOfFeatures));
			
			//  ================== Dynamic Kernel Algorithm ================= //

			String [] classes = new String[] {"class1", "class2"};
			int[] iClass = DynamicKernels.GetClasses(learningSet, classes.length, numberOfFeatures);
			
			for(int i = 0; i < numberOfPatterns; i ++)
			{
				for(int j = 0; j < numberOfFeatures; j++)
				{
					System.out.print(learningSet[i][j] + " " + classes[iClass[i]]);
				}
				System.out.println();
			}
		
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}
}
