package com.analyticsServer.AnalyticsServer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//this is class that the analytic server makes get request to to gather information

@RestController
//cross origin to localhost:8080 so my other web pages can fetch data from this server
@CrossOrigin(origins = "http://localhost:8080")
public class viewServer {

	@RequestMapping(value = "/views", method = RequestMethod.GET)
	public String getStatParams(@RequestParam(value = "startDate", defaultValue = "") String startDate,
			@RequestParam(value = "endDate", defaultValue = "") String endDate) throws ParseException {
		// adding hour-minutes-seconds to entered dates so they can be transformed to
		// time stamps and work with calculations
		String newStartDate = startDate + " 00:00:00";
		String newEndDate = endDate + " 00:00:00";

		// creating new table with only rows that are between start and end date
		List<List<String>> filteredTable = new ArrayList<List<String>>();

		// changing the strings of the entered dates to time stamps
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Timestamp convertedStart = new Timestamp(((java.util.Date) df.parse(newStartDate)).getTime());
		Timestamp convertedEnd = new Timestamp(((java.util.Date) df.parse(newEndDate)).getTime());

		// index of column in table in memory with time variable is 1
		int index = 1;

		// going through table in memory and adding in rows that have times between the
		// start and end time
		for (int i = 0; i < trackViewServer.table.size(); i++) {
			String time = trackViewServer.table.get(i).get(index);
			Timestamp convertedTime = new Timestamp(((java.util.Date) df.parse(time)).getTime());
			if (convertedTime.before(convertedEnd) && convertedTime.after(convertedStart)) {
				filteredTable.add(trackViewServer.table.get(i));
			}
		}

		// getting value for unique to use page
		int numUsers = getData.getNumUsers(filteredTable);

		// getting value for top 3 browsers
		List<String> topBrowsers = getData.getTopBrowsers(filteredTable);

		// getting top 3 URLs
		List<String> topUrls = getData.getTopUrls(filteredTable);

		// getting number of requests per time interval
		List<requestsPerTime> numRequests = requestsPerTime.getNumRequests(filteredTable);

		// storing information in JSON object to return it to user
		JSONObject result = new JSONObject();

		// adding information to JSON object
		result.put("uniqueUsers", numUsers);
		result.put("browsers", topBrowsers);
		result.put("URLs", topUrls);
		result.put("requests", numRequests);

		// return object
		return result.toString();
	}

}
