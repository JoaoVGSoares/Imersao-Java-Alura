package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {

    String rating;
    private Movie[] items;

    public Movie[] getItems() {
        return items;
    }

    public void setItems(Movie[] items) {
        this.items = items;
    }
}