package com.microservice.trial.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.trial.moviecatalogservice.entity.CatalogItem;
import com.microservice.trial.moviecatalogservice.entity.Rating;
import com.microservice.trial.moviecatalogservice.service.RestService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	RestService restService;

	@GetMapping("/{userId}/{isTimeOut}")
	public List<CatalogItem> getCatalog(@PathVariable(name = "userId") String userId,
			@PathVariable(name = "isTimeOut") int isTimeOut) throws Exception {
		List<Rating> ratings = restService.getRatingsFromRatingsService(userId, isTimeOut);
		return ratings.stream().map(rating -> restService.getcatalogItemsInfo(rating)).collect(Collectors.toList());
	}

}

// web cient

// @Autowired
// private WebClient.Builder webclientBuilder;

// Movie movie = webclientBuilder.build().method(HttpMethod.GET)
// .uri("http://localhost:8082/movies/" +
// rating.getMovieId()).retrieve().bodyToMono(Movie.class)
// .block();