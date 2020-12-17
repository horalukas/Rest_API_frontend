package com.vaadin.resource;

import com.vaadin.model.MovieGoerDTO;
import com.vaadin.model.MovieGoerModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MovieGoerResource {
    private final RestTemplate restTemplate;

    private static final String RESOURCE_URL = "http://localhost:8080/moviegoer";
    private static final String ONE_URI = "/{id}";
    public static String currentUser = "testEmail";

    public MovieGoerResource(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.rootUri(RESOURCE_URL).build();
    }

    public URI create(MovieGoerModel data){
        return restTemplate.postForLocation(RESOURCE_URL, data);
    }

    public MovieGoerModel findById(int id){
        return restTemplate.getForObject(RESOURCE_URL + ONE_URI, MovieGoerModel.class, id);
    }

    public MovieGoerDTO findByEmail(String email){
        return restTemplate.getForObject(RESOURCE_URL + "?email={email}", MovieGoerDTO.class, email);
    }

    public List<MovieGoerModel> findAll(){
        ResponseEntity<List<MovieGoerModel>> result = restTemplate.exchange(RESOURCE_URL + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<MovieGoerModel>>() {});
        return result.getBody();
    }

    public void update(MovieGoerModel data, int id){
        restTemplate.put(RESOURCE_URL + ONE_URI, data, id);
    }

    public void deleteById(int id){
        restTemplate.delete(RESOURCE_URL + ONE_URI);
    }

    public void deleteByEmail(String email){
        restTemplate.delete(RESOURCE_URL + "?email={email}", email);
    }
}
