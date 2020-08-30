package com.canopus.movieinfoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieSummary {
    @JsonProperty("Title")
    public String title;

    @JsonProperty("Plot")
    public String plot;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public MovieSummary() {
    }
}
