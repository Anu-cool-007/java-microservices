package com.canopus.moviecatalogservice.services;

import com.canopus.moviecatalogservice.model.CatalogItem;
import com.canopus.moviecatalogservice.model.Movie;
import com.canopus.moviecatalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
    }

    private CatalogItem getFallbackCatalogItem(Rating userRating) {
        return new CatalogItem("Movie name not found", "", userRating.getRating());
    }
}
