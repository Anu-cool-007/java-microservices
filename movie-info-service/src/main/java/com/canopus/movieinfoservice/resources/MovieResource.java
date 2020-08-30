package com.canopus.movieinfoservice.resources;

import com.canopus.movieinfoservice.model.Movie;
import com.canopus.movieinfoservice.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject(
                "http://www.omdbapi.com/?i=" + movieId + "&apikey=" + apiKey,
                MovieSummary.class
        );

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getPlot());
    }
}
