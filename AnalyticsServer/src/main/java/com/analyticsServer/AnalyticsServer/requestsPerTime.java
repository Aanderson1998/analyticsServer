package com.analyticsServer.AnalyticsServer;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//class that makes the list of objects for requests over time
public class requestsPerTime {

	// constructor has to variables, the time stamp and number of requests
	public Timestamp time;
	public int requests;

	public requestsPerTime(Timestamp time, int requests) {
		this.time = time;
		this.requests = requests;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}

	// function to get request per time data
	public static List<requestsPerTime> getNumRequests(List<List<String>> table) throws ParseException {
		int index = 1; // index of time stamp variable within in memory data structure
		
		// creating object list that will hold number of request for each time interval
		List<requestsPerTime> requestPerTime = new ArrayList<>();
		
		// getting and setting start time (first time stamp in table)
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String ts = table.get(0).get(index);
		Timestamp startTime = new Timestamp(((java.util.Date) df.parse(ts)).getTime());
		
		// initial number of request at start time (this value is used to keep track of
		// number of requests)
		int num = 0;
		
		// time stamp for end time. Which is 5 minutes after start time (to get five
		// minute intervals)
		Timestamp endTime = new Timestamp(startTime.getTime() + (1000 * 60 * 5));
		
		// for loop to go through table adding requests done within the start and end
		// time
		// when the time of request = the end time, the start and end time are updated,
		// the number of request is set back to 0, and the old number of request and
		// start time is put in object list
		for (int i = 0; i < table.size(); i++) {
			String time = table.get(i).get(index);
			Timestamp ConvertedTime = new Timestamp(((java.util.Date) df.parse(time)).getTime());
			if (ConvertedTime.before(endTime)) {
				num++;
			} else {
				requestPerTime.add(new requestsPerTime(startTime, num));
				startTime = endTime;
				endTime = new Timestamp(startTime.getTime() + (1000 * 60 * 5));
				num = 0;
			}
		}
		requestPerTime.add(new requestsPerTime(startTime, num));

		// returning list
		return requestPerTime;
	}
}
