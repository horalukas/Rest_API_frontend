package com.vaadin.resource;

import com.vaadin.model.MovieStarModel;
import com.vaadin.model.TicketSeatDTO;
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
public class TicketSeatResource {
    private final RestTemplate restTemplate;

    private static final String RESOURCE_URL = "http://localhost:8080/ticketseat";
    private static final String ONE_URI = "/{id}";

    public TicketSeatResource(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.rootUri(RESOURCE_URL).build();
    }

    public URI create(TicketSeatModel data){
        return restTemplate.postForLocation(RESOURCE_URL, data);
    }

    public TicketSeatModel findById(long id){
        return restTemplate.getForObject(RESOURCE_URL + ONE_URI, TicketSeatModel.class, id);
    }

    public List<TicketSeatModel> findAll(){
        ResponseEntity<List<TicketSeatModel>> result = restTemplate.exchange(RESOURCE_URL + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<TicketSeatModel>>() {});
        return result.getBody();
    }

    public List<TicketSeatModel> findAllByOwnerEmail(String email){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        ResponseEntity<List<TicketSeatModel>> result = restTemplate.exchange(RESOURCE_URL + "?email={email}", HttpMethod.GET, null, new ParameterizedTypeReference<List<TicketSeatModel>>() {}, params);
        return result.getBody();
    }

    public List<TicketSeatDTO> findAllByScreeningId(int id){
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", id);
        ResponseEntity<List<TicketSeatDTO>> result = restTemplate.exchange(RESOURCE_URL + "?id={id}", HttpMethod.GET, null, new ParameterizedTypeReference<List<TicketSeatDTO>>() {}, params);
        return result.getBody();
    }

    public void update(TicketSeatModel data, long id){
        restTemplate.put(RESOURCE_URL + ONE_URI, data, id);
    }
}
