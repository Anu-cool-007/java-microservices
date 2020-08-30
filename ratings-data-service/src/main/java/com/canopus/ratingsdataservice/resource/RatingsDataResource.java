package com.canopus.ratingsdataservice.resource;

import com.canopus.ratingsdataservice.model.Rating;
import com.canopus.ratingsdataservice.model.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings-data")
public class RatingsDataResource {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("/users/{userId}")
    public UserRating getRatings(@PathVariable String userId) {
        return new UserRating(Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 4)
        ));
    }
}
