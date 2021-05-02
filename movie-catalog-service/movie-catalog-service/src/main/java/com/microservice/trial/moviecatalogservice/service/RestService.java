package com.microservice.trial.moviecatalogservice.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.trial.moviecatalogservice.entity.CatalogItem;
import com.microservice.trial.moviecatalogservice.entity.Movie;
import com.microservice.trial.moviecatalogservice.entity.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RestService {
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getcatalogItemsInfo(Rating rating) {
		Movie movie = restTemplate.getForEntity("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class)
				.getBody();
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}

	@HystrixCommand(fallbackMethod = "getFallbackRatings")
	public List<Rating> getRatingsFromRatingsService(String userId, int isTimeOut) throws URISyntaxException {
		URI uri = new URI("http://ratings-data-service/ratingsdata/users/" + userId + "/" + isTimeOut);
		RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
		List<Rating> ratings = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Rating>>() {
		}).getBody();
		return ratings;
	}
	
	private List<Rating> getFallbackRatings(String userId, int isTimeOut) throws URISyntaxException {
		return Arrays.asList(new Rating("22", 999666));
	}

	private CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("name Not found ", "", rating.getRating());
	}
}
