package com.analyticsServer.AnalyticsServer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class server2 {

	@RequestMapping(value = "/views", method = RequestMethod.GET)
	public String getStatParams(@RequestParam(value = "startDate", defaultValue = "") String startDate,
			@RequestParam(value = "endDate", defaultValue = "") String endDate) {
		// getting value for unique to use page
		int numUsers = getNumUsers(server.table);
		// getting value for top 3 browsers
		List<String> topBrowsers = getTopBrowsers(server.table);
		// getting top 3 urls
		List<String> topUrls = getTopUrls(server.table);
		// getting number of requests per time interval
		List<String> numRequests = getNumRequests(server.table);

		// storing information in json object to return it to user
		org.json.JSONObject result = new org.json.JSONObject();

		// adding information to json object
		result.put("Number of unique users", numUsers);
		result.put("Top 3 browsers", topBrowsers);
		result.put("Top 3 URLs", topUrls);
		result.put("Number of request per five minute interval", numRequests);

		return result.toString();
	}

	private List<String> getNumRequests(List<List<String>> table) {
		// return a list of objects
		//create list
		//go through table and update list as you go (because list is in order time wise)
		
		//can have to variable start time and end time that you keep updating
		//at first start time is the time of the first entry in table and end time is 5 minutes after
		//iterate through list updating number variable counting request in the time slot
		//when reach end time add start time variable and that number to object and add it to list
		//next update start and end times to equal the last end time and 5 minutes after
		//do the same thing until through entire table
		return null;
	}

	private List<String> getTopUrls(List<List<String>> table) {
		// create hash map with each unique URL and increase count each time you see it
		// get max three from map and put in list and return
		return null;
	}

	private List<String> getTopBrowsers(List<List<String>> table) {
		// create hash map with each unique browser and increase count each time you see
		// it
		// get max three from map and put in list and return
		return null;
	}

	public int getNumUsers(List<List<String>> table) {
		// create new table, iterate through other table, if unique add to table keep
		// checking
		// return size of new table created
		return 0;
	}

}
