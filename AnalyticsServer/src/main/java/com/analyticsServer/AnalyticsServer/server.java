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
public class server {

	// in memory data structure to hold values every time user enters web site
	public static List<List<String>> table = new ArrayList<List<String>>();

	@RequestMapping(value = "/track-view", method = RequestMethod.GET)
	public String getParam(@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "url", defaultValue = "") String url,
			@RequestParam(value = "browser", defaultValue = "") String browser,
			@RequestParam(value = "screenSize", defaultValue = "") String screenSize) {

		// getting time stamp
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String time = dateFormat.format(new Date());

		// getting IP address

		// adding user information to record and adding it to in memory data structure
		List<String> record = new ArrayList<String>();
		record.add(id);
		record.add(url);
		record.add(browser);
		record.add(screenSize);
		record.add(time);
		// IP address
		table.add(record);

		// creating JSON object to put data and return to user (to show request was
		// sent)
		org.json.JSONObject result = new org.json.JSONObject();

		result.put("status", "in memory");
		result.put("id", id);
		result.put("url", url);
		result.put("browser", browser);
		result.put("screenSize", screenSize);
		result.put("ip address", "we getting it");
		result.put("timestamp", time);

		// returning results
		return result.toString();

	}

}
