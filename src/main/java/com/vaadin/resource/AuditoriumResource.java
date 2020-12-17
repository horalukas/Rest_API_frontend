package com.vaadin.resource;

import com.vaadin.model.AuditoriumDTO;
import com.vaadin.model.AuditoriumModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class AuditoriumResource {
    private final RestTemplate restTemplate;

    private static final String RESOURCE_URL = "http://localhost:8080/auditorium";
    private static final String ONE_URI = "/{id}";

    public AuditoriumResource(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.rootUri(RESOURCE_URL).build();
    }

    public URI create(AuditoriumModel data){
        return restTemplate.postForLocation(RESOURCE_URL, data);
    }

    public AuditoriumModel findById(int id){
        return restTemplate.getForObject(RESOURCE_URL + ONE_URI, AuditoriumModel.class, id);
    }

    public List<AuditoriumDTO> findAll(){
        ResponseEntity<List<AuditoriumDTO>> result = restTemplate.exchange(RESOURCE_URL + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<AuditoriumDTO>>() {});
        return result.getBody();
    }

    public void update(AuditoriumModel data, int id){
        restTemplate.put(RESOURCE_URL + ONE_URI, data, id);
    }
}
