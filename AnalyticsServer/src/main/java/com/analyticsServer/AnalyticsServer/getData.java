package com.analyticsServer.AnalyticsServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class getData {
	
	
	
	public static List<String> getTopUrls(List<List<String>> table) {
		int index = 5; // column index in table for URLs
		HashMap<String, Integer> map = new HashMap<>();
		int i = 0;
		while (i < table.size()) {
			String URL = table.get(i).get(index);
			if (map.containsKey(URL) == false) {
				map.put(URL, 0);
			}
			map.put(URL, map.get(URL) + 1);
			i++;
		}
		List<String> keys = new ArrayList<String>(map.keySet()); 
		List<Integer> values = new ArrayList<Integer>(map.values());
		List<String> topURLs = new ArrayList<String>();
		for (int j = 0; j < map.size() ; j++) {
			int maxVal = Collections.max(values); 
			int maxId = values.indexOf(maxVal);
			topURLs.add(keys.get(maxId));
			keys.set(maxId, null);
			values.set(maxId,-1);
		}
		topURLs=topURLs.stream().limit(3).collect(Collectors.toList());
		return topURLs;
	}
	
	
	
	public static List<String> getTopBrowsers(List<List<String>> table) {
		int index = 3; // column index in table for browser
		HashMap<String, Integer> map = new HashMap<>();
		int i = 0;
		while (i < table.size()) {
			String browser = table.get(i).get(index);
			if (map.containsKey(browser) == false) {
				map.put(browser, 0);
			}
			map.put(browser, map.get(browser) + 1);
			i++;
		}
		List<String> keys = new ArrayList<String>(map.keySet()); 
		List<Integer> values = new ArrayList<Integer>(map.values());
		List<String> topBrowsers = new ArrayList<String>();
		for (int j = 0; j < map.size() ; j++) {
			int maxVal = Collections.max(values); 
			int maxId = values.indexOf(maxVal);
			topBrowsers.add(keys.get(maxId));
			keys.set(maxId, null);
			values.set(maxId,-1);
		}
		topBrowsers=topBrowsers.stream().limit(3).collect(Collectors.toList());
		return topBrowsers;
	}
	
	
	
	public static int getNumUsers(List<List<String>> table) {
		int index = 2; // column index in table for unique id
		int i = 0;
		List<String> uniqueUsers = new ArrayList<String>();
		while (i < table.size()) {
			String user = table.get(i).get(index);
			if (uniqueUsers.contains(user) == false) {
				uniqueUsers.add(user);
			}
			i++;
		}
		return uniqueUsers.size();
	}
	

}
