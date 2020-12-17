package com.vaadin.resource;

import com.vaadin.model.ScreeningDTO;
import com.vaadin.model.ScreeningModel;
import com.vaadin.model.TicketSeatModel;
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
public class ScreeningResource {
    private final RestTemplate restTemplate;

    private static final String RESOURCE_URL = "http://localhost:8080/screening";
    private static final String ONE_URI = "/{id}";

    public ScreeningResource(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.rootUri(RESOURCE_URL).build();
    }

    public URI create(ScreeningModel data){
        return restTemplate.postForLocation(RESOURCE_URL, data);
    }

    public ScreeningModel findById(int id){
        return restTemplate.getForObject(RESOURCE_URL + ONE_URI, ScreeningModel.class, id);
    }

    public List<ScreeningDTO> findAll(){
        ResponseEntity<List<ScreeningDTO>> result = restTemplate.exchange(RESOURCE_URL + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<ScreeningDTO>>() {});
        return result.getBody();
    }

    public List<ScreeningModel> findAllByMovieName(String name){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        ResponseEntity<List<ScreeningModel>> result = restTemplate.exchange(RESOURCE_URL + "?name={name}", HttpMethod.GET, null, new ParameterizedTypeReference<List<ScreeningModel>>() {}, params);
        return result.getBody();
    }

    public void update(ScreeningModel data, int id){
        restTemplate.put(RESOURCE_URL + ONE_URI, data, id);
    }

    public void deleteById(int id){
        restTemplate.delete(RESOURCE_URL + ONE_URI);
    }
}
