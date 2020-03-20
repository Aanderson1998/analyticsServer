package com.analyticsServer.AnalyticsServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class getData {
	
	public static List<String> getTopUrls(List<List<String>> table) {
		int index = 1; // column index in table for url
		HashMap<String, Integer> map = new HashMap<>();
		int i = 0;
		while (i <= table.size()) {
			String url = table.get(i).get(index);
			if (map.containsKey(url) == false) {
				map.put(url, 0);
			}
			map.put(url, map.get(url) + 1);
			i++;
		}
		List<Integer> topUrls = new ArrayList<Integer>(map.values());
		// get max three from map and put in list and return
		return null;
	}

	public static List<String> getTopBrowsers(List<List<String>> table) {
		int index = 2; // column index in table for browser
		HashMap<String, Integer> map = new HashMap<>();
		int i = 0;
		while (i <= table.size()) {
			String browser = table.get(i).get(index);
			if (map.containsKey(browser) == false) {
				map.put(browser, 0);
			}
			map.put(browser, map.get(browser) + 1);
			i++;
		}
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		ArrayList<Entry<String, Integer>> listOfEntry = new ArrayList<Entry<String, Integer>>(entrySet);
		for (int j = 0; j <= 3; j++) {
			// String browser = Collections.max(listOfEntry.get(i).getValue());
		}
		// get max three from map and put in list and return
		return null;
	}

	
	public static int getNumUsers(List<List<String>> table) {
		int index = 0; // column index in table for unique id
		int i = 0;
		List<String> uniqueUsers = new ArrayList<String>();
		while (i <= table.size()) {
			String user = table.get(i).get(index);
			if (uniqueUsers.contains(user) == false) {
				uniqueUsers.add(user);
			}
			i++;
		}
		return uniqueUsers.size();
	}

}
