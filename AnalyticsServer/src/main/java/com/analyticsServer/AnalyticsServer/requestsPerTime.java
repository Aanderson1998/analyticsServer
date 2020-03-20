package com.analyticsServer.AnalyticsServer;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class requestsPerTime {

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

	
	
	public static List<requestsPerTime> getNumRequests(List<List<String>> table) throws ParseException {
		int index = 4;
		List<requestsPerTime> requestPerTime = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String ts = table.get(0).get(index);
		Timestamp startTime = new Timestamp(((java.util.Date) df.parse(ts)).getTime());

		for (int i = 0; i <= table.size(); i++) {
			Timestamp endTime = new Timestamp(startTime.getTime() + (1000 * 60 * 5));
			String time = table.get(i).get(index);
			Timestamp ConvertedTime = new Timestamp(((java.util.Date) df.parse(time)).getTime());
			int num = 0;
			if (ConvertedTime.before(endTime)) {
				num++;
			}
			requestPerTime.add(new requestsPerTime(startTime, num));
			startTime = endTime;
		}
		return requestPerTime;
	}
}


