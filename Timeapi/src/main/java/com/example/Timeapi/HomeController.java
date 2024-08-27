package com.example.Timeapi;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/time-conversion")
	public String getTimeInLocations(@RequestParam String usaLocation) {
		// Define time zones
		ZoneId usaZoneId;
		try {
			usaZoneId = ZoneId.of(usaLocation);
		} catch (Exception e) {
			return "Invalid USA location or timezone ID";
		}

		ZoneId indiaZoneId = ZoneId.of("Asia/Kolkata");

		// Get current time in specified USA location
		ZonedDateTime usaTime = ZonedDateTime.now(usaZoneId);
		ZonedDateTime indiaTime = usaTime.withZoneSameInstant(indiaZoneId);

		// Format the output
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a z");

		String response = String.format("%s Time: %s\nIndia Time: %s", usaLocation, usaTime.format(formatter),
				indiaTime.format(formatter));

		return response;
	}

}
