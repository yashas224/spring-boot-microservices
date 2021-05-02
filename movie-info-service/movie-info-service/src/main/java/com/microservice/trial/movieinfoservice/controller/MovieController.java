package com.microservice.trial.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.trial.movieinfoservice.entity.Movie;
import com.microservice.trial.movieinfoservice.entity.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${api.key}")
	private String apiKey;

	@GetMapping("/{movieId}")
	public Movie getMovies(@PathVariable String movieId) {
		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieSummary.class);

		return new Movie(movieSummary.getId(), movieSummary.getTitle(), movieSummary.getOverview());
	}
}
