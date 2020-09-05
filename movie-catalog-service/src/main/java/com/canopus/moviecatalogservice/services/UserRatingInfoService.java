package com.canopus.moviecatalogservice.services;

import com.canopus.moviecatalogservice.model.Rating;
import com.canopus.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class UserRatingInfoService {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://ratings-data-service/ratings-data/users/" + userId, UserRating.class);
    }

    private UserRating getFallbackUserRating(String userId) {
        return new UserRating(Collections.singletonList(new Rating("test_movie_id", 0)));
    }
}
