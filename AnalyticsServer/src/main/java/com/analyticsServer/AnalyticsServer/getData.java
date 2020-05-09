package com.analyticsServer.AnalyticsServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//class that gets URL list, browser list, and number of unique users
public class getData {

	// function to get top three URLs
	public static List<String> getTopUrls(List<List<String>> table) {
		int index = 5; // column index in table for URLs
		// creating hash map to hold URL and number of request with that URL
		// I like the HashMap approach!
		HashMap<String, Integer> map = new HashMap<>();
		// while loop iterating through in memory table and adding to hash map for each
		// URL
		int i = 0;
		while (i < table.size()) {
			String URL = table.get(i).get(index);
			if (map.containsKey(URL) == false) {
				map.put(URL, 0);
			}
			map.put(URL, map.get(URL) + 1);
			i++;
		}
		// creating two lists. One with the URLs from the hash map and one with the
		// value (number of requests with that URL)
		List<String> keys = new ArrayList<String>(map.keySet());
		List<Integer> values = new ArrayList<Integer>(map.values());
		// creating new list that will keep URLs in order of most used to least used
		List<String> topURLs = new ArrayList<String>();
		// going through map, getting max value, then getting the URL that goes with
		// that max value, then adding this URL to the topURLs list, then setting the
		// values at that index to -1 and null in the URL and values list, so they are
		// not looked at again
		// going through entire map to sort the URLs and put them in topURLs list
		for (int j = 0; j < map.size(); j++) {
			int maxVal = Collections.max(values);
			int maxId = values.indexOf(maxVal);
			topURLs.add(keys.get(maxId));
			keys.set(maxId, null);
			values.set(maxId, -1);
		}
		// taking top three values from list and returning them
		// Using streams. I like it! :)
		topURLs = topURLs.stream().limit(3).collect(Collectors.toList());
		return topURLs;
	}
	
	
	
	// function to get top 3 browsers
	// I wonder if this method and the getTopURLs could share some code.
	public static List<String> getTopBrowsers(List<List<String>> table) {
		int index = 3; // column index in table for browser
		// hash map to hold browser and number of times it appears in table
		HashMap<String, Integer> map = new HashMap<>();
		// going through list and adding to hash map for each browsr
		int i = 0;
		while (i < table.size()) {
			String browser = table.get(i).get(index);
			if (map.containsKey(browser) == false) {
				map.put(browser, 0);
			}
			map.put(browser, map.get(browser) + 1);
			i++;
		}
		// creating two lists, one to hold browsers and one to hold values
		List<String> keys = new ArrayList<String>(map.keySet());
		List<Integer> values = new ArrayList<Integer>(map.values());
		// table to hold sorted browsers in order of most seen to least seen
		List<String> topBrowsers = new ArrayList<String>();
		// going through map, finding max, and adding it to list
		for (int j = 0; j < map.size(); j++) {
			int maxVal = Collections.max(values);
			int maxId = values.indexOf(maxVal);
			topBrowsers.add(keys.get(maxId));
			keys.set(maxId, null);
			values.set(maxId, -1);
		}
		// taking top 3 from list and returning that list
		topBrowsers = topBrowsers.stream().limit(3).collect(Collectors.toList());
		return topBrowsers;
	}

	
	

	// function to get number of unique users
	public static int getNumUsers(List<List<String>> table) {
		int index = 2; // column index in table for unique id
		int i = 0;
		// list that will hold unique users
		// A HashSet might be a good data structure here.
		List<String> uniqueUsers = new ArrayList<String>();
		// while loop to go through table and add unique users to list
		while (i < table.size()) {
			String user = table.get(i).get(index);
			if (uniqueUsers.contains(user) == false) {
				uniqueUsers.add(user);
			}
			i++;
		}
		// returning size of list (which is how many unique users there are)
		return uniqueUsers.size();
	}

}
