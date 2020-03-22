package com.analyticsServer.AnalyticsServer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class trackViewServer {

	// in memory data structure to hold values every time user enters web site
	public static List<List<String>> table = new ArrayList<List<String>>();


	@RequestMapping(value = "/track-view", method = RequestMethod.GET)
	public List<String> getParam(@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "url", defaultValue = "") String url,
			@RequestParam(value = "browser", defaultValue = "") String browser,
			@RequestParam(value = "screenSize", defaultValue = "") String screenSize) {
		

		// getting time stamp
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String time = dateFormat.format(new Date());

		// getting ip address
		String ipAddress;
		try {

			ipAddress = InetAddress.getLocalHost().getHostAddress();

		} catch (UnknownHostException e) {
			ipAddress = null;
		}

		// adding user information to record and adding it to in memory data structure
		List<String> record = new ArrayList<String>();
		record.add(ipAddress); // index 0
		record.add(time); // index 1
		record.add(id); // index 2
		record.add(browser); // index 3
		record.add(screenSize); // index 4
		record.add(url); // index 5

		table.add(record);



		// returning results
		return record;

	}

}
