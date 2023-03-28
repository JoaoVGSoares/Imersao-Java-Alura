package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Main {
    private static final String TOP_MOVIES_URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";


    public static void main(String[] args) throws Exception {
        //fazer uma conexão http e buscar os top 250 filmes
        var address = URI.create(TOP_MOVIES_URL);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //extrair os dados que interessam(titulo, poster, classificação)

        ObjectMapper mapper = new ObjectMapper();
        MovieResponse movieResponse = mapper.readValue(body, MovieResponse.class);
        System.out.println(movieResponse);

        for (Movie movie : movieResponse.getItems()) {
            double rating = Double.parseDouble(movie.getImDbRating());
            int stars = (int) Math.round(rating);

            String title = "Title: " + movie.getTitle();
            String ratingStars = "Rating: " + movie.getImDbRating() + " ";
            String poster = "Poster: " + movie.getImage();

            System.out.println(title);
            for(int i = 0; i < stars; i++) {
                System.out.print("\u2B50");
            }
            System.out.println();
            System.out.println("\033[30;44m" + ratingStars + "\033[0m");
            System.out.println(poster);
            System.out.println();
        }

    }}