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

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class viewServer {


	@RequestMapping(value = "/views", method = RequestMethod.GET)
	public String getStatParams(@RequestParam(value = "startDate", defaultValue = "") String startDate,
			@RequestParam(value = "endDate", defaultValue = "") String endDate) throws ParseException {
		String newStartDate= startDate + " 00:00:00";
		String newEndDate= endDate + " 00:00:00";
		// creating new table with only rows that are between start and end date
		List<List<String>> filteredTable = new ArrayList<List<String>>();
		// changing the strings of the dates to time stamps
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Timestamp convertedStart = new Timestamp(((java.util.Date) df.parse(newStartDate)).getTime());
		Timestamp convertedEnd = new Timestamp(((java.util.Date) df.parse(newEndDate)).getTime());
		// going through table in memory and adding in rows that have times between the
		// start and end time
		// index of column in table with time is 1
		int index = 1;
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
