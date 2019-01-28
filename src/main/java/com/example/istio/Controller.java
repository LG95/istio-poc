package com.example.istio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author LG95
 **/
@RestController
public class Controller {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String externalBaseUrl;

    public Controller(@Value("${external.base.url}") String externalBaseUrl) {
        this.externalBaseUrl = externalBaseUrl;
    }

    @GetMapping(value = "/{path}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String get(@PathVariable String path) {
        return restTemplate.getForEntity(URI.create(externalBaseUrl + path), String.class).getBody();
    }
}
