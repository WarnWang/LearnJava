package wapapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Author: Wang Youan, Date: 2015-10-28
 * 
 * I use dynamic programming to solve this question. I find the fact that the
 * highest score of each line has nothing to do with the formal line, so I
 * calculate each points of the given line, then use these value to calculate
 * those points on the next line. When I reach the right border, I will find the
 * max value of them, then get the answer.
 */

public class TravelInformationCenter {
    private static int noOfCity;
    private static int noOfQuery;
    private static int currentFestival = 2;
    private static HashMap<HashSet<Integer>, Integer> destination = new HashMap<>();
    private static ArrayList<ArrayList<Integer>> map = new ArrayList<>();

    private static void handleDestinations(List<String> roadsInfo) {
	for (String string : roadsInfo) {
	    int city1 = Integer.parseInt(string.split(" ")[0]);
	    int city2 = Integer.parseInt(string.split(" ")[1]);
	    HashSet<Integer> cityInfo = createHashSet(city1, city2);
	    destination.put(cityInfo, 1);
	    map.get(city1 - 1).add(city2);
	    map.get(city2 - 1).add(city1);
	}
    }

    private static int findDestination(int queryCity, int currentCity) {
	int path = -1;
	HashSet<Integer> cityInfo = createHashSet(queryCity, currentFestival);
	if (destination.containsKey(cityInfo)) {
	    path = destination.get(cityInfo);
	} else {
	    for (int nextCity : map.get(queryCity - 1)) {
		int tempPath;
		if (nextCity == currentCity) {
		    continue;
		}
		tempPath = findDestination(nextCity, queryCity);
		if (tempPath != -1) {
		    path = tempPath + 1;
		    destination.put(cityInfo, path);
		    break;
		}
	    }
	}
	return path;
    }

    private static int getDestinations(int queryCity) {
	int path = 0;
	HashSet<Integer> cityInfo = createHashSet(queryCity, currentFestival);
	if (queryCity == currentFestival) {
	    path = 0;
	} else if (destination.containsKey(cityInfo)) {
	    path = destination.get(cityInfo);
	} else {
	    for (int nextCity : map.get(queryCity - 1)) {
		int lastpath = findDestination(nextCity, queryCity);
		if (lastpath > 0) {
		    path = lastpath + 1;
		    break;
		}
	    }
	    destination.put(cityInfo, path);
	}
	return path;

    }

    private static HashSet<Integer> createHashSet(int a, int b) {
	HashSet<Integer> hashSet = new HashSet<>();
	hashSet.add(a);
	hashSet.add(b);
	return hashSet;
    }

    private static void handleEvents(List<String> events) {
	for (String string : events) {
	    int typeOfQuery = Integer.parseInt(string.split(" ")[0]);
	    int queryCity = Integer.parseInt(string.split(" ")[1]);

	    if (typeOfQuery == 1) {
		currentFestival = queryCity;
	    } else {
		System.out.println(getDestinations(queryCity));
	    }
	}
    }

    public static void main(String[] args) {

	// This is the input of this exam.
	String input = "5 5\n"
		+ "1 2\n"
		+ "1 3\n"
		+ "3 4\n"
		+ "3 5\n"
		+ "2 5\n"
		+ "2 3\n"
		+ "1 3\n"
		+ "2 3\n"
		+ "2 4";

	List<String> input_info = Arrays.asList(input.split("\n"));

	noOfCity = Integer.parseInt(input_info.get(0).split(" ")[0]);
	noOfQuery = Integer.parseInt(input_info.get(0).split(" ")[1]);

	for (int i = 0; i < noOfCity; i++) {
	    map.add(new ArrayList<Integer>());
	}

	handleDestinations(input_info.subList(1, noOfCity));
	handleEvents(input_info.subList(noOfCity, noOfCity + noOfQuery));
    }

}
