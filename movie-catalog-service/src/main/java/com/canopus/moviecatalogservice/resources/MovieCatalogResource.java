package com.canopus.moviecatalogservice.resources;

import com.canopus.moviecatalogservice.model.CatalogItem;
import com.canopus.moviecatalogservice.model.UserRating;
import com.canopus.moviecatalogservice.services.MovieInfoService;
import com.canopus.moviecatalogservice.services.UserRatingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private UserRatingInfoService userRatingInfoService;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        // get all rated movie IDs
        UserRating userRating = userRatingInfoService.getUserRating(userId);
        return userRating.getUserRatings().stream().map(movieInfoService::getCatalogItem).collect(Collectors.toList());
    }

    @GetMapping("/services")
    public List<ServiceInstance> getEurekaServices() {
        List<String> services = this.discoveryClient.getServices();
        List<ServiceInstance> instances = new ArrayList<>();
        services.forEach(serviceName -> {
            instances.addAll(this.discoveryClient.getInstances(serviceName));
        });
        return instances;
    }
}
