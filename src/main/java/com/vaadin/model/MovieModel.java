package com.vaadin.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class MovieModel {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String director;
    @NotNull
    private int minutes;
    @NotEmpty
    @NotNull
    private String rating;
    private List<Integer> starIds;

    public MovieModel(String name, String director, int minutes, String rating, List<Integer> starIds) {
        this.name = name;
        this.director = director;
        this.minutes = minutes;
        this.rating = rating;
        this.starIds = starIds;
    }

    public MovieModel() {
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getRating() {
        return rating;
    }

    public List<Integer> getStarIds() { return starIds; }
}
