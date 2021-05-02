package com.microservice.trial.ratingsdataservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.trial.ratingsdataservice.model.Rating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	// to verify load balancing
	// we display which service port of the app is called
	// to create multiple instances of th app , change the server.port in command
	// line and execute the jar file obtained after a maven build
	// java -jar -Dserver.port=8001 ratings-data-service-0.0.1-SNAPSHOT.jar
	@Value("${server.port}")
	private String port;

	@RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
	public Rating getMovieRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@RequestMapping(value = "/users/{userId}/{isTimeOut}", method = RequestMethod.GET)
	public List<Rating> getMovieRatingForAUsera(@PathVariable("userId") String movieId,
			@PathVariable(name = "isTimeOut") int isTimeOut) throws Exception {
		System.out.println("-------------------------port used-----------------------" + port);
		if (isTimeOut > 5) {
			Thread.sleep(10000);
		}
		return Arrays.asList(new Rating("121", 3), new Rating("232", 5));
	}

}