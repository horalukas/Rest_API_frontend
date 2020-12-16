package com.vaadin.resource;

import com.vaadin.model.MovieStarModel;
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
public class MovieStarResource {
    private final RestTemplate restTemplate;

    private static final String RESOURCE_URL = "http://localhost:8080/moviestar";
    private static final String ONE_URI = "/{id}";

    public MovieStarResource(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.rootUri(RESOURCE_URL).build();
    }

    public URI create(MovieStarModel data){
        return restTemplate.postForLocation(RESOURCE_URL, data);
    }

    public MovieStarModel findById(int id){
        return restTemplate.getForObject(RESOURCE_URL + ONE_URI, MovieStarModel.class, id);
    }

    public List<MovieStarModel> findAllByIds(List<Integer> ids){
        HashMap<String, List<Integer>> params = new HashMap<String, List<Integer>>();
        params.put("ids", ids);
        ResponseEntity<List<MovieStarModel>> result = restTemplate.exchange(RESOURCE_URL + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<MovieStarModel>>() {}, params);
        return result.getBody();
    }

    public MovieStarModel findByName(String first, String last){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("first", first);
        params.put("last", last);
        return  restTemplate.getForObject(RESOURCE_URL, MovieStarModel.class, params);
    }

    public List<MovieStarModel> findAll(){
        ResponseEntity<List<MovieStarModel>> result = restTemplate.exchange(RESOURCE_URL + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<MovieStarModel>>() {});
        return result.getBody();
    }

    public void update(MovieStarModel data, int id){
        restTemplate.put(RESOURCE_URL + ONE_URI, data, id);
    }
}
