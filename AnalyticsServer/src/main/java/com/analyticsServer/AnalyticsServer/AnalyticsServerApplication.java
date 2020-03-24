package com.analyticsServer.AnalyticsServer;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//class to run server 
@SpringBootApplication
public class AnalyticsServerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AnalyticsServerApplication.class, args);
	}

}
