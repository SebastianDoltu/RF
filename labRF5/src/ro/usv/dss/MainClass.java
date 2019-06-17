package ro.usv.dss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MainClass {
	
	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (K key : map.keySet()) {
			if (value.equals(map.get(key))) {
				return key;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		
	
		
		List<City> learningSet = null;
		try {
			
			// =============================   reading data from file    ===============================
			
			
			learningSet = FileUtils.readLearningSetFromFile("data.csv");
			
			int numberOfPatterns = learningSet.size();
			for(City c : learningSet)
			{
				System.out.println(c.toString());
			}
			System.out.println("Learning set has " + numberOfPatterns);
			
			
			
			// ==================== the cities we want to find their county  ===========================
			
			City city1 = new City(25.89, 47.56,"", "");
			City city2 = new City(24, 45.15,"", ""); 
			City city3 = new City(25.33, 45.44,"", "");
			
			List<City> newPatterns = new ArrayList<City>();
			newPatterns.add(city1);
			newPatterns.add(city2);
			newPatterns.add(city3);
			
			
			// ================== calculate distances from those 3 cities to all other 13k ==============
			
			
			List<Map<City, double[]>> distancesList = new ArrayList<Map<City, double[]>>();
		
			for(int i = 0; i < newPatterns.size(); i++)
			{
				Map<City, double[]> distances = new HashMap<City, double[]>();
				double[] cityDistance =  new double[learningSet.size()];
				double[] pattern = {newPatterns.get(i).GetX(), newPatterns.get(i).GetY()};
				for(int j = 0; j < learningSet.size(); j++)
				{
					double[] pattern2 = {learningSet.get(j).GetX(), learningSet.get(j).GetY()};
					cityDistance[j] = DistanceUtils.calculateGeneralisedEuclidianDistance(pattern, pattern2);
				}
				distances.put(newPatterns.get(i), cityDistance);
				distancesList.add(distances);
			}
			
			// ================= get the closest k cities for our 3 input foreign cities =================
			
			int k = 9;
			
			
			for(int i = 0; i < 3; i++)
			{
				TreeMap<String, Integer>  howManyCities = new TreeMap<String, Integer>();
				TreeMap<Double, String> closestCities = DistanceUtils.getClosestCities(learningSet, distancesList.get(i), k);
				System.out.println(closestCities);
				System.out.println(closestCities.size());
				// calculate the number of cities in each county)
				for(Map.Entry<Double, String> entry : closestCities.entrySet())
				{
					if(howManyCities.containsKey(entry.getValue()))
					{						
						howManyCities.replace(entry.getValue(), howManyCities.get(entry.getValue())+ 1);
					}
					else
					{			
						howManyCities.put(entry.getValue(), 1);
					}
				}			
				
				System.out.println(howManyCities);
				
				// calculate max apearence of cities
				int max = 0;
				
				for(Map.Entry<String, Integer> entry : howManyCities.entrySet())
				{				
					if(max < entry.getValue())
						max = entry.getValue();
					
				}
				
				// getting the county for most cities
				
				String closestCity = getKey(howManyCities, max);
				newPatterns.get(i).setCounty(closestCity);
				
			}
			
			System.out.println(newPatterns);
			
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
