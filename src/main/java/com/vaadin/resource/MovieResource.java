package com.vaadin.resource;

import com.vaadin.model.MovieGoerModel;
import com.vaadin.model.MovieModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

@Component
public class MovieResource {
    private final RestTemplate restTemplate;

    private static final String RESOURCE_URL = "http://localhost:8080/movie";
    private static final String ONE_URI = "/{id}";

    public MovieResource(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.rootUri(RESOURCE_URL).build();
    }

    public URI create(MovieModel data){
        return restTemplate.postForLocation(RESOURCE_URL, data);
    }

    public MovieModel findById(int id){
        return restTemplate.getForObject(RESOURCE_URL + ONE_URI, MovieModel.class, id);
    }

    public List<MovieModel> findAll(){
        ResponseEntity<List<MovieModel>> result = restTemplate.exchange(RESOURCE_URL + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<MovieModel>>() {});
        return result.getBody();
    }

    public MovieModel findByName(String name){
        HashMap<String, String> params= new HashMap<String, String>();
        params.put("name", name);
        return  restTemplate.getForObject(RESOURCE_URL, MovieModel.class, params);
    }

    public List<MovieModel> findAllByDirector(String director){
        HashMap<String, String> params= new HashMap<String, String>();
        params.put("director", director);
        ResponseEntity<List<MovieModel>> result = restTemplate.exchange(RESOURCE_URL + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<MovieModel>>() {}, params);
        return result.getBody();
    }

    public List<MovieModel> findAllByRating(String rating){
        HashMap<String, String> params= new HashMap<String, String>();
        params.put("rating", rating);
        ResponseEntity<List<MovieModel>> result = restTemplate.exchange(RESOURCE_URL + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<MovieModel>>() {}, params);
        return result.getBody();
    }

    public void update(MovieModel data, int id){
        restTemplate.put(RESOURCE_URL + ONE_URI, data, id);
    }
}